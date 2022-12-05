package dreamlink.overlay.home.edit;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.disk.LocalRoom;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.border.button.ButtonBorderComponent;
import dreamlink.overlay.component.border.button.ButtonState;
import dreamlink.overlay.component.border.button.StaticButtonBorderComponentProvider;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.StaticButtonComponentProvider;
import dreamlink.overlay.component.choice.ChoiceComponent;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.overlay.component.window.WindowComponent;
import dreamlink.overlay.home.IHomeChildComponentProvider;
import dreamlink.utility.maths.Vector4fMaths;

public class HomeEditComponent extends WrapperComponent {

    private class InternalHomeEditComponentProvider implements IHomeEditComponentProvider {

        @Override
        public void gotoList() {
            HomeEditComponent.this.selectedComponentKey = HomeEditComponent.listComponentKey;
            HomeEditComponent.this.listComponent.updateIsValidSelection();
        }

        @Override
        public void gotoCreate() {
            HomeEditComponent.this.selectedComponentKey = HomeEditComponent.createComponentKey;
            HomeEditComponent.this.createComponent.clear();
        }

        @Override
        public void gotoEdit(LocalRoom room) {
            HomeEditComponent.this.selectedComponentKey = HomeEditComponent.editComponentKey;
            HomeEditComponent.this.editComponent.clear(room);
        }

    }

    private static final String windowTitle = "Dreamlink: Edit";
    private static final String backButtonText = "Back";
    private static final Vector2ic buttonDimensions = new Vector2i(160, 24);
    private static final Vector2ic windowSize = new Vector2i(480, 340);

    private static final int listComponentKey = 0;
    private static final int editComponentKey = 1;
    private static final int createComponentKey = 2;

    private static final int itemSpacing = 5;
    private static final int padding = 5;

    private final IComponent component;
    private final IHomeChildComponentProvider provider;
    private final HomeEditRoomMetadataComponent editComponent;
    private final HomeEditRoomListComponent listComponent;
    private final HomeEditRoomCreateComponent createComponent;
    private int selectedComponentKey = 0;

    public HomeEditComponent(IHomeChildComponentProvider provider) {
        this.provider = provider;

        var roomEditProvider = new InternalHomeEditComponentProvider();
        this.editComponent = new HomeEditRoomMetadataComponent(roomEditProvider);
        this.listComponent = new HomeEditRoomListComponent(roomEditProvider);
        this.createComponent = new HomeEditRoomCreateComponent(roomEditProvider);

        this.component = new WindowComponent(
            MenuTextureSample.iconFolder,
            HomeEditComponent.windowTitle,
            new BoxComponent(
                new PaddingComponent(
                    new VerticalSpanComponent(
                        Alignment.center,
                        HomeEditComponent.itemSpacing
                    ).addComponent(
                        new ButtonBorderComponent(
                            new StaticButtonBorderComponentProvider(ButtonState.normal),
                            new PaddingComponent(
                                new ChoiceComponent<>(
                                    this::getComponentKey
                                )
                                    .addComponent(HomeEditComponent.listComponentKey, this.listComponent)
                                    .addComponent(HomeEditComponent.editComponentKey, this.editComponent)
                                    .addComponent(HomeEditComponent.createComponentKey, this.createComponent),
                                HomeEditComponent.padding
                            )
                        )
                    ).addComponent(
                        new HorizontalSpanComponent(Alignment.center, HomeEditComponent.itemSpacing)
                            .addComponent(
                                new ButtonComponent(
                                    new StaticButtonComponentProvider(this.provider::gotoRoot),
                                    new BoxComponent(
                                        new TextLineLabelComponent(
                                            HomeEditComponent.backButtonText,
                                            FontDecoration.normal,
                                            Vector4fMaths.black
                                        ),
                                        BoxDimension.fixed(HomeEditComponent.buttonDimensions.x()),
                                        BoxDimension.fixed(HomeEditComponent.buttonDimensions.y())
                                    )
                                )
                            )
                    ),
                    HomeEditComponent.itemSpacing
                ),
                BoxDimension.fixed(HomeEditComponent.windowSize.x()),
                BoxDimension.fixed(HomeEditComponent.windowSize.y())
            )
        );
    }

    private int getComponentKey() {
        return this.selectedComponentKey;
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
