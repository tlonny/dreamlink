package doors.level.camera;

import org.lwjgl.glfw.GLFW;

import doors.core.io.Keyboard;
import doors.utility.CubeFace;
import doors.utility.vector.Vector3fl;

public class NoClipMovementSystem implements ICameraMovementSystem {

    private static float SPEED = 0.04f;
    private static float FRICTION = 0.6f;

    private Vector3fl extraVelocity = new Vector3fl();

    @Override
    public void update(Camera camera) {
        camera.velocity.zeroFuzz();
        camera.position.add(camera.velocity);

        this.extraVelocity.set(0);

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_W))
            this.extraVelocity.add(CubeFace.FRONT.normal);

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_S))
            this.extraVelocity.add(CubeFace.BACK.normal);

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_D))
            this.extraVelocity.add(CubeFace.RIGHT.normal);

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_A))
            this.extraVelocity.add(CubeFace.LEFT.normal);

        this.extraVelocity.mul(SPEED);
        this.extraVelocity.rotateX(camera.rotation.x);
        this.extraVelocity.rotateY(camera.rotation.y);
        camera.velocity.mul(1 - FRICTION);
        camera.velocity.add(extraVelocity);

    }


}
