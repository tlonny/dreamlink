package dreamlink.window;

import org.joml.Vector2f;
import org.joml.Vector2i;

import dreamlink.state.scene.SceneManager;
import dreamlink.utility.maths.Vector2fMaths;
import dreamlink.utility.maths.Vector2iMaths;

public class WindowMouseModule {

    private final Vector2i position;
    private final Vector2f deltaPosition;
    private final IWindowModuleProvider provider;
    private boolean isCentered;

    public WindowMouseModule(IWindowModuleProvider provider) {
        this.provider = provider;
        this.position = new Vector2i();
        this.deltaPosition = new Vector2f();
    }

    private Vector2i fetchCenterPosition = new Vector2i();

    public Vector2i getMousePosition(Vector2i target) {
        return target.set(this.position);
    }

    public Vector2f getDeltaPosition(Vector2f target) {
        return target.set(this.deltaPosition);
    }

    private Vector2i centerMouseResolution = new Vector2i();

    public void centerMouse() {
        var GLFWModule = this.provider.getGLFWModule();

        var resolution = GLFWModule.getResolution(this.centerMouseResolution);
        var centerPosition = this.fetchCenterPosition.set(resolution);
        Vector2iMaths.div(centerPosition, 2);

        GLFWModule.setMousePosition(centerPosition);
        if(this.isCentered) {
            this.deltaPosition.set(this.position);
            Vector2fMaths.div(this.deltaPosition, resolution);
            this.deltaPosition.sub(0.5f, 0.5f);
        }
        this.isCentered = true;

    }

    public void fetch() {
        var GLFWModule = this.provider.getGLFWModule();
        GLFWModule.getMousePosition(this.position);

        var scene = SceneManager.instance.getScene();
        if(scene.cursorIsEnabled()) {
            this.deltaPosition.set(0f);
            this.isCentered = false;
        } else {
            this.centerMouse();
        }
    }

}
