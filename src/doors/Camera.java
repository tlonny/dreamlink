package doors;

import doors.io.Keyboard;
import doors.io.Mouse;
import doors.level.Door;
import doors.level.Terrain;
import doors.utility.geometry.Vector3fl;

import org.lwjgl.glfw.GLFW;

public class Camera {

    public static Camera CAMERA = new Camera();

    private static float PITCH_LIMIT = (float)Math.toRadians(85f);
    private static float MOUSE_SENSITIVITY = 0.9f;

    public Vector3fl position;
    public Vector3fl rotation;
    public Vector3fl velocity;

    public Camera() {
        this.position = new Vector3fl();
        this.rotation = new Vector3fl();
        this.velocity = new Vector3fl();
    }

    private static float SPEED = 0.15f;
    private static float FRICTION = 0.7f;

    public void setup() {
    }

    public void update() {
        this.velocity.zeroFuzz();
        this.position.add(this.velocity);

        var extraVelocity = new Vector3fl();

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_S))
            extraVelocity.z += SPEED;

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_W))
            extraVelocity.z -= SPEED;

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_A))
            extraVelocity.x -= SPEED;

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_D))
            extraVelocity.x += SPEED;

        if(Keyboard.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_E)) {
            this.openDoor();
        }

        extraVelocity.rotateX(this.rotation.x);
        extraVelocity.rotateY(this.rotation.y);
        this.velocity.add(extraVelocity);
        this.velocity.mul(1 - FRICTION);

        var centerX = (float)Mouse.MOUSE.position.x / Config.RESOLUTION.x - 0.5f;
        var centerY = (float)Mouse.MOUSE.position.y / Config.RESOLUTION.y - 0.5f;

        this.rotation.y -= centerX * MOUSE_SENSITIVITY;
        this.rotation.x -= centerY * MOUSE_SENSITIVITY;
        this.rotation.x = Math.min(Math.max(this.rotation.x, - PITCH_LIMIT), PITCH_LIMIT);
    }

    public void openDoor() {
        Door target = null;
        float distanceToTarget = Float.MAX_VALUE;

        for(var door : Terrain.CURRENT_TERRAIN.doors.values()) {
            var distance = door.position.distance(this.position);
            if(distance < distanceToTarget) {
                target = door;
                distanceToTarget = distance;
            }
        }

        target.open();
    }
}
