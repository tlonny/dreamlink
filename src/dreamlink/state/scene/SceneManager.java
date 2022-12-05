package dreamlink.state.scene;

import dreamlink.state.ISynchronizedState;
import dreamlink.state.SynchronizedStateManager;

public class SceneManager implements ISynchronizedState {

    public static final SceneManager instance = new SceneManager();

    private IScene nextScene;
    private IScene currentScene;

    public void setScene(IScene scene) {
        this.nextScene = scene;
    }

    public IScene getScene() {
        return this.currentScene;
    }

    public void setup() {
        SynchronizedStateManager.instance.addState(this);
        this.currentScene = LogoScene.instance;
    }

    @Override
    public void advanceState() {
        if(this.nextScene == null) {
            return;
        }

        var oldScene = this.currentScene;
        this.currentScene = this.nextScene;
        this.nextScene = null;
        this.currentScene.onBind(oldScene);
    }
    
}
