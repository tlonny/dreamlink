package dreamlink.graphics.program.uniform.strategy;

import org.lwjgl.opengl.GL42;

public class FloatUniformStrategy implements IUniformStrategy<Float> {

    @Override
    public void setValue(int uniformID, Float value) {
        GL42.glUniform1f(
            uniformID,
            value.floatValue()
        );
    }
    
}
