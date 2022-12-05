package dreamlink.state.scene;

import dreamlink.overlay.PublishOverlay;

public class PublishScene implements IScene {

    public static final PublishScene instance = new PublishScene();

    @Override
    public boolean cursorIsEnabled() {
        return true;
    }

    @Override
    public void onBind(IScene oldScene) {
        PublishOverlay.instance.publish();
    }

    @Override
    public void update() {
        PublishOverlay.instance.update();
    }

    @Override
    public void render() {
        PublishOverlay.instance.render();
    }

}
