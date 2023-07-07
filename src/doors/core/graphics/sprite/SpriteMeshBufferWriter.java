package doors.core.graphics.sprite;

import doors.Config;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.texture.TextureSample;
import doors.core.utility.CubeFace;
import doors.core.utility.vector.Vector3fl;
import doors.core.utility.vector.IVector2fl;

public class SpriteMeshBufferWriter {

    private MeshBuffer meshBuffer;
    private Vector3fl positionBuffer;
    private Vector3fl dimensionsBuffer;

    public SpriteMeshBufferWriter(MeshBuffer meshBuffer) {
        this.meshBuffer = meshBuffer;
        this.positionBuffer = new Vector3fl();
        this.dimensionsBuffer = new Vector3fl();
    }

    public void writeSprite(TextureSample textureSample, IVector2fl position, IVector2fl dimensions, Vector3fl color) {
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

        this.meshBuffer.writeQuad(
            textureSample, 
            0,
            this.positionBuffer,
            this.dimensionsBuffer,
            CubeFace.FRONT, 
            color
        );
    }

}
