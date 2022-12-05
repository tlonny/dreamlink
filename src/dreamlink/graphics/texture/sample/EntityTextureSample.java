package dreamlink.graphics.texture.sample;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.texture.TextureUnit;

public class EntityTextureSample extends TextureSample {

    private static final Vector2ic atlasDimensions = new Vector2i(512, 544);

    public static final TextureSample missing = new EntityTextureSample(new Vector2i(0, 0), new Vector2i(32, 32));
    public static final TextureSample placeholder = new EntityTextureSample(new Vector2i(32, 0), new Vector2i(32, 32));
    public static final TextureSample selector = new EntityTextureSample(new Vector2i(64, 0), new Vector2i(32, 32));
    public static final TextureSample door = new EntityTextureSample(new Vector2i(96, 0), new Vector2i(32, 32));
    public static final TextureSample logo = new EntityTextureSample(new Vector2i(0, 32), new Vector2i(512, 512));

    public EntityTextureSample(Vector2ic position, Vector2ic dimensions) {
        super(
            TextureUnit.entity,
            EntityTextureSample.atlasDimensions,
            position,
            dimensions
        );
    }
    
}
