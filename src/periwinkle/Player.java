package periwinkle;

import periwinkle.terrain.BlockType;
import periwinkle.io.ButtonLatch;
import periwinkle.terrain.BlockFormat;
import periwinkle.utility.CubeFace;
import periwinkle.utility.VoxelCaster;
import org.joml.*;
import org.lwjgl.glfw.GLFW;

import java.lang.Math;

public class Player {

    private static float SPEED = 0.4f;
    private static float JUMP_SPEED = 0.5f;
    private static float GRAVITY = 0.08f;
    private static float EPSILON = 0.001f;
    private static float TERMINAL_FALL_SPEED = 1f;

    public Vector3f position = new Vector3f();
    public Vector3f previousPosition = new Vector3f();

    public Vector3f velocity = new Vector3f();
    public Vector3f dimensions = new Vector3f(0.9f, 1.8f, 0.9f);
    public BlockType blockType = BlockType.MAGMA;
    public boolean isOnGround;

    private ButtonLatch leftMouseLatch = new ButtonLatch();
    private ButtonLatch rightMouseLatch = new ButtonLatch();

    private void updateVelocity() {
        var newVelocity = new Vector3f();

        if(!Game.DEBUG_CONSOLE.active) {
            if(Game.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_S))
                newVelocity.z += SPEED;

            if(Game.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_W))
                newVelocity.z -= SPEED;

            if(Game.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_A))
                newVelocity.x -= SPEED;

            if(Game.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_D))
                newVelocity.x += SPEED;
        }

        newVelocity.rotateY(Game.CAMERA.rotation.x);
        this.velocity.x = newVelocity.x;
        this.velocity.z = newVelocity.z;
        this.velocity.y -= GRAVITY;

        if(!Game.DEBUG_CONSOLE.active) {
            if(this.isOnGround && Game.KEYBOARD.isKeyDown(GLFW.GLFW_KEY_SPACE)) {
                this.velocity.y = JUMP_SPEED;
            }
        }

        this.velocity.y = Math.max(this.velocity.y, -TERMINAL_FALL_SPEED);
    }

    private void modifyBlocks() {
        var direction = Game.CAMERA.directionVector;
        var caster = new VoxelCaster();
        caster.castRay(Game.CAMERA.position, direction);

        if(Game.MOUSE.isRightMouseButtonPressed(this.rightMouseLatch)) {
            for(var ix =0; ix < 40; ix += 1) {
                caster.advance();
                if(Game.WORLD.getBlock(caster.cursor).blockType.blockFormat != BlockFormat.VOID) {
                    Game.WORLD.setBlock(caster.cursor, BlockType.AIR);
                    break;
                }
            }
            return;
        }

        if(Game.MOUSE.isLeftMouseButtonPressed(this.leftMouseLatch)) {
            Vector3i previous = new Vector3i(caster.cursor);
            for(var ix =0; ix < 40; ix += 1) {
                caster.advance();
                if(Game.WORLD.getBlock(caster.cursor).blockType.blockFormat != BlockFormat.VOID) {
                    Game.WORLD.setBlock(previous, this.blockType);
                    break;
                }
                previous.set(caster.cursor);
            }
        }
    }

    private void updatePosition() {
        this.isOnGround = false;
        for(var cubeFace : CubeFace.CUBE_FACES) {
            var projection = new Vector3f(cubeFace.normal).dot(this.velocity);

            // We are not moving in this direction, we don't have to worry about collisions.
            if(projection <= 0)
                continue;

            var step = new Vector3f(cubeFace.normal).mul(EPSILON);
            for(var amt = 0f; amt <= projection; amt += EPSILON) {
                var trial = new Vector3f(this.position).add(step);
                if(Game.WORLD.terrainCollision(trial, this.dimensions)) {
                    this.velocity.sub(new Vector3f(cubeFace.normal).mul(projection));
                    if(cubeFace == CubeFace.BOTTOM)
                        this.isOnGround = true;
                    break;
                }
                this.position.set(trial);
            }
        }
    }

    public void simulate() {
        this.previousPosition.set(this.position);

        this.updateVelocity();
        this.updatePosition();
        this.modifyBlocks();
    }
}
