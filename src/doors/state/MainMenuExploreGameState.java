package doors.state;

import java.util.Arrays;

import org.lwjgl.opengl.GL42;

import doors.Config;
import doors.graphics.rendertarget.PhysicalRenderTarget;
import doors.graphics.texture.TextureChannel;
import doors.graphics.sprite.SpriteBatch;
import doors.ui.UITextureAtlas;
import doors.ui.element.AlignmentElement;
import doors.ui.element.ContainerElement;
import doors.ui.element.HorizontalSpanElement;
import doors.ui.element.IUIElement;
import doors.ui.element.IconElement;
import doors.ui.element.MenuButtonElement;
import doors.ui.element.MenuTitleElement;
import doors.ui.element.TextInputElement;
import doors.ui.element.TextLabelElement;
import doors.ui.element.VerticalSpanElement;
import doors.ui.element.WindowElement;
import doors.ui.element.AlignmentElement.HorizontalAlignment;
import doors.ui.element.ButtonElement;
import doors.perspective.FlatPerspective;
import doors.utility.geometry.Vector2in;
import doors.utility.geometry.Vector3fl;

public class MainMenuExploreGameState extends GameState {

    public static MainMenuExploreGameState MAIN_MENU_EXPLORE_GAME_STATE = new MainMenuExploreGameState();

    private IUIElement root;
    private TextInputElement levelInput;

    public void setup() {
        // We need to do this in setup to stop a circular references to other game states
        this.root = new ContainerElement(this.buildWindow());
    }

    public MainMenuExploreGameState() {
        this.levelInput = new TextInputElement(20);
    }

    private void explore() {
        ExploreGameState.EXPLORE_GAME_STATE.use(this.levelInput.getText());
    }

    private IUIElement buildWindow() {
        return new WindowElement(
            new VerticalSpanElement(Arrays.asList(
                new AlignmentElement(new MenuTitleElement(UITextureAtlas.ICON_EXPLORE, "Doors.Explore")),
                new AlignmentElement(new VerticalSpanElement(Arrays.asList(
                    new AlignmentElement(new TextLabelElement("Level:", false, Vector3fl.ZERO), HorizontalAlignment.LEFT),
                    new AlignmentElement(
                        new HorizontalSpanElement(Arrays.asList(
                            new AlignmentElement(this.levelInput),
                            new AlignmentElement(new ButtonElement(new IconElement(TextureChannel.UI_TEXTURE_CHANNEL, UITextureAtlas.ICON_TICK), this::explore))
                        ),4)
                    )
                ), 0)),
                new AlignmentElement(new MenuButtonElement(UITextureAtlas.ICON_QUIT, "Back", MainMenuRootGameState.MAIN_MENU_ROOT_GAME_STATE::use))
            ), 4)
        );
    }

    @Override
    public void update() {
        this.root.calculateDimensions();
        var origin = new Vector2in(Config.RESOLUTION).sub(this.root.getDimensions()).div(2);
        this.root.calculatePosition(origin);
        this.root.update();
        this.root.writeElement();

        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.useRenderTarget();
        GL42.glDisable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
        FlatPerspective.FLAT_PERSPECTIVE.apply();

        SpriteBatch.SPRITE_BATCH.updateMesh();
        SpriteBatch.SPRITE_BATCH.render();
    }
}
