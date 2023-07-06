package doors.core.graphics.sprite;

import doors.Config;
import doors.core.graphics.mesh.Mesh;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.texture.TextureChannel;
import doors.core.graphics.texture.TextureSample;
import doors.core.utility.CubeFace;
import doors.core.utility.vector.Vector3fl;
import doors.core.utility.vector.IVector2fl;

public class SpriteBatch {

    private Mesh mesh;
    private MeshBuffer meshBuffer;
    private Vector3fl positionBuffer;
    private Vector3fl dimensionsBuffer;

    public SpriteBatch(int maxSprites) {
        this.meshBuffer = new MeshBuffer(maxSprites);
        this.mesh = new Mesh();
        this.positionBuffer = new Vector3fl();
        this.dimensionsBuffer = new Vector3fl();
    }

    public void setup() {
        this.mesh.setup();
    }

    public void startBatch() {
        this.meshBuffer.clear();
    }

    public void endBatch() {
        this.meshBuffer.flip();
        this.mesh.loadDataFromMeshBuffer(this.meshBuffer);
    }

    public void render() {
        this.mesh.render(Vector3fl.ZERO, Vector3fl.ZERO, Vector3fl.ONE, Vector3fl.WHITE);
    }

    public void writeSprite(TextureChannel textureChannel, TextureSample textureSample, IVector2fl position, IVector2fl dimensions, Vector3fl color) {
        this.positionBuffer.set(
            position.getFloatX() / Config.RESOLUTION.x * 2f - 1f,
            (Config.RESOLUTION.y - position.getFloatY() - dimensions.getFloatY()) / Config.RESOLUTION.y * 2f - 1f, 
            1f
        );

        this.dimensionsBuffer.set(
            dimensions.getFloatX() / Config.RESOLUTION.x * 2f, 
            dimensions.getFloatY() / Config.RESOLUTION.y * 2f,
            0f
        );

        this.meshBuffer.addQuad(
            textureSample, 
            textureChannel,
            this.positionBuffer,
            this.dimensionsBuffer,
            CubeFace.FRONT, 
            color
        );
    }

}
