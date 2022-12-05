package periwinkle;

import periwinkle.overlay.DebugConsole;
import periwinkle.terrain.BlockType;
import periwinkle.terrain.BlockFormat;
import periwinkle.terrain.World;
import periwinkle.utility.CubeFace;
import periwinkle.utility.VoxelCaster;
import org.joml.*;
import org.lwjgl.glfw.GLFW;

import java.lang.Math;

public class Player {

    private static float SPEED = 0.1f;
    private static float JUMP_SPEED = 0.5f;
    private static float GRAVITY = 0.02f;
    private static float EPSILON = 0.001f;
    private static float TERMINAL_FALL_SPEED = 1f;

    public static Player PLAYER = new Player();

    public Vector3f position = new Vector3f();
    public Vector3f velocity = new Vector3f();
    public Vector3f dimensions = new Vector3f(0.9f, 1.8f, 0.9f);
    public BlockType blockType = BlockType.MAGMA;
    public boolean isOnGround;

    private void updateVelocity() {
        if(DebugConsole.DEBUG_CONSOLE.active)
            return;

        var newVelocity = new Vector3f();

        if(Input.INPUT.isKeyDown(GLFW.GLFW_KEY_S))
            newVelocity.z += SPEED;

        if(Input.INPUT.isKeyDown(GLFW.GLFW_KEY_W))
            newVelocity.z -= SPEED;

        if(Input.INPUT.isKeyDown(GLFW.GLFW_KEY_A))
            newVelocity.x -= SPEED;

        if(Input.INPUT.isKeyDown(GLFW.GLFW_KEY_D))
            newVelocity.x += SPEED;

        newVelocity.rotateY(Camera.CAMERA.rotation.x);
        this.velocity.x = newVelocity.x;
        this.velocity.z = newVelocity.z;

        if(!this.isOnGround)
            this.velocity.y -= GRAVITY;
        else if(Input.INPUT.isKeyDown(GLFW.GLFW_KEY_SPACE))
            this.velocity.y = JUMP_SPEED;
        this.velocity.y = Math.max(this.velocity.y, -TERMINAL_FALL_SPEED);
    }

    private void modifyBlocks() {
        var direction = Camera.CAMERA.getDirectionVector();
        var caster = new VoxelCaster();
        caster.castRay(Camera.CAMERA.position, direction);

        if(Input.INPUT.isRightMousePressed()) {
            for(var ix =0; ix < 40; ix += 1) {
                caster.advance();
                if(World.WORLD.getBlock(caster.cursor).blockType.blockFormat == BlockFormat.SOLID) {
                    World.WORLD.setBlock(caster.cursor, BlockType.AIR);
                    break;
                }
            }
            return;
        }

        if(Input.INPUT.isLeftMousePressed()) {
            Vector3i previous = new Vector3i(caster.cursor);
            for(var ix =0; ix < 40; ix += 1) {
                caster.advance();
                if(World.WORLD.getBlock(caster.cursor).blockType.blockFormat == BlockFormat.SOLID) {
                    World.WORLD.setBlock(previous, this.blockType);
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
                if(World.WORLD.terrainCollision(trial, this.dimensions)) {
                    this.velocity.sub(new Vector3f(cubeFace.normal).mul(projection));
                    if(cubeFace == CubeFace.BOTTOM)
                        this.isOnGround = true;
                    break;
                }
                this.position.set(trial);
            }
        }
    }

    public void update() {
        this.updateVelocity();
        this.modifyBlocks();
        this.updatePosition();
    }
}
