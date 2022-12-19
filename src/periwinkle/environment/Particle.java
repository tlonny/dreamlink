package periwinkle.environment;

import org.joml.Vector2f;
import org.joml.Vector3f;

import periwinkle.Game;
import periwinkle.entity.Entity;
import periwinkle.graphics.MeshBuffer;
import periwinkle.graphics.Texture;
import periwinkle.utility.CubeFace;

public class Particle extends Entity {

    public Vector3f position = new Vector3f();
    public Vector3f color = new Vector3f(1f, 1f, 1f);
    public float scale = 1f;
    public Vector3f previousPosition = new Vector3f();
    public Vector2f dimensions = new Vector2f();
    public Texture texture;

    private ParticleEmitter emitter;
    private Vector3f positionBuffer = new Vector3f();

    public Particle(ParticleEmitter emitter) {
        super();
        this.emitter = emitter;
    }

    public void setup() {
        this.emitter.addParticle(this);
        Game.PERFORMANCE_TRACKER.numParticles += 1;
    }

    public void tearDown() {
        this.emitter.removeParticle(this);
        Game.PERFORMANCE_TRACKER.numParticles -= 1;
    }

    public void batch(MeshBuffer meshBuffer, float simFactor) {
        positionBuffer.set(this.previousPosition).lerp(this.position, simFactor);
        for(var cubeFace : CubeFace.FRONT_AND_BACK_CUBE_FACES) {
            for (var vertIx = 0; vertIx < cubeFace.vertices.length; vertIx += 1) {
                var vertex = cubeFace.vertices[vertIx];
                meshBuffer.position.set(vertex).mul(this.scale).mul(Game.CAMERA.billboardMatrix).add(positionBuffer);
                meshBuffer.normal.set(CubeFace.FRONT.normal);
                meshBuffer.localLight = 1f;
                meshBuffer.globalLight = 0f;
                meshBuffer.color.set(this.color);
                meshBuffer.textureOffset.set(this.texture.vertices[vertIx]);
                meshBuffer.push();
            }
        }
    }
}
