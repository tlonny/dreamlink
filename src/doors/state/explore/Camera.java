package doors.state.explore;

import doors.Config;
import doors.core.io.Keyboard;
import doors.core.io.Mouse;
import doors.level.Door;
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

    private Vector2fl mouseDelta;

    public Camera() {
        this.position = new Vector3fl();
        this.rotation = new Vector3fl();
        this.velocity = new Vector3fl();
        this.mouseDelta = new Vector2fl();
    }

    private static float SPEED = 0.2f;
    private static float FRICTION = 0.6f;

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

        this.mouseDelta
            .set(Mouse.MOUSE.position)
            .div(Config.RESOLUTION)
            .sub(0.5f)
            .mul(-MOUSE_SENSITIVITY);

        this.rotation.y += this.mouseDelta.x;
        this.rotation.x += this.mouseDelta.y;
        this.rotation.x = Math.min(Math.max(this.rotation.x, - PITCH_LIMIT), PITCH_LIMIT);
    }

    public void openDoor() {
        Door target = null;
        float distanceToTarget = Float.MAX_VALUE;

        for(var door : ExploreGameState.EXPLORE_GAME_STATE.currentLevel.doors.values()) {
            var distance = door.position.getDistance(this.position);
            if(distance < distanceToTarget && ExploreGameState.EXPLORE_GAME_STATE.openDoor != door) {
                target = door;
                distanceToTarget = distance;
            }
        }

        if(target == null)
            return;

        ExploreGameState.EXPLORE_GAME_STATE.openDoor(target);
    }
}
