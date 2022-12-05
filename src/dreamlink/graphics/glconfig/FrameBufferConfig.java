package dreamlink.graphics.glconfig;

import org.joml.Vector2i;
import org.lwjgl.opengl.GL42;

import dreamlink.graphics.FrameBuffer;
import dreamlink.graphics.glconfig.state.GLState;
import dreamlink.window.Window;

public class FrameBufferConfig extends GLConfig<FrameBuffer> {

    private static final Vector2i setFrameBufferResolution = new Vector2i();

    private static void setFrameBuffer(FrameBuffer frameBuffer) {
        var resolution = Window.instance.getResolution(FrameBufferConfig.setFrameBufferResolution);
        GL42.glBindFramebuffer(GL42.GL_FRAMEBUFFER, frameBuffer != null ? frameBuffer.getFrameBufferID() : 0);
        GL42.glViewport(0, 0, resolution.x(), resolution.y());
    }

    private static GLState<FrameBuffer> strategy = new GLState<>(
        FrameBufferConfig::setFrameBuffer
    );

    public static void setup() {
        FrameBufferConfig.strategy.setState(null);
    }

    public FrameBufferConfig() {
        super(FrameBufferConfig.strategy);
    }

}
