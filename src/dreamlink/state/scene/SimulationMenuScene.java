package dreamlink.state.scene;

import dreamlink.overlay.RoomBannerOverlay;
import dreamlink.overlay.simulation.SimulationExploreMenuOverlay;
import dreamlink.overlay.simulation.edit.SimulationEditMenuOverlay;
import dreamlink.window.Window;
import dreamlink.window.button.Button;
import dreamlink.world.World;

public class SimulationMenuScene implements IScene {

    public static final SimulationMenuScene instance = new SimulationMenuScene();

    public void update() {
        SimulationEditMenuOverlay.instance.update();
        SimulationExploreMenuOverlay.instance.update();
        RoomBannerOverlay.instance.update();

        if(Window.instance.isButtonPressed(Button.keyEscape)) {
            SimulationScene.instance.bind();
        }
    }

    public void writeToSpriteBatch() {
    }

    @Override
    public boolean cursorIsEnabled() {
        return true;
    }

    @Override
    public void onBind(IScene oldScene) {
        SimulationEditMenuOverlay.instance.clear();
    }

    @Override
    public void render() {
        World.instance.render();
        SimulationEditMenuOverlay.instance.render();
        SimulationExploreMenuOverlay.instance.render();
        RoomBannerOverlay.instance.render();
    }
    
}
