package dreamlink.graphics.program.uniform.strategy;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL42;
import org.lwjgl.system.MemoryUtil;

public class Matrix4fUniformStrategy implements IUniformStrategy<Matrix4f> {

    private static final FloatBuffer matrixFloatBuffer = MemoryUtil.memAllocFloat(16);

    public void setValue(int uniformID, Matrix4f value) {
        Matrix4fUniformStrategy.matrixFloatBuffer.clear();
        value.get(Matrix4fUniformStrategy.matrixFloatBuffer);
        GL42.glUniformMatrix4fv(
            uniformID,
            false,
            Matrix4fUniformStrategy.matrixFloatBuffer
        );
    }
}
