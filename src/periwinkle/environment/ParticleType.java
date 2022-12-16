package periwinkle.environment;

import periwinkle.graphics.Atlas;
import periwinkle.graphics.Sprite;

public class ParticleType {

    public final Sprite sprite;

    public ParticleType(String spriteKey) {
        this.sprite = Atlas.PARTICLE_ATLAS.getSprite(spriteKey);
    }

    public static ParticleType RAIN_DROP = new ParticleType("RAIN_DROP");
    public static ParticleType SNOW_FLAKE = new ParticleType("SNOW_FLAKE");

}
