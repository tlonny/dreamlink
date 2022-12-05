package dreamlink.state.scene;

import dreamlink.overlay.ReticuleOverlay;
import dreamlink.overlay.RoomBannerOverlay;
import dreamlink.overlay.simulation.quickbar.QuickBarOverlay;
import dreamlink.player.Player;
import dreamlink.window.Window;
import dreamlink.window.button.Button;
import dreamlink.world.World;

public class SimulationScene implements IScene {

    private static final String spawnDoor = "main";

    public static final SimulationScene instance = new SimulationScene();

    @Override
    public void bind() {
        SceneManager.instance.setScene(this);
    }

    @Override
    public void onBind(IScene oldScene) {
        if(oldScene == LoadingScene.instance) {
            Player.instance.spawn(SimulationScene.spawnDoor);
            RoomBannerOverlay.instance.reset();
        }
    }

    @Override
    public boolean cursorIsEnabled() {
        return false;
    }

    @Override
    public void update() {
        RoomBannerOverlay.instance.update();
        Player.instance.update();
        World.instance.update();
        QuickBarOverlay.instance.update();

        if(Window.instance.isButtonPressed(Button.keyEscape)) {
            SimulationMenuScene.instance.bind();
        }

    }

    @Override
    public void render() {
        World.instance.render();
        ReticuleOverlay.instance.render();
        RoomBannerOverlay.instance.render();
        QuickBarOverlay.instance.render();
    }
}
