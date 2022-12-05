package dreamlink.graphics.program.uniform.strategy;

import org.lwjgl.opengl.GL42;

public class BooleanUniformStrategy implements IUniformStrategy<Boolean> {

    @Override
    public void setValue(int uniformID, Boolean value) {
        GL42.glUniform1i(
            uniformID,
            value.booleanValue() ? 1 : 0
        );
    }
    
}
