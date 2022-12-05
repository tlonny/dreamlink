package dreamlink.state.scene;

import dreamlink.overlay.RoomLoadOverlay;
import dreamlink.world.World;

public class LoadingScene implements IScene {

    public static final LoadingScene instance = new LoadingScene();

    @Override
    public boolean cursorIsEnabled() {
        return false;
    }

    @Override
    public void onBind(IScene oldScene) {
        RoomLoadOverlay.instance.reset();
        World.instance.getRoom().load();
    }

    @Override
    public void update() {
        RoomLoadOverlay.instance.update();
        World.instance.update();
    }

    @Override
    public void render() {
        RoomLoadOverlay.instance.render();
    }
}
