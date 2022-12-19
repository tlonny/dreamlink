package periwinkle.environment;

import org.joml.Vector2i;

import periwinkle.graphics.Atlas;
import periwinkle.graphics.Texture;

public class ParticleAtlas extends Atlas {

    private static Vector2i TILE_8_8 = new Vector2i(8,8);

    public Texture rain = this.createTexture(new Vector2i(0,0), TILE_8_8);
    public Texture solid = this.createTexture(new Vector2i(1,0), TILE_8_8);
    public Texture smallStar = this.createTexture(new Vector2i(2,0), TILE_8_8);
    public Texture largeStar = this.createTexture(new Vector2i(3,0), TILE_8_8);

    public ParticleAtlas() {
        super("src/texture/particle.png", new Vector2i(512, 512));
    }
}


