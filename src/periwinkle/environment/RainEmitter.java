package periwinkle.environment;

import periwinkle.Game;
import org.joml.Vector2i;
import java.util.Random;

public class RainEmitter extends ParticleEmitter {

    private static int MAX_RAIN = 5_000;
    private static float RAIN_SPEED = 2f;
    private static float SPAWN_RANGE = 64;
    private static float DE_SPAWN_RANGE = SPAWN_RANGE * 1.5f;

    public RainEmitter() {
        super(MAX_RAIN);
    }

    private Random random = new Random();

    private boolean inBounds(Particle particle) {
        if(particle.position.distance(Game.CAMERA.position) > DE_SPAWN_RANGE)
            return false;
        var height = Game.WORLD.getHeight(new Vector2i(
            (int)particle.position.x,
            (int)particle.position.z
        ));

        // Takes an extra tick to de-register the entity
        // so we need to make sure the rain doesn't come through the floor...
        if(particle.position.y <= height + RAIN_SPEED)
            return false;
        return true;
    }

    public void simulate() {
        for(var ix = 0; ix < Game.SKY_TYPE.rainPerSim; ix += 1) {
            var particle = new Particle(this);
            particle.texture = Game.PARTICLE_ATLAS.rain;
            particle.scale = 0.1f + this.random.nextFloat() * 0.4f;
            particle.color.set(Game.SKY_TYPE.rainColor);
            particle.position.set(
                (this.random.nextFloat() - 0.5f) * SPAWN_RANGE * 2f + Game.CAMERA.position.x,
                Game.CAMERA.position.y + SPAWN_RANGE,
                (this.random.nextFloat() - 0.5f) * SPAWN_RANGE * 2f + Game.CAMERA.position.z
            );
            particle.previousPosition.set(particle.position);
        }
        super.simulate();
    }

    public void simulateParticle(Particle particle) {
        particle.position.y -= RAIN_SPEED;
        if(!this.inBounds(particle))
            particle.destroy();
    }

}
