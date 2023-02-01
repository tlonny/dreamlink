package periwinkle.terrain;

import periwinkle.Game;
import periwinkle.utility.Maths;
import periwinkle.utility.NoiseGenerator;

import java.util.Random;

import org.joml.Vector3f;
import org.joml.Vector3i;

public class Generator {

    private static int EDGE_NOISE_SCALE = 6;
    private static int CAVERN_NOISE_SCALE = 12;
    private static int SNAKE_NOISE_SCALE = 16;

    private static int NUM_SNAKES = 50;
    private static int SNAKE_STEP_SIZE = 3;
    private static int SNAKE_MIN_STEPS = 50;
    private static int SNAKE_MAX_STEPS = 300;
    private static float SNAKE_MIN_STEP_ROTATE = (float)Math.PI * 0.25f;
    private static float SNAKE_MAX_STEP_ROTATE = (float)Math.PI * 0.50f;

    private NoiseGenerator edgeNoiseGenerator = new NoiseGenerator(EDGE_NOISE_SCALE);
    private NoiseGenerator cavernNoiseGenerator = new NoiseGenerator(CAVERN_NOISE_SCALE);
    private NoiseGenerator snakeNoise = new NoiseGenerator(SNAKE_NOISE_SCALE);

    private Random random = new Random();

    private float edgeFactor(Vector3i position, int sharpeningIterations) {
        var buffer = new Vector3f();
        buffer.set(
            (float)position.x / World.WORLD_BLOCK_DIMENSIONS.x,
            (float)position.y / World.WORLD_BLOCK_DIMENSIONS.y,
            (float)position.z / World.WORLD_BLOCK_DIMENSIONS.z
        );
        for(var ix = 0; ix < sharpeningIterations; ix += 1) {
            buffer.mul(buffer);
        }
        buffer.mul(-1f).add(1f, 1f, 1f);
        var positiveEdgeFactor = Maths.min(buffer);

        buffer.set(
            1f - (float)position.x / World.WORLD_BLOCK_DIMENSIONS.x,
            1f - (float)position.y / World.WORLD_BLOCK_DIMENSIONS.y,
            1f - (float)position.z / World.WORLD_BLOCK_DIMENSIONS.z
        );
        for(var ix = 0; ix < sharpeningIterations; ix += 1) {
            buffer.mul(buffer);
        }
        buffer.mul(-1f).add(1f, 1f, 1f);
        var negativeEdgeFactor = Maths.min(buffer);
        return Math.max(0f, Math.min(positiveEdgeFactor, negativeEdgeFactor));
    }

    private void smoothEdges(Vector3i position) {
        var edgeNoise = this.edgeNoiseGenerator.getNoise(position);
        var edgeSmoothing = this.edgeFactor(position, 3) + edgeNoise * 0.4f;
        if (edgeSmoothing <= 0.4f) {
            Game.WORLD.setBlock(position, BlockType.AIR);
        }
    }

    private void generatePerlinCaverns(Vector3i position) {

        var cavernNoise = this.cavernNoiseGenerator.getNoise(position);
        var cavern = cavernNoise * this.edgeFactor(position, 3);

        if (cavern > 0.65f) {
            Game.WORLD.setBlock(position, BlockType.AIR);
        }
    }

    private void generateSnakedPassage() {
        var position = new Vector3f(
            this.random.nextFloat() * World.WORLD_BLOCK_DIMENSIONS.x,
            this.random.nextFloat() * World.WORLD_BLOCK_DIMENSIONS.y,
            this.random.nextFloat() * World.WORLD_BLOCK_DIMENSIONS.z
        );
        var blockPosition = new Vector3i();
        var carveBuffer = new Vector3i();
        var steps = this.random.nextInt(SNAKE_MIN_STEPS, SNAKE_MAX_STEPS);
        var step = new Vector3f();

        var twistFactor = this.random.nextFloat(SNAKE_MIN_STEP_ROTATE, SNAKE_MAX_STEP_ROTATE);

        step.set(SNAKE_STEP_SIZE,0,0);
        step.rotateZ(this.random.nextFloat() * 2 * (float)Math.PI);
        step.rotateY(this.random.nextFloat() * 2 * (float)Math.PI);

        for(var ix = 0; ix < steps; ix += 1) {
            blockPosition.set((int)position.x, (int)position.y, (int)position.z);
            for(var x = -2; x <= 2; x += 1) {
                for(var y = -2; y <= 2; y += 1) {
                    for(var z = -2; z <= 2; z += 1) {
                        if(Math.abs(x) + Math.abs(y) + Math.abs(z) <= 5) {
                            carveBuffer.set(blockPosition).add(x,y,z);
                            Game.WORLD.setBlock(carveBuffer, BlockType.AIR);
                        }
                    }
                }
            }

            step.rotateZ((this.snakeNoise.getNoise(blockPosition) * 2f - 1f) * twistFactor);
            blockPosition.add(World.WORLD_BLOCK_DIMENSIONS);
            step.rotateY((this.snakeNoise.getNoise(blockPosition) * 2f - 1f) * twistFactor);
            position.add(step);
        }
    }

    public void generate() {
        this.random.setSeed(System.currentTimeMillis());
        var positionBuffer = new Vector3i();
        for(var x = 1; x < World.WORLD_BLOCK_DIMENSIONS.x - 1; x += 1) {
            for (var z = 1; z < World.WORLD_BLOCK_DIMENSIONS.z - 1; z += 1) {
                for (var y = 1; y < World.WORLD_BLOCK_DIMENSIONS.y - 2; y += 1) {
                    positionBuffer.set(x,y,z);
                    Game.WORLD.setBlock(positionBuffer, BlockType.MUD);
                    this.smoothEdges(positionBuffer);
                    this.generatePerlinCaverns(positionBuffer);
                }
            }
        }
        for(var ix = 0; ix < NUM_SNAKES; ix += 1) {
            this.generateSnakedPassage();
        }
    }

}
