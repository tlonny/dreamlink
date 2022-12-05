package dreamlink.graphics.program.uniform.strategy;

import org.lwjgl.opengl.GL42;

public class IntegerUniformStrategy implements IUniformStrategy<Integer> {

    @Override
    public void setValue(int uniformID, Integer value) {
        GL42.glUniform1i(
            uniformID,
            value.intValue()
        );
    }
    
}
