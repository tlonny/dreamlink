package dreamlink.state.scene;

import dreamlink.overlay.home.HomeOverlay;
import dreamlink.world.World;

public class HomeScene implements IScene {

    public static final HomeScene instance = new HomeScene();

    @Override
    public void bind() {
        SceneManager.instance.setScene(this);
    }

    @Override
    public void onBind(IScene oldScene) {
        World.instance.clear();
        HomeOverlay.instance.reset();
    }

    @Override
    public boolean cursorIsEnabled() {
        return true;
    }

    @Override
    public void update() {
        HomeOverlay.instance.update();
    }

    @Override
    public void render() {
        HomeOverlay.instance.render();
    }
    
}
