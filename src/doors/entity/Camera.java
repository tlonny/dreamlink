package doors.entity;

import doors.core.config.Config;
import doors.core.io.Keyboard;
import doors.core.io.Mouse;
import doors.core.utility.CubeFace;
import doors.core.utility.vector.Vector2fl;
import doors.core.utility.vector.Vector3fl;

import org.lwjgl.glfw.GLFW;

public class Camera {

    public static Camera CAMERA = new Camera();

    private static float PITCH_LIMIT = (float)Math.toRadians(85f);
    private static float MOUSE_SENSITIVITY = 0.9f;

    public final Vector3fl position;
    public final Vector3fl rotation;
    public final Vector3fl velocity;

    public Vector2fl mouseDelta;

    public Camera() {
        this.position = new Vector3fl();
        this.rotation = new Vector3fl();
        this.velocity = new Vector3fl();
        this.mouseDelta = new Vector2fl();
    }

    private static float SPEED = 0.04f;
    private static float FRICTION = 0.6f;

    public void update() {
        this.velocity.zeroFuzz();
        this.position.add(this.velocity);

        var extraVelocity = new Vector3fl();

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_W))
            extraVelocity.add(CubeFace.FRONT.normal);

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_S))
            extraVelocity.add(CubeFace.BACK.normal);

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_D))
            extraVelocity.add(CubeFace.RIGHT.normal);

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_A))
            extraVelocity.add(CubeFace.LEFT.normal);

        extraVelocity.mul(SPEED);
        extraVelocity.rotateX(this.rotation.x);
        extraVelocity.rotateY(this.rotation.y);
        this.velocity.mul(1 - FRICTION);
        this.velocity.add(extraVelocity);

        this.mouseDelta
            .set(Mouse.MOUSE.position)
            .div(Config.CONFIG.getResolution())
            .sub(0.5f)
            .mul(MOUSE_SENSITIVITY)
            // in 2D screen space, left and up are negative, right and down are positive.
            // in 3D world space, left and up are positive, right and down are negative. 
            // Thus both axes need to be scaled by -1f.
            .mul(-1f, -1f);

        // Using the right hand rule, stick your thumb up (+ve Y) and notice your fingers curl left.
        // In our coordinate system, left is positive, so we ADD the mouse delta.
        this.rotation.y += this.mouseDelta.x;

        // Using the right hand rule, stick your thumb left (+ve X) and notice your fingers curl down.
        // In our coordinate system, down is negative, so we SUBTRACT the mouse delta.
        this.rotation.x -= this.mouseDelta.y;

        this.rotation.x = Math.min(Math.max(this.rotation.x, - PITCH_LIMIT), PITCH_LIMIT);

        this.rotation.y %= 2 * Math.PI;
        if(this.rotation.y < 0) {
            this.rotation.y += 2 * Math.PI;
        }
    }

}
