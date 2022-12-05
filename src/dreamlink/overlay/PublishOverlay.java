package dreamlink.overlay;

import java.io.IOException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;
import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.config.RepositoryConfig;
import dreamlink.config.UserSettingsConfig;
import dreamlink.disk.LocalRoom;
import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.glconfig.blend.BlendMode;
import dreamlink.graphics.glconfig.blend.BlendModeConfig;
import dreamlink.graphics.program.SpriteShaderProgram;
import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.border.dialog.DialogBorderComponent;
import dreamlink.overlay.component.border.dialog.DialogState;
import dreamlink.overlay.component.border.dialog.StaticDialogBorderComponentProvider;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.IButtonComponentProvider;
import dreamlink.overlay.component.icon.IconComponent;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.CharacterData;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.overlay.component.text.line.view.ITextLineViewComponentProvider;
import dreamlink.overlay.component.text.line.view.TextLineViewComponent;
import dreamlink.overlay.component.window.WindowComponent;
import dreamlink.overlay.eventspan.EventSpanManager;
import dreamlink.state.scene.HomeScene;
import dreamlink.utility.file.FileFns;
import dreamlink.utility.file.TempFile;
import dreamlink.utility.maths.Vector2iMaths;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.window.Window;
import dreamlink.worker.WorkerPool;
import dreamlink.worker.WorkerTask;

public class PublishOverlay extends WrapperComponent {

    private class InternalBackButtonProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            HomeScene.instance.bind();
        }

        @Override
        public boolean isButtonDisabled() {
            var task = PublishOverlay.this.task;
            return task != null;
        }

    }

    private class InternalErrorTextProvider implements ITextLineViewComponentProvider {

        @Override
        public int getCharacterSize() {
            var error = PublishOverlay.this.error;
            return error == null
                ? 0
                : error.length();
        }

        @Override
        public int getCharacterOffset() {
            return 0;
        }

        @Override
        public void setCharacterState(int characterIndex, CharacterData characterState) {
            var error = PublishOverlay.this.error;
            characterState.set(
                error.charAt(characterIndex),
                FontDecoration.normal,
                Vector4fMaths.red
            );
        }

    }

    private final static String contentTypeHeaderName = "Content-Type";
    private final static String uploadKeyHeaderName = "X-Upload-Secret";
    private final static String uploadPathSegment = "/v1/public/room/";
    private final static String contentTypeHeaderValue = "application/zip";

    private final static String errorInvalidKey = "Invalid upload key";
    private final static String errorNoAccessToNamespace = "No access to namespace";
    private final static String errorUploadRejected = "Upload rejected";
    private final static String errorConnectionError = "Connection error";

    private static final String windowTitle = "Publishing Room...";
    private static final int itemSpacing = 5;
    private static final int textPadding = 2;
    private static final String backButtonText = "Okay";
    private static final int minTextWidth = 420;
    private static final Vector2ic buttonDimensions = new Vector2i(160, 24);
    private static final Vector2ic iconDimensions = new Vector2i(16, 16);

    public static final PublishOverlay instance = new PublishOverlay();

    private final IComponent component;
    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final EventSpanManager eventSpanManager = new EventSpanManager();

    private LocalRoom room;
    private String error;
    private WorkerTask<String> task;

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    public PublishOverlay() {
        this.component = new WindowComponent(
            MenuTextureSample.iconFolder,
            PublishOverlay.windowTitle,
            new PaddingComponent(
                new VerticalSpanComponent(Alignment.center, PublishOverlay.itemSpacing)
                    .addComponent(
                        new HorizontalSpanComponent(
                            Alignment.center,
                            PublishOverlay.itemSpacing 
                        )
                            .addComponent(
                                new DialogBorderComponent(
                                    new StaticDialogBorderComponentProvider(DialogState.focused),
                                    new IconComponent(
                                        this::getIcon,
                                        PublishOverlay.iconDimensions
                                    )
                                )
                            )
                            .addComponent(
                                new BoxComponent(
                                    new DialogBorderComponent(
                                        new StaticDialogBorderComponentProvider(DialogState.focused),
                                        new PaddingComponent(
                                            new TextLineViewComponent(
                                                new InternalErrorTextProvider(),
                                                Alignment.start
                                            ),
                                            PublishOverlay.textPadding
                                        )
                                    ),
                                    BoxDimension.fixed(PublishOverlay.minTextWidth),
                                    BoxDimension.wrap()
                                )
                            )
                    )
                    .addComponent(
                        new ButtonComponent(
                            new InternalBackButtonProvider(),
                            new BoxComponent(
                                new TextLineLabelComponent(
                                    PublishOverlay.backButtonText,
                                    FontDecoration.normal,
                                    Vector4fMaths.black
                                ),
                                BoxDimension.fixed(PublishOverlay.buttonDimensions.x()),
                                BoxDimension.fixed(PublishOverlay.buttonDimensions.y())
                            )
                        )
                    ),
                PublishOverlay.itemSpacing
            )
        );
    }

    public void setRoom(LocalRoom room) {
        this.room = room;
        this.error = null;
    }

    private String publishData() {
        try(
            var tempFile = new TempFile(".zip");
            var httpClient = HttpClients.createDefault();
        ) {
            var uploadURL = RepositoryConfig.instance
                .getOrigin()
                .resolve(PublishOverlay.uploadPathSegment)
                .resolve(String.format("%s/data", this.room.file.getName()));

            var srcFile = this.room.file;
            FileFns.compressIntoZip(srcFile, tempFile.file);

            var post = new HttpPost(uploadURL);
            post.setEntity(new FileEntity(tempFile.file));
            post.setHeader(
                PublishOverlay.uploadKeyHeaderName, 
                UserSettingsConfig.instance.uploadKey
            );
            post.setHeader(
                PublishOverlay.contentTypeHeaderName, 
                PublishOverlay.contentTypeHeaderValue
            );

            try(var response = httpClient.execute(post)) {
                var statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == 401) {
                    return PublishOverlay.errorInvalidKey;
                } else if(statusCode == 403) {
                    return PublishOverlay.errorNoAccessToNamespace;
                } else if(statusCode != 200) {
                    return PublishOverlay.errorUploadRejected;
                } else {
                    return null;
                }
            }

        } catch (IOException e) {
            return PublishOverlay.errorConnectionError;
        }

    }

    public void update() {
        if(this.task != null && this.task.isDone()) {
            this.error = this.task.join();
            this.task = null;
        }

        this.eventSpanManager.clear();
        super.registerEventSpans(this.eventSpanManager);
        this.eventSpanManager.update();
    }

    public void publish() {
        if(this.task != null) {
            return;
        }
        this.task = WorkerPool.instance.submit(this::publishData);
    }

    private ISpriteTemplate getIcon() {
        if(this.error != null) {
            return MenuTextureSample.iconRestrict;
        }

        if(this.task == null) {
            return MenuTextureSample.iconPermit;
        }

        return null;
    }

    public void setup() {
        this.spriteBatch.setup();
        var resolution = Window.instance.getResolution(new Vector2i());
        this.component.computeInitialDimensions();
        this.component.computeDimensions(resolution);
        this.component.computePosition(Vector2iMaths.zero, SpriteHeight.menu);
    }

    public void destroy() {
        this.spriteBatch.destroy();
    }

    private final Vector2i renderResolution = new Vector2i();
    private final ShaderProgramConfig renderShaderProgramConfig = new ShaderProgramConfig();
    private final BlendModeConfig renderBlendModeConfig = new BlendModeConfig();

    public void render() {
        this.spriteBatch.clear();

        MenuTextureSample.background.writeToSpriteBatch(
            this.spriteBatch,
            Vector2iMaths.zero,
            Window.instance.getResolution(this.renderResolution),
            SpriteHeight.background
        );
        this.writeToSpriteBatch(this.spriteBatch);
        this.eventSpanManager.getCursor().writeToSpriteBatch(this.spriteBatch);
        this.spriteBatch.buffer();

        try(
            var shaderProgramConfig = this.renderShaderProgramConfig.checkpoint();
            var blendModeConfig = this.renderBlendModeConfig.checkpoint();
        ) {
            blendModeConfig.setState(BlendMode.alphaBlend);
            shaderProgramConfig.setState(SpriteShaderProgram.instance);
            this.spriteBatch.render();
        }
    }
    
}
