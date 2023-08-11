package doors.level.camera;

import org.lwjgl.glfw.GLFW;

import doors.io.Keyboard;
import doors.level.ILevelProvider;
import doors.level.block.BlockData;
import doors.utility.CubeFace;
import doors.utility.vector.Vector3fl;
import doors.utility.vector.Vector3in;

public class PlayerMovementSystem implements ICameraMovementSystem {

    private static Vector3fl COLLIDER_DIMENSIONS = new Vector3fl(0.9f, 1.4f, 0.9f);
    private static Vector3fl CAMERA_ORIGIN = new Vector3fl(0.45f, 1.2f, 0.45f);
    private static float STEP_SIZE = 0.001f;
    private static float SPEED = 0.15f;
    private static float FRICTION = 0.6f;
    private static float JUMP_SPEED = 0.8f;
    private static float GRAVITY = 0.01f;
    private static float TERMINAL_FALL_SPEED = 1f;

    private Vector3fl extraVelocity = new Vector3fl();
    private Vector3fl collisionPosition = new Vector3fl();

    private boolean isOnGround = false;
    private BlockData blockDataBuffer = new BlockData();
    private ILevelProvider levelProvider;

    public PlayerMovementSystem(ILevelProvider terrainProvider) {
        this.levelProvider = terrainProvider;
    }

    private boolean isCollisionWithTerrain(Vector3fl position) {
        this.collisionPosition.set(position).sub(CAMERA_ORIGIN);
        var startX = (int)(this.collisionPosition.x);
        var startY = (int)(this.collisionPosition.y);
        var startZ = (int)(this.collisionPosition.z);

        var endX = (int)(this.collisionPosition.x + COLLIDER_DIMENSIONS.x);
        var endY = (int)(this.collisionPosition.y + COLLIDER_DIMENSIONS.y);
        var endZ = (int)(this.collisionPosition.z + COLLIDER_DIMENSIONS.z);

        var terrainData = this.levelProvider.getLevel().terrainData;
        var blockPosition = new Vector3in();
        for(var x = startX; x <= endX; x += 1) {
            for(var y = startY; y <= endY; y += 1) {
                for(var z = startZ; z <= endZ; z += 1) {
                    terrainData.getBlockData(blockPosition.set(x, y, z), this.blockDataBuffer);
                    if(this.blockDataBuffer.block != null && this.blockDataBuffer.block.isSolid()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void update(Camera camera) {
        camera.velocity.zeroFuzz();
        this.extraVelocity.set(0);

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_W))
            this.extraVelocity.add(CubeFace.FRONT.normal);

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_S))
            this.extraVelocity.add(CubeFace.BACK.normal);

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_D))
            this.extraVelocity.add(CubeFace.RIGHT.normal);

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_A))
            this.extraVelocity.add(CubeFace.LEFT.normal);

        if(Keyboard.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
            if(this.isOnGround) {
                this.extraVelocity.y += JUMP_SPEED;
            }
        }

        this.extraVelocity.mul(SPEED);
        this.extraVelocity.rotateY(camera.rotation.y);

        camera.velocity.add(extraVelocity);
        camera.velocity.y -= GRAVITY;
        camera.velocity.x *= 1 - FRICTION;
        camera.velocity.z *= 1 - FRICTION;
        camera.velocity.y = Math.max(camera.velocity.y, -TERMINAL_FALL_SPEED);

        this.isOnGround = false;

        for(var cubeFace : CubeFace.CUBE_FACES) {
            var projection = new Vector3fl(cubeFace.normal).getDot(camera.velocity);

            if(projection <= 0) {
                continue;
            }

            var projectionVector = new Vector3fl(cubeFace.normal).mul(projection);
            var stepVector = new Vector3fl(cubeFace.normal).mul(STEP_SIZE);
            var trialVector = new Vector3fl();

            for(var stepSum = 0f; stepSum <= projection; stepSum += STEP_SIZE) {
                trialVector.set(camera.position).add(stepVector);
                if(this.isCollisionWithTerrain(trialVector)) {
                    camera.velocity.sub(projectionVector);
                    if(cubeFace == CubeFace.BOTTOM) {
                        this.isOnGround = true;
                    }
                    break;
                }
                camera.position.set(trialVector);
            }
        }
    }


}
