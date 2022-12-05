package dreamlink.graphics.glconfig.blend;

import org.lwjgl.opengl.GL42;

public class BlendMode {

    public static final BlendMode noBlend = new BlendMode(
        "noblend",
        GL42.GL_ONE, 
        GL42.GL_ZERO
    );

    public static final BlendMode alphaBlend = new BlendMode(
        "alphablend",
        GL42.GL_SRC_ALPHA, 
        GL42.GL_ONE_MINUS_SRC_ALPHA
    );

    public static final BlendMode invert = new BlendMode(
        "invert",
        GL42.GL_ONE_MINUS_DST_COLOR,
        GL42.GL_ONE_MINUS_SRC_ALPHA
    );

    public final String name;
    public final int srcFactor;
    public final int dstFactor;

    private BlendMode(String name, int srcFactor, int dstFactor) {
        this.name = name;
        this.srcFactor = srcFactor;
        this.dstFactor = dstFactor;
    }
    
}
