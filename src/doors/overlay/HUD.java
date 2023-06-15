package doors.overlay;

import org.joml.Vector2i;

import doors.Camera;
import doors.Config;
import doors.graphics.Mesh;
import doors.graphics.MeshBuffer;
import doors.graphics.TextureChannel;
import doors.graphics.TextureSample;
import doors.graphics.TextureSampler;
import doors.utility.Maths;

public class HUD {

    private static Vector2i RETICULE_SIZE = new Vector2i(16, 16);
    private static int MAX_SPRITES = 500;
    private static float EXPONENTIAL_WEIGHTING = 0.1f;
    private static Vector2i BASE_POSITION = new Vector2i(4, 4);
    private static TextureSample WORLD_RENDER_TEXTURE_SAMPLE = 
        new TextureSampler(TextureChannel.PORTAL_TEXTURE_CHANNEL, Config.RESOLUTION).createTextureSample();

    public static HUD HUD = new HUD();

    private Mesh mesh;
    private Vector2i positionCursor;
    private MeshBuffer meshBuffer;

    public long previousTime;
    public float framePeriod;

    public HUD() {
        this.mesh = new Mesh();
        this.meshBuffer = new MeshBuffer(MAX_SPRITES);
        this.positionCursor = new Vector2i(BASE_POSITION);
    }

    public void setup() {
        this.mesh.setup();
    }

    private void write(String text) {
        var textWriter = new TextMeshBufferWriter(this.meshBuffer);
        textWriter.pushText(this.positionCursor, text, Maths.VEC3F_ONE, false);
        this.positionCursor.y += OverlayTextureAtlas.TILE_8_16.y;
    }

    private void buildPerformanceTrackingMesh() {
        var timeNow = System.currentTimeMillis();
        var newFramePeriod = timeNow - previousTime;
        this.previousTime = timeNow;

        this.framePeriod *= (1-EXPONENTIAL_WEIGHTING);
        this.framePeriod += EXPONENTIAL_WEIGHTING * newFramePeriod;

        var fps = 1000f/(this.framePeriod);

        this.positionCursor.set(BASE_POSITION);
        this.write(String.format("frames per second: %.2f", fps));
        this.write(String.format(
                "position: %.1f, %.1f, %.1f", 
                Camera.CAMERA.position.x, 
                Camera.CAMERA.position.y, 
                Camera.CAMERA.position.z
        ));

    }
    private void buildReticuleMesh() {
        var spriteWriter = new SpriteMeshBufferWriter(this.meshBuffer);
        var reticulePosition = new Vector2i(
            Config.RESOLUTION.x / 2 - RETICULE_SIZE.x / 2,
            Config.RESOLUTION.y / 2 - RETICULE_SIZE.y / 2
        );
        spriteWriter.pushSprite(reticulePosition, RETICULE_SIZE, OverlayTextureAtlas.RETICULE, Maths.VEC3F_ONE);
    }

    private void buildWorldBillboardMesh() {
        var spriteWriter = new SpriteMeshBufferWriter(this.meshBuffer);
        spriteWriter.pushSprite(Maths.VEC2I_ZERO, Config.RESOLUTION, WORLD_RENDER_TEXTURE_SAMPLE, Maths.VEC3F_ONE);
    }

    public void render() {
        this.buildWorldBillboardMesh();
        this.buildPerformanceTrackingMesh();
        this.buildReticuleMesh();

        this.meshBuffer.flip();
        this.mesh.loadFromMeshBuffer(this.meshBuffer);
        this.meshBuffer.clear();
        this.mesh.render(Maths.VEC3F_ZERO, Maths.VEC3F_ZERO, Maths.VEC3F_ONE, Maths.VEC3F_ONE);
    }

}
