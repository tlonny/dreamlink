package dreamlink.overlay.home;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.StaticButtonComponentProvider;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.overlay.component.window.WindowComponent;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.window.Window;

public class HomeRootComponent extends WrapperComponent {

    private static final int itemSpacing = 5;
    private static final Vector2ic buttonDimensions = new Vector2i(210, 24);
    private static final String exploreButtonText = "Explore";
    private static final String editButtonText = "Edit";
    private static final String creditsButtonText = "Credits";
    private static final String settingsButtonText = "Settings";
    private static final String quitButtonText = "Quit";
    private static final String windowTitle = "Dreamlink: Main Menu";

    private final IComponent component;
    private final IHomeChildComponentProvider provider;

    public HomeRootComponent(IHomeChildComponentProvider provider) {
        this.provider = provider;
        this.component = new WindowComponent(
            MenuTextureSample.iconFolder,
            HomeRootComponent.windowTitle,
            new PaddingComponent(
                new VerticalSpanComponent(
                    Alignment.center,
                    HomeRootComponent.itemSpacing
                ).addComponent(
                    new ButtonComponent(
                        new StaticButtonComponentProvider(this.provider::gotoExplore),
                        new BoxComponent(
                            new TextLineLabelComponent(
                                HomeRootComponent.exploreButtonText,
                                FontDecoration.normal,
                                Vector4fMaths.black
                            ),
                            BoxDimension.fixed(HomeRootComponent.buttonDimensions.x()),
                            BoxDimension.fixed(HomeRootComponent.buttonDimensions.y())
                        )
                    )
                ).addComponent(
                    new ButtonComponent(
                        new StaticButtonComponentProvider(this.provider::gotoEdit),
                        new BoxComponent(
                            new TextLineLabelComponent(
                                HomeRootComponent.editButtonText,
                                FontDecoration.normal,
                                Vector4fMaths.black
                            ),
                            BoxDimension.fixed(HomeRootComponent.buttonDimensions.x()),
                            BoxDimension.fixed(HomeRootComponent.buttonDimensions.y())
                        )
                    )
                ).addComponent(
                    new ButtonComponent(
                        new StaticButtonComponentProvider(this.provider::gotoSettings),
                        new BoxComponent(
                            new TextLineLabelComponent(
                                HomeRootComponent.settingsButtonText,
                                FontDecoration.normal,
                                Vector4fMaths.black
                            ),
                            BoxDimension.fixed(HomeRootComponent.buttonDimensions.x()),
                            BoxDimension.fixed(HomeRootComponent.buttonDimensions.y())
                        )
                    )
                ).addComponent(
                    new ButtonComponent(
                        new StaticButtonComponentProvider(this.provider::gotoCredits),
                        new BoxComponent(
                            new TextLineLabelComponent(
                                HomeRootComponent.creditsButtonText,
                                FontDecoration.normal,
                                Vector4fMaths.black
                            ),
                            BoxDimension.fixed(HomeRootComponent.buttonDimensions.x()),
                            BoxDimension.fixed(HomeRootComponent.buttonDimensions.y())
                        )
                    )
                ).addComponent(
                    new ButtonComponent(
                        new StaticButtonComponentProvider(Window.instance::setShouldClose),
                        new BoxComponent(
                            new TextLineLabelComponent(
                                HomeRootComponent.quitButtonText,
                                FontDecoration.normal,
                                Vector4fMaths.black
                            ),
                            BoxDimension.fixed(HomeRootComponent.buttonDimensions.x()),
                            BoxDimension.fixed(HomeRootComponent.buttonDimensions.y())
                        )
                    )
                ),
                HomeRootComponent.itemSpacing
            )
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
