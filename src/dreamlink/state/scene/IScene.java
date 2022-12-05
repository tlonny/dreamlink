package dreamlink.state.scene;

public interface IScene {

    public boolean cursorIsEnabled();

    public void onBind(IScene oldScene);

    public default void bind() {
        SceneManager.instance.setScene(this);
    }

    public void update();

    public void render();

}
