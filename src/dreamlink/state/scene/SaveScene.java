package dreamlink.state.scene;

import dreamlink.overlay.RoomBannerOverlay;
import dreamlink.overlay.simulation.SimulationExploreMenuOverlay;
import dreamlink.overlay.simulation.edit.SimulationEditMenuOverlay;
import dreamlink.overlay.simulation.quickbar.QuickBarOverlay;
import dreamlink.world.World;

public class SaveScene implements IScene {

    public static final SaveScene instance = new SaveScene();

    @Override
    public boolean cursorIsEnabled() {
        return true;
    }

    @Override
    public void onBind(IScene oldScene) {
        var room = World.instance.getRoom();
        room.save();
    }

    @Override
    public void update() {
        World.instance.update();

        var room = World.instance.getRoom();
        if(!room.isSaving()) {
            SimulationMenuScene.instance.bind();
        }
    }

    public void render() {
        World.instance.render();
        QuickBarOverlay.instance.render();
        SimulationEditMenuOverlay.instance.render();
        SimulationExploreMenuOverlay.instance.render();
        RoomBannerOverlay.instance.render();
    }

}
