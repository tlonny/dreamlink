package periwinkle.environment;

import java.util.HashSet;
import java.util.Set;
import org.joml.Vector3f;

import periwinkle.Game;
import periwinkle.graphics.Mesh;
import periwinkle.graphics.MeshBuffer;
import periwinkle.utility.Maths;

public abstract class ParticleEmitter {


    private Set<Particle> particles = new HashSet<Particle>();
    private int maxParticles;
    private MeshBuffer meshBuffer;
    private Mesh mesh = new Mesh(Game.PARTICLE_ATLAS);

    public ParticleEmitter(int maxParticles) {
        this.maxParticles = maxParticles;
        this.meshBuffer = new MeshBuffer(maxParticles * 2); // FRONT_AND_BACK
    }

    public void addParticle(Particle particle) {
        this.particles.add(particle);
    }

    public void removeParticle(Particle particle) {
        this.particles.remove(particle);
    }

    public abstract void simulateParticle(Particle particle);
        
    public void simulate() {
        for(var particle : this.particles) {
            particle.previousPosition.set(particle.position);
            this.simulateParticle(particle);
        }
    }

    public void setup() {
        this.mesh.setup();
    }

    public void render(float simFactor) {
        this.meshBuffer.clear();
        var particleCount = 0;
        for(var particle : this.particles) {
            particle.batch(this.meshBuffer, simFactor);
            particleCount += 1;

            if(particleCount >= this.maxParticles) {
                break;
            }
        }

        this.meshBuffer.flip();
        this.mesh.loadFromBuffer(this.meshBuffer);
        this.mesh.render(Maths.ZERO, Maths.COLOR_WHITE, 0f);
    }


}
