package doors.core.graphics.cube;

import doors.core.graphics.Shader;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.texture.TextureSample;
import doors.core.utility.CubeFace;
import doors.core.utility.vector.Vector3fl;

public class CubeSchema {

    public Vector3fl origin;
    public Vector3fl position;
    public Vector3fl dimensions;

    public TextureSample frontSample;
    public TextureSample backSample;
    public TextureSample topSample;
    public TextureSample bottomSample;
    public TextureSample rightSample;
    public TextureSample leftSample;

    public int cubeID;

    private Vector3fl positionBuffer;

    public CubeSchema(
        Vector3fl position, 
        Vector3fl origin,
        Vector3fl dimensions,
        TextureSample frontSample,
        TextureSample backSample,
        TextureSample topSample,
        TextureSample bottomSample,
        TextureSample rightSample,
        TextureSample leftSample,
        int cubeID
    ) {
        this.position = new Vector3fl(position).sub(origin);
        this.origin = origin;
        this.dimensions = dimensions;
        this.frontSample = frontSample;
        this.backSample = backSample;
        this.topSample = topSample;
        this.bottomSample = bottomSample;
        this.rightSample = rightSample;
        this.leftSample = leftSample;
        this.cubeID = cubeID;

        this.positionBuffer = new Vector3fl();
    }
    
    public CubeSchema(
        Vector3fl position, 
        Vector3fl origin,
        Vector3fl dimensions,
        TextureSample frontSample,
        TextureSample backSample,
        TextureSample topSample,
        TextureSample bottomSample,
        TextureSample rightSample,
        TextureSample leftSample
    ) {
        this(
            position, 
            origin, 
            dimensions, 
            frontSample, 
            backSample, 
            topSample, 
            bottomSample, 
            rightSample, 
            leftSample,
            0
        );
    }

    private TextureSample getTextureSample(CubeFace cubeFace) {
        if(cubeFace == CubeFace.FRONT) {
            return this.frontSample;
        }

        if(cubeFace == CubeFace.BACK) {
            return this.backSample;
        }

        if(cubeFace == CubeFace.TOP) {
            return this.topSample;
        }

        if(cubeFace == CubeFace.BOTTOM) {
            return this.bottomSample;
        }

        if(cubeFace == CubeFace.RIGHT) {
            return this.rightSample;
        }

        return this.leftSample;
    }

    public void transform(Vector3fl position, Vector3fl rotation, Vector3fl scale) {
        this.positionBuffer.set(position).add(this.origin);
        Shader.SHADER.setCubeTransformer(this.cubeID, this.positionBuffer, rotation, scale);
    }

    public void writeCubeSchema(MeshBuffer meshBuffer) {
        for(var cubeFace : CubeFace.CUBE_FACES) {
            var textureSample = this.getTextureSample(cubeFace);
            meshBuffer.writeQuad(
                textureSample,
                this.cubeID,
                this.position,
                this.dimensions,
                cubeFace,
                Vector3fl.WHITE
            );
        }
    }

}
