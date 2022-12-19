package periwinkle.environment;

import periwinkle.Game;
import periwinkle.graphics.Texture;

public class ParticleType {

    public Texture texture;
    public String name;

    public ParticleType(String name, Texture texture) {
        this.name = name;
        this.texture = texture;
    }

    public static ParticleType RAIN_DROP = new ParticleType("rain", Game.PARTICLE_ATLAS.rain);

}
