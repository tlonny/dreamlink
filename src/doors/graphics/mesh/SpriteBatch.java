package doors.graphics.mesh;

import doors.core.Config;
import doors.graphics.texture.TextureSample;
import doors.utility.CubeFace;
import doors.utility.vector.IVector2fl;
import doors.utility.vector.Vector3fl;

public class SpriteBatch {

    private static int MAX_SPRITES = 5_000;

    public static SpriteBatch SPRITE_BATCH = new SpriteBatch();

    private MeshBuffer meshBuffer;
    private Mesh mesh = new Mesh();

    public SpriteBatch() {
        this.meshBuffer = new MeshBuffer(MAX_SPRITES);
    }

    public void setup() {
        this.mesh.setup();
    }

    public void writeSprite(TextureSample textureSample, IVector2fl position, IVector2fl dimensions, Vector3fl color) {
        var worldPosition = new Vector3fl(
            position.getFloatX() / Config.RESOLUTION.x * 2f - 1f,
            (Config.RESOLUTION.y - position.getFloatY() - dimensions.getFloatY()) / Config.RESOLUTION.y * 2f - 1f, 
            1f
        );

        var worldDimensions = new Vector3fl(
            dimensions.getFloatX() / Config.RESOLUTION.x * 2f, 
            dimensions.getFloatY() / Config.RESOLUTION.y * 2f,
            0f
        );

        this.meshBuffer.writeQuad(
            textureSample, 
            0,
            worldPosition,
            worldDimensions,
            CubeFace.FRONT, 
            color
        );
    }

    public void flush() {
        this.mesh.loadDataFromMeshBuffer(this.meshBuffer);
        this.meshBuffer.clear();
    }

    public void render() {
        this.mesh.render(
            Vector3fl.ZERO,
            Vector3fl.ZERO,
            Vector3fl.ONE,
            Vector3fl.WHITE
        );
    }


}
