package periwinkle.terrain;

import periwinkle.utility.NoiseGenerator;
import org.joml.Vector2i;
import org.joml.Vector3i;

public class Generator {

    private final NoiseGenerator highFrequencyTerrainNoise = new NoiseGenerator(5);
    private final NoiseGenerator lowFrequencyTerrainNoise = new NoiseGenerator(30, 30);

    private final NoiseGenerator[] terrainGenerators = new NoiseGenerator[] {
        new NoiseGenerator(1, 40),
            new NoiseGenerator(5, 30),
            new NoiseGenerator(6, 50),
            new NoiseGenerator(16, 40),
            new NoiseGenerator(26, 40),
            new NoiseGenerator(2, 20),
            new NoiseGenerator(3, 10),
    };

    private final NoiseGenerator cavernNoise = new NoiseGenerator(12);

    public static Generator GENERATOR = new Generator();

    private float diverge(float input, float scale) {
        var exponent = (input - 0.5f) * scale;
        return 1f / ((float)Math.exp(-exponent) + 1f);
    }

    private void generateTerrain() {
        for(var x = 1; x < World.WORLD_BLOCK_DIMENSIONS.x - 1; x += 1) {
            for (var z = 1; z < World.WORLD_BLOCK_DIMENSIONS.z - 1; z += 1) {
                var sum = 0f;
                var coordinates = new Vector2i(x, z);
                for(var generator : this.terrainGenerators)
                    sum += diverge(generator.getNoise(coordinates), 50);
                var totalHeight = World.WORLD_BLOCK_DIMENSIONS.y - 2 - 20
                        + sum * 20f / this.terrainGenerators.length;
                for(var y = 1; y < totalHeight; y += 1) {
                    World.WORLD.setBlock(new Vector3i(x, y, z), BlockType.STONE);
                }
            }
        }
    }

    private void generateCaverns() {
        for(var x = 1; x < World.WORLD_BLOCK_DIMENSIONS.x - 1; x += 1) {
            for (var z = 1; z < World.WORLD_BLOCK_DIMENSIONS.z - 1; z += 1) {
                for (var y = 1; y < World.WORLD_BLOCK_DIMENSIONS.y - 2; y += 1) {
                    var position = new Vector3i(x, y, z);
                    var noise = this.cavernNoise.getNoise(position);
                    if(noise > 0.75f)
                        World.WORLD.setBlock(position, BlockType.AIR);
                }
            }
        }
    }

    public void generate() {
        this.generateTerrain();
        this.generateCaverns();
    }

}
