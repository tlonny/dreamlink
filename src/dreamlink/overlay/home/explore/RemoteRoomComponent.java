package dreamlink.overlay.home.explore;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.config.RepositoryConfig;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.IButtonComponentProvider;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.CharacterData;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.overlay.component.text.line.input.ITextLineInputComponentProvider;
import dreamlink.overlay.component.text.line.input.TextLineInputComponent;
import dreamlink.overlay.component.text.line.view.ITextLineViewComponentProvider;
import dreamlink.overlay.component.text.line.view.TextLineViewComponent;
import dreamlink.utility.maths.Vector4fMaths;

public class RemoteRoomComponent extends WrapperComponent {

    private class InternalExploreButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            RemoteRoomComponent.this.provider.onRoomSelect(RemoteRoomComponent.this.roomName);
        }

        @Override
        public boolean isButtonDisabled() {
            return RemoteRoomComponent.this.roomName.isBlank();
        }
    }

    private class InternalRoomNameTextInputComponentProvider implements ITextLineInputComponentProvider {

        @Override
        public String getValue() {
            return RemoteRoomComponent.this.roomName;
        }

        @Override
        public void onChange(String value) {
            RemoteRoomComponent.this.roomName = value;
        }

    }

    private class InternalRepositoryTextComponentProvider implements ITextLineViewComponentProvider {

        @Override
        public void setCharacterState(int characterIndex, CharacterData state) {
            if(characterIndex < RemoteRoomComponent.usingPrefixText.length()) {
                state.set(
                    RemoteRoomComponent.usingPrefixText.charAt(characterIndex),
                    FontDecoration.normal,
                    Vector4fMaths.black
                );
                return;
            }

            characterIndex -= RemoteRoomComponent.usingPrefixText.length();
            state.set(
                RepositoryConfig.instance.getOrigin().getHost().charAt(characterIndex),
                FontDecoration.underline,
                Vector4fMaths.blue
            );
            return;
        }

        @Override
        public int getCharacterOffset() {
            return 0;
        }

        @Override
        public int getCharacterSize() {
            return RemoteRoomComponent.usingPrefixText.length() + RepositoryConfig.instance.getOrigin().getHost().length();
        }

    }

    private static final String usingPrefixText = "Using: ";
    private static final String exploreButtonText = "Explore";
    private static final String instructionsText = "Enter room name:";
    private static final Vector2ic buttonDimensions = new Vector2i(160, 24);
    private static final int itemSpacing = 5;

    private final IRoomSelectProvider provider;
    private final IComponent component;
    private String roomName;

    public RemoteRoomComponent(IRoomSelectProvider provider) {
        this.provider = provider;
        this.component = new VerticalSpanComponent(
            Alignment.start,
            RemoteRoomComponent.itemSpacing
        ).addComponent(
            new HorizontalSpanComponent(
                Alignment.end,
                RemoteRoomComponent.itemSpacing
            )
                .addComponent(
                    new TextLineViewComponent(
                        new InternalRepositoryTextComponentProvider(),
                        Alignment.start
                    )
                )
        ).addComponent(
            new BoxComponent(
                new TextLineLabelComponent(
                    RemoteRoomComponent.instructionsText,
                    FontDecoration.normal,
                    Vector4fMaths.black
                ),
                BoxDimension.grow(Alignment.start),
                BoxDimension.wrap()
            )
        ).addComponent(
            new TextLineInputComponent(
                new InternalRoomNameTextInputComponentProvider()
            )
        ).addComponent(
            new ButtonComponent(
                new InternalExploreButtonComponentProvider(),
                new BoxComponent(
                    new TextLineLabelComponent(
                        RemoteRoomComponent.exploreButtonText,
                        FontDecoration.normal,
                        Vector4fMaths.black
                    ),
                    BoxDimension.fixed(RemoteRoomComponent.buttonDimensions.x()),
                    BoxDimension.fixed(RemoteRoomComponent.buttonDimensions.y())
                )
            )
        );
    }

    public void clear() {
        this.roomName = "";
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
