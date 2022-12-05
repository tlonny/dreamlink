package dreamlink.graphics.mesh.vertexbuffer;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL42;

public class FloatVertexBuffer implements IVertexBuffer {

    private final int location;
    private final int size;
    private int vertexBufferID;

    public FloatVertexBuffer(int location, int size) {
        this.location = location;
        this.size = size;
    }

    @Override
    public void setup() {
        this.vertexBufferID = GL42.glGenBuffers();
        GL42.glBindBuffer(GL42.GL_ARRAY_BUFFER, this.vertexBufferID);
        GL42.glVertexAttribPointer(this.location, this.size, GL42.GL_FLOAT, false, 0, 0);
        GL42.glEnableVertexAttribArray(this.location);
    }

    public void buffer(FloatBuffer floatBuffer) {
        GL42.glBindBuffer(GL42.GL_ARRAY_BUFFER, this.vertexBufferID);
        GL42.glBufferData(GL42.GL_ARRAY_BUFFER, floatBuffer, GL42.GL_DYNAMIC_DRAW);
    }

    @Override
    public void destroy() {
        GL42.glDeleteBuffers(this.vertexBufferID);
    }
    
}
