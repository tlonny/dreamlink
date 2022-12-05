package dreamlink.graphics.program.uniform.strategy;

import org.joml.Vector4fc;
import org.lwjgl.opengl.GL42;

public class Vector4fUniformStrategy implements IUniformStrategy<Vector4fc> {

    public void setValue(int programID, Vector4fc value) {
        GL42.glUniform4f(
            programID,
            value.x(),
            value.y(),
            value.z(),
            value.w()
        );
    }
    
}
