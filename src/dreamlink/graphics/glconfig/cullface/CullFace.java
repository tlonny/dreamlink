package dreamlink.graphics.glconfig.cullface;

import org.lwjgl.opengl.GL42;

public class CullFace {

    public static final CullFace front = new CullFace(GL42.GL_FRONT);
    public static final CullFace back = new CullFace(GL42.GL_BACK);

    public final int face;

    public CullFace(int face) {
        this.face = face;
    }
    
}
