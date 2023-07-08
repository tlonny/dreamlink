package doors.entity;

import doors.core.config.Config;
import doors.core.io.Keyboard;
import doors.core.io.Mouse;
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

        // Camera is facing INTO the screen, therefore forward
        // motion corresponds to a DECREASE in the z coordinate.
        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_W))
            extraVelocity.z -= SPEED;

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_S))
            extraVelocity.z += SPEED;

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_D))
            extraVelocity.x += SPEED;

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_A))
            extraVelocity.x -= SPEED;

        extraVelocity.rotateX(this.rotation.x);
        extraVelocity.rotateY(this.rotation.y);
        this.velocity.mul(1 - FRICTION);
        this.velocity.add(extraVelocity);

        this.mouseDelta
            .set(Mouse.MOUSE.position)
            .div(Config.CONFIG.getResolution())
            .sub(0.5f)
            .mul(MOUSE_SENSITIVITY)
            // In our 2D coordinate system, y = 0 is at the TOP of the screen.
            // In our 3D coordinate system, lower values of Y are at the BOTTOM of the world.
            // thus we should invert the y delta value to facilitate this difference.
            .mul(1f, -1f);

        // Generally, if our mouse moves right, we want to turn in a clockwise direction.
        // Using the right hand rule, if we stick our thumb up vertically (positive Y axis direction)
        // and notice your fingers curl in an anti-clockwise direction. Thus, we need to scale by -1f.
        this.rotation.y -= this.mouseDelta.x;

        // Generally, if our mouse moves up, we want to look up towards the sky. 
        // Using the right hand rule, stick your thumb to the right and notice your fingers curl up
        // towards the screen (negative Z) - which is the direction of forward motion. Thus no scaling is
        // required!
        this.rotation.x += this.mouseDelta.y;
        this.rotation.x = Math.min(Math.max(this.rotation.x, - PITCH_LIMIT), PITCH_LIMIT);
    }

}
