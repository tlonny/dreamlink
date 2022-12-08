package periwinkle.environment;

import periwinkle.Camera;
import periwinkle.Player;
import periwinkle.graphics.Atlas;
import periwinkle.graphics.Mesh;
import periwinkle.graphics.MeshBuffer;
import periwinkle.graphics.Shader;
import periwinkle.utility.CubeFace;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

import java.util.HashSet;
import java.util.Set;

public class Sky {

    private static int MAX_RAIN = 5000;
    private static int MAX_RAIN_PER_TICK = 10;
    private static int SKY_BOX_LENGTH = 8196;
    private static Vector3f SKY_BOX_OFFSET = new Vector3f(
        SKY_BOX_LENGTH,
        SKY_BOX_LENGTH,
        SKY_BOX_LENGTH
    ).mul(-0.5f);

    private Mesh skyBoxMesh = new Mesh(Atlas.SKY_ATLAS);
    private Mesh rainMesh = new Mesh(Atlas.PARTICLE_ATLAS);

    private MeshBuffer rainMeshBuffer = new MeshBuffer(MAX_RAIN * 2);
    public Set<Rain> rainSet = new HashSet<>();

    public SkyType skyType = SkyType.RAIN_DAY;

    private static Vector2f[] TEXTURE_OFFSETS = Atlas.SKY_ATLAS.buildTextureOffsets(new Vector2i(0, 0), new Vector2i(512, 512));

    public static Sky SKY = new Sky();
    public static void init() {
        SKY.setup();
    }

    private MeshBuffer skyBoxMeshBuffer = new MeshBuffer(6);

    private void buildSkyBoxMesh() {
        this.skyBoxMeshBuffer.clear();
        for(var cubeFace : CubeFace.CUBE_FACES) {
            for(var ix = 0; ix < cubeFace.vertices.length; ix += 1) {
                var vertex = cubeFace.vertices[ix];
                var inverseNormal = new Vector3f(cubeFace.normal).mul(-1f);
                this.skyBoxMeshBuffer.pushVertex(
                        new Vector3f(vertex).add(inverseNormal).mul(SKY_BOX_LENGTH),
                        new Vector3f(cubeFace.normal),
                        TEXTURE_OFFSETS[ix],
                        0f,
                        1f
                );
            }
            this.skyBoxMeshBuffer.indexQuad();
        }
        this.skyBoxMeshBuffer.flip();
        this.skyBoxMesh.loadMesh(this.skyBoxMeshBuffer);

    }

    public void setup() {
        this.rainMesh.setup();
        this.skyBoxMesh.setup();
        this.skyBoxMeshBuffer.clear();
        this.buildSkyBoxMesh();
    }

    public void update() {
        var newRain = (int)(MAX_RAIN_PER_TICK * this.skyType.rainRate);
        var rainCount = this.rainSet.size();
        if(rainCount + newRain < MAX_RAIN) {
            for (var ix = 0; ix < newRain; ix += 1)
                new Rain();
        }

        for(var rain : this.rainSet)
            rain.update();
    }

    private void renderSkyBox() {
        var position = new Vector3f(Player.PLAYER.position).add(SKY_BOX_OFFSET);
        this.skyBoxMesh.render(position, this.skyType.starColor, 0f);
    }

    private void renderRain(float stepFactor) {
        this.rainMeshBuffer.clear();
        var billboardMatrix = Camera.CAMERA.getBillboardMatrix();
        var rainInterpolatedPosition = new Vector3f();
        for(var rain : this.rainSet) {
            if(rain.position.distance(Camera.CAMERA.position) >= Rain.SPAWN_RANGE)
                continue;
            rainInterpolatedPosition.set(rain.previousPosition).lerp(rain.position, stepFactor);
            this.rainMeshBuffer.pushParticle(rainInterpolatedPosition, rain.width, ParticleType.RAIN, billboardMatrix);
        }

        this.rainMeshBuffer.flip();
        this.rainMesh.loadMesh(this.rainMeshBuffer);
        Shader.SHADER.setGlobalColor(this.skyType.rainColor);
        Shader.SHADER.setModelMatrix(new Matrix4f().identity());
        this.rainMesh.render(new Vector3f(), this.skyType.rainColor, 0f);
    }

    public void render(float stepFactor) {
        this.renderSkyBox();
        this.renderRain(stepFactor);
    }

}
