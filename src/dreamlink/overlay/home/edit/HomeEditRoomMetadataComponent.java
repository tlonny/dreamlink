package dreamlink.overlay.home.edit;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.disk.LocalRoom;
import dreamlink.disk.LocalRoomState;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.IButtonComponentProvider;
import dreamlink.overlay.component.button.StaticButtonComponentProvider;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.CharacterData;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.overlay.component.text.line.input.ITextLineInputComponentProvider;
import dreamlink.overlay.component.text.line.input.TextLineInputComponent;
import dreamlink.overlay.component.text.line.view.ITextLineViewComponentProvider;
import dreamlink.overlay.component.text.line.view.TextLineViewComponent;
import dreamlink.utility.maths.Vector4fMaths;

public class HomeEditRoomMetadataComponent extends WrapperComponent {

    private class InternalTitleTextViewComponentProvider implements ITextLineViewComponentProvider {

        @Override
        public void setCharacterState(int characterIndex, CharacterData characterState) {
            if(characterIndex < HomeEditRoomMetadataComponent.titlePrefix.length()) {
                characterState.set(
                    HomeEditRoomMetadataComponent.titlePrefix.charAt(characterIndex),
                    FontDecoration.normal,
                    Vector4fMaths.black
                );
                return;
            }

            characterIndex -= HomeEditRoomMetadataComponent.titlePrefix.length();
            var room = HomeEditRoomMetadataComponent.this.room;
            characterState.set(
                room.roomName.charAt(characterIndex),
                FontDecoration.underline,
                Vector4fMaths.blue
            );
        }

        @Override
        public int getCharacterOffset() {
            return 0;
        }

        @Override
        public int getCharacterSize() {
            return 0
                + HomeEditRoomMetadataComponent.titlePrefix.length() 
                + HomeEditRoomMetadataComponent.this.room.roomName.length();
        }

    }

    private class InternalNameTextInputComponentProvider implements ITextLineInputComponentProvider {

        @Override
        public String getValue() {
            return HomeEditRoomMetadataComponent.this.name;
        }

        @Override
        public void onChange(String value) {
            HomeEditRoomMetadataComponent.this.name = value;
            HomeEditRoomMetadataComponent.this.updateCanRename();
        }

    }

    private class InternalRenameButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            LocalRoomState.instance.renameRoom(
                HomeEditRoomMetadataComponent.this.room.roomName,
                HomeEditRoomMetadataComponent.this.name
            );
            HomeEditRoomMetadataComponent.this.updateCanRename();
            HomeEditRoomMetadataComponent.this.provider.gotoList();
        }

        @Override
        public boolean isButtonDisabled() {
            return !HomeEditRoomMetadataComponent.this.canRename;
        }

    }

    private class InternalCopyButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            LocalRoomState.instance.copyRoom(
                HomeEditRoomMetadataComponent.this.room.roomName,
                HomeEditRoomMetadataComponent.this.name
            );
            HomeEditRoomMetadataComponent.this.updateCanRename();
            HomeEditRoomMetadataComponent.this.provider.gotoList();
        }

        @Override
        public boolean isButtonDisabled() {
            return !HomeEditRoomMetadataComponent.this.canRename;
        }

    }

    private static final int itemSpacing = 5;
    private static final Vector2ic buttonDimensions = new Vector2i(160, 24);
    private static final String titlePrefix = "Edit Metadata:";
    private static final String roomNameLabelText = "Room Name:";
    private static final String renameButtonText = "Rename";
    private static final String copyToButtonText = "Copy";
    private static final String backButtonText = "Back";

    private final IComponent component;
    private final IHomeEditComponentProvider provider;
    
    private String name;
    private LocalRoom room;
    private boolean canRename;

    public void clear(LocalRoom room) {
        this.room = room;
        this.name = room.roomName;
    }

    public HomeEditRoomMetadataComponent(IHomeEditComponentProvider provider) {
        this.provider = provider;
        this.component = new VerticalSpanComponent(
            Alignment.start,
            HomeEditRoomMetadataComponent.itemSpacing
        )
            .addComponent(
                new TextLineViewComponent(
                    new InternalTitleTextViewComponentProvider(),
                    Alignment.start
                )
            )
            .addComponent(
                new TextLineLabelComponent(
                    HomeEditRoomMetadataComponent.roomNameLabelText, 
                    FontDecoration.normal,    
                    Vector4fMaths.black
                )
            )
            .addComponent(
                new TextLineInputComponent(
                    new InternalNameTextInputComponentProvider()
                )
            )
            .addComponent(
                new HorizontalSpanComponent(
                    Alignment.start,
                    HomeEditRoomMetadataComponent.itemSpacing 
                )
                    .addComponent(
                        new ButtonComponent(
                            new InternalRenameButtonComponentProvider(),
                            new BoxComponent(
                                new TextLineLabelComponent(HomeEditRoomMetadataComponent.renameButtonText, FontDecoration.normal, Vector4fMaths.black),
                                BoxDimension.fixed(HomeEditRoomMetadataComponent.buttonDimensions.x()),
                                BoxDimension.fixed(HomeEditRoomMetadataComponent.buttonDimensions.y())
                            )
                        )
                    )
                    .addComponent(
                        new ButtonComponent(
                            new InternalCopyButtonComponentProvider(),
                            new BoxComponent(
                                new TextLineLabelComponent(HomeEditRoomMetadataComponent.copyToButtonText, FontDecoration.normal, Vector4fMaths.black),
                                BoxDimension.fixed(HomeEditRoomMetadataComponent.buttonDimensions.x()),
                                BoxDimension.fixed(HomeEditRoomMetadataComponent.buttonDimensions.y())
                            ))
                    )
                    .addComponent(
                        new ButtonComponent(
                            new StaticButtonComponentProvider(this.provider::gotoList),
                            new BoxComponent(
                                new TextLineLabelComponent(HomeEditRoomMetadataComponent.backButtonText, FontDecoration.normal, Vector4fMaths.black),
                                BoxDimension.fixed(HomeEditRoomMetadataComponent.buttonDimensions.x()),
                                BoxDimension.fixed(HomeEditRoomMetadataComponent.buttonDimensions.y())
                            ))
                    )
            );
    }

    private void updateCanRename() {
        this.canRename = !LocalRoomState.instance.hasRoom(this.name);
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
