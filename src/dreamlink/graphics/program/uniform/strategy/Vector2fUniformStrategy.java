package dreamlink.graphics.program.uniform.strategy;

import org.joml.Vector2fc;
import org.lwjgl.opengl.GL42;

public class Vector2fUniformStrategy implements IUniformStrategy<Vector2fc> {

    public void setValue(int uniformID, Vector2fc value) {
        GL42.glUniform2f(
            uniformID,
            value.x(),
            value.y()
        );
    }
    
}
