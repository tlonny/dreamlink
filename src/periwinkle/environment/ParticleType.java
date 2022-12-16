package periwinkle.environment;

import periwinkle.graphics.Atlas;
import periwinkle.graphics.Sprite;

public class ParticleType {

    public Sprite sprite;
    private final String spriteKey;

    public static void init() {
        RAIN_DROP.setup();
        SNOW_FLAKE.setup();
    }

    public ParticleType(String spriteKey) {
        this.spriteKey = spriteKey;
    }

    public void setup() {
        this.sprite = Atlas.PARTICLE_ATLAS.getSprite(spriteKey);
    }

    public static ParticleType RAIN_DROP = new ParticleType("RAIN_DROP");
    public static ParticleType SNOW_FLAKE = new ParticleType("SNOW_FLAKE");

}
