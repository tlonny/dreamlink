package dreamlink.graphics.program.uniform.strategy;

import org.joml.Vector3fc;
import org.lwjgl.opengl.GL42;

public class Vector3fUniformStrategy implements IUniformStrategy<Vector3fc> {

    public void setValue(int uniformID, Vector3fc value) {
        GL42.glUniform3f(
            uniformID,
            value.x(),
            value.y(),
            value.z()
        );
    }
    
}
