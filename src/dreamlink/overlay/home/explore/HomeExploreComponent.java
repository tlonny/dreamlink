package dreamlink.overlay.home.explore;

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
import dreamlink.overlay.component.icon.IconComponent;
import dreamlink.overlay.component.icon.StaticIconComponentProvider;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.tab.TabViewComponent;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.overlay.component.window.WindowComponent;
import dreamlink.overlay.home.IHomeChildComponentProvider;
import dreamlink.state.SimulationState;
import dreamlink.state.scene.LoadingScene;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.world.World;

public class HomeExploreComponent extends WrapperComponent {

    private static final String windowTitle = "DreamLink: Explore";
    private static final Vector2ic windowSize = new Vector2i(480, 320);
    private static final int iconSpacing = 5;
    private static final Vector2ic iconDimensions = new Vector2i(16, 16);
    private static final String backButtonText = "Back";
    private static final Vector2ic buttonDimensions = new Vector2i(160, 24);
    private static final int itemSpacing = 5;

    private final IComponent component;
    private final IHomeChildComponentProvider provider;
    private final RemoteRoomComponent remoteRoomComponent;
    private final LocalRoomSelectComponent localRoomComponent;

    public HomeExploreComponent(IHomeChildComponentProvider provider) {
        this.provider = provider;
        this.remoteRoomComponent = new RemoteRoomComponent(this::onRoomSelect);
        this.localRoomComponent = new LocalRoomSelectComponent(this::onRoomSelect);

        this.component = new WindowComponent(
            MenuTextureSample.iconFolder,
            HomeExploreComponent.windowTitle,
            new BoxComponent(
                new PaddingComponent(
                    new VerticalSpanComponent(
                        Alignment.center,
                        HomeExploreComponent.itemSpacing
                    ).addComponent(
                        new TabViewComponent()
                            .addTab(
                                new HorizontalSpanComponent(
                                    Alignment.end,
                                    HomeExploreComponent.iconSpacing
                                )
                                    .addComponent(
                                        new IconComponent(
                                            new StaticIconComponentProvider(MenuTextureSample.iconDisk),
                                            HomeExploreComponent.iconDimensions
                                        )
                                    )
                                    .addComponent(
                                        new TextLineLabelComponent(
                                            "Local",
                                            FontDecoration.normal,
                                            Vector4fMaths.black
                                        )
                                    ),
                                this.localRoomComponent
                            )
                            .addTab(
                                new HorizontalSpanComponent(
                                    Alignment.end,
                                    HomeExploreComponent.iconSpacing
                                )
                                    .addComponent(
                                        new IconComponent(
                                            new StaticIconComponentProvider(MenuTextureSample.iconDish),
                                            HomeExploreComponent.iconDimensions
                                        )
                                    )
                                    .addComponent(
                                        new TextLineLabelComponent(
                                            "Remote",
                                            FontDecoration.normal,
                                            Vector4fMaths.black
                                        )
                                    ),
                                this.remoteRoomComponent
                            )
                    ).addComponent(
                        new ButtonComponent(
                            new StaticButtonComponentProvider(this.provider::gotoRoot),
                            new BoxComponent(
                                new TextLineLabelComponent(
                                    HomeExploreComponent.backButtonText,
                                    FontDecoration.normal,
                                    Vector4fMaths.black
                                ),
                                BoxDimension.fixed(HomeExploreComponent.buttonDimensions.x()),
                                BoxDimension.fixed(HomeExploreComponent.buttonDimensions.y())
                            )
                        )
                    ),
                    HomeExploreComponent.itemSpacing
                ),
                BoxDimension.min(HomeExploreComponent.windowSize.x()),
                BoxDimension.min(HomeExploreComponent.windowSize.y())
            )
        );
    }

    public void clear() {
        this.remoteRoomComponent.clear();
        this.localRoomComponent.clear();
    }

    public void onRoomSelect(String roomName) {
        SimulationState.instance.setAllowCrouch(true);
        SimulationState.instance.setAllowEdit(false);
        SimulationState.instance.setAllowOpenDoors(true);
        SimulationState.instance.setShowHiddenBlocks(false);
        SimulationState.instance.setNoClip(false);

        World.instance.setRoom(roomName);
        LoadingScene.instance.bind();
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
}
