package doors;

import doors.io.Keyboard;
import doors.io.Mouse;
import doors.component.IHasDimensions;
import doors.component.IHasPosition;
import doors.component.IHasRotation;
import doors.gamestate.CameraGameState;
import doors.terrain.Door;
import doors.utility.Maths;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera implements IHasPosition, IHasRotation, IHasDimensions {

    public static Camera CAMERA = new Camera();

    private static float PITCH_LIMIT = (float)Math.toRadians(85f);
    private static float MOUSE_SENSITIVITY = 0.9f;

    public Vector3f position;
    public Vector3f rotation;
    public Vector3f velocity;

    public Camera() {
        this.position = new Vector3f();
        this.rotation = new Vector3f();
        this.velocity = new Vector3f();
    }

    private static float SPEED = 0.15f;
    private static float FRICTION = 0.7f;

    public void update() {
        if(!CameraGameState.CAMERA_GAME_STATE.isUsed()) {
            return;
        }

        Maths.zeroFuzz(this.velocity);
        this.position.add(this.velocity);

        var extraVelocity = new Vector3f();

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

        for(var door : Game.GAME.currentTerrain.doors.values()) {
            var distance = door.position.distance(this.position);
            if(distance < distanceToTarget) {
                target = door;
                distanceToTarget = distance;
            }
        }

        target.open();
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
