package doors;

import doors.io.Keyboard;
import doors.io.Mouse;
import doors.io.Window;
import doors.spatial.IHasDimensions;
import doors.spatial.IHasPosition;
import doors.spatial.IHasRotation;
import doors.utility.Maths;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera implements IHasPosition, IHasRotation, IHasDimensions {

    public static Camera CAMERA = new Camera();

    private static float PITCH_LIMIT = (float)Math.toRadians(85f);
    private static float MOUSE_SENSITIVITY = 1f/900;

    public Vector3f position;
    public Vector3f rotation;
    public Vector3f velocity;

    public Camera() {
        this.position = new Vector3f();
        this.rotation = new Vector3f();
        this.velocity = new Vector3f();
    }

    private static float SPEED = 0.05f;
    private static float FRICTION = 0.7f;

    public void update() {
        var extraVelocity = new Vector3f();

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_S))
            extraVelocity.z += SPEED;

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_W))
            extraVelocity.z -= SPEED;

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_A))
            extraVelocity.x -= SPEED;

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_D))
            extraVelocity.x += SPEED;

        extraVelocity.rotateX(this.rotation.x);
        extraVelocity.rotateY(this.rotation.y);
        this.velocity.add(extraVelocity);
        this.velocity.mul(1 - FRICTION);

        Maths.zeroFuzz(this.velocity);
        this.position.add(this.velocity);

        this.rotation.y -= (Mouse.MOUSE.position.x - Window.WINDOW.dimensions.x /2f) * MOUSE_SENSITIVITY;
        this.rotation.x -= (Mouse.MOUSE.position.y - Window.WINDOW.dimensions.y /2f) * MOUSE_SENSITIVITY;
        this.rotation.x = Math.min(Math.max(this.rotation.x, - PITCH_LIMIT), PITCH_LIMIT);
    }

    @Override
    public Vector3f getRotation() {
        return this.rotation;
    }

    @Override
    public Vector3f getDimensions() {
        return Maths.VEC3F_ZERO;
    }

    @Override
    public Vector3f getPosition() {
        return this.position;
    }
}
