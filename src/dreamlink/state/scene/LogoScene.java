package dreamlink.state.scene;

import dreamlink.overlay.LogoOverlay;

public class LogoScene implements IScene {

    public static final LogoScene instance = new LogoScene();

    @Override
    public void onBind(IScene oldScene) {
        LogoOverlay.instance.reset();
    }

    public void update() {
        LogoOverlay.instance.update();
    }

    @Override
    public boolean cursorIsEnabled() {
        return false;
    }

    @Override
    public void render() {
        LogoOverlay.instance.render();
    }
    
}
