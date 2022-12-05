package periwinkle.utility;

import org.joml.Vector2i;
import org.joml.Vector3i;
import org.spongepowered.noise.Noise;
import org.spongepowered.noise.NoiseQuality;

import java.util.Random;

public class NoiseGenerator {

    private final int seed;
    private final float scale;

    public NoiseGenerator(int seed, int scale) {
        this.seed = seed;
        this.scale = scale;
    }

    public NoiseGenerator(int scale) {
        this(new Random().nextInt(), scale);
    }

    public float getNoise(Vector2i position) {
        return this.getNoise(new Vector3i(position, 0));
    }

    public float getNoise(Vector3i position) {
        return (float) Noise.valueCoherentNoise3D(
                (float)position.x / this.scale,
                (float)position.y / this.scale,
                (float)position.z / this.scale,
                this.seed,
                NoiseQuality.FAST
        );
    }
}
