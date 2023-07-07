package doors.core.graphics.cube;

import org.joml.Matrix4f;

public class Skeleton {

    public static int MAX_TRANSFORMERS = 32;

    public Matrix4f[] transformerMatrices;
    public int transformerCount;

    public Skeleton() {
        this.transformerMatrices = new Matrix4f[MAX_TRANSFORMERS];
        this.transformerCount = 0;
    }

    public Matrix4f createTransformer() {
        var transformerMatrix = new Matrix4f();
        this.transformerMatrices[this.transformerCount] = transformerMatrix;
        this.transformerCount += 1;
        return transformerMatrix;
    }

    public void clear() {
        this.transformerCount = 0;
    }

    public void apply() {
    }

}
