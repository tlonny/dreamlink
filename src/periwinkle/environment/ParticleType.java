package periwinkle.environment;

import periwinkle.graphics.Atlas;
import periwinkle.graphics.ITextureLookup;
import org.joml.Vector2f;
import org.joml.Vector2i;

public class ParticleType implements ITextureLookup {

    private static Vector2i PARTICLE_DIMENSIONS = new Vector2i(16, 16);
    private final Vector2f[] textureOffsets;

    public ParticleType(Vector2f[] textureOffsets) {
        this.textureOffsets = textureOffsets;
    }

    public static ParticleType RAIN = new ParticleType(Atlas.PARTICLE_ATLAS.buildTextureOffsets(new Vector2i(0 ,0), PARTICLE_DIMENSIONS));

    @Override
    public Vector2f[] getTextureOffsets() {
        return this.textureOffsets;
    }
}
