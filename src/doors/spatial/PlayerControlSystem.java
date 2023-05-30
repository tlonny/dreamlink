package doors.spatial;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import doors.Game;
import doors.utility.CubeFace;

public class PlayerControlSystem {

    private static float SPEED = 1f;
    private static float JUMP_SPEED = 0.8f;
    private static float GRAVITY = 0.1f;
    private static float EPSILON = 0.001f;
    private static float TERMINAL_FALL_SPEED = 1f;
    private static float FRICTION = 0.7f;

    public boolean noClip = true;
    public boolean active = true;

    private boolean isOnGround;
    private Vector3f velocityBuffer = new Vector3f();

    private void updateVelocity(SpatialComponent component) {
        this.velocityBuffer.zero();

        if(this.active) {
            if(Game.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_S))
                this.velocityBuffer.z += SPEED;

            if(Game.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_W))
                this.velocityBuffer.z -= SPEED;

            if(Game.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_A))
                this.velocityBuffer.x -= SPEED;

            if(Game.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_D))
                this.velocityBuffer.x += SPEED;

            if(Game.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_SPACE) && this.isOnGround && !this.noClip)
                this.velocityBuffer.y += JUMP_SPEED;
        }

        if(this.noClip) {
            this.velocityBuffer.rotateX(Game.CAMERA.rotation.y);
            this.velocityBuffer.rotateY(Game.CAMERA.rotation.x);
            component.velocity.add(this.velocityBuffer); 
            component.velocity.y *= (1 - FRICTION);
        } else {
            this.velocityBuffer.rotateY(Game.CAMERA.rotation.x);
            component.velocity.add(this.velocityBuffer);
            component.velocity.y -= GRAVITY;
            component.velocity.y = Math.max(component.velocity.y, -TERMINAL_FALL_SPEED);
        }
        component.velocity.x *= (1 - FRICTION);
        component.velocity.z *= (1 - FRICTION);

        if(Math.abs(component.velocity.x) < EPSILON) {
            component.velocity.x = 0;
        }
        if(Math.abs(component.velocity.y) < EPSILON) {
            component.velocity.y = 0;
        }
        if(Math.abs(component.velocity.z) < EPSILON) {
            component.velocity.z = 0;
        }
    }

    private void updatePosition(SpatialComponent component) {
        if(this.noClip) {
            component.position.add(component.velocity);
            return;
        }

        this.isOnGround = false;
        for(var cubeFace : CubeFace.CUBE_FACES) {
            var projection = new Vector3f(cubeFace.normal).dot(component.velocity);

            // We are not moving in this direction, we don't have to worry about collisions.
            if(projection <= 0)
                continue;

            var step = new Vector3f(cubeFace.normal).mul(EPSILON);
            for(var amt = 0f; amt <= projection; amt += EPSILON) {
                var trial = new Vector3f(component.position).add(step);
                if(component.terrain.isCollision(trial, component.dimensions)) {
                    component.velocity.sub(new Vector3f(cubeFace.normal).mul(projection));
                    if(cubeFace == CubeFace.BOTTOM)
                        this.isOnGround = true;
                    break;
                }
                component.position.set(trial);
            }
        }
    }

    public void simulate(SpatialComponent component) {
        this.updateVelocity(component);
        this.updatePosition(component);
    }

}
