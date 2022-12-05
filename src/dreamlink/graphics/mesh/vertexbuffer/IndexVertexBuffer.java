package dreamlink.graphics.mesh.vertexbuffer;

import java.nio.IntBuffer;

import org.lwjgl.opengl.GL42;

public class IndexVertexBuffer implements IVertexBuffer {

    private int vertexBufferID;

    public void setup() {
        this.vertexBufferID = GL42.glGenBuffers();
    }

    public void buffer(IntBuffer intBuffer) {
        GL42.glBindBuffer(GL42.GL_ELEMENT_ARRAY_BUFFER, this.vertexBufferID);
        GL42.glBufferData(GL42.GL_ELEMENT_ARRAY_BUFFER, intBuffer, GL42.GL_DYNAMIC_DRAW);
    }
    
    public void destroy() {
        GL42.glDeleteBuffers(this.vertexBufferID);
    }
}
