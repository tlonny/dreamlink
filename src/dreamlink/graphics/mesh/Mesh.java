package dreamlink.graphics.mesh;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL42;
import org.lwjgl.system.MemoryUtil;

import dreamlink.graphics.glconfig.MeshConfig;
import dreamlink.graphics.mesh.vertexbuffer.IVertexBuffer;

public abstract class Mesh {

    private static final int maxSize = 500_000;
    protected static final FloatBuffer floatBuffer = MemoryUtil.memAllocFloat(Mesh.maxSize);
    protected static final IntBuffer intBuffer = MemoryUtil.memAllocInt(Mesh.maxSize);

    private final List<IVertexBuffer> vertexBuffers = new ArrayList<>();
    private int vertexArrayID;

    public abstract int getIndicesCount();

    protected void addMeshBuffer(IVertexBuffer meshBuffer) {
        this.vertexBuffers.add(meshBuffer);
    }

    public int getVertexArrayID() {
        return this.vertexArrayID;
    }

    public void setup() {
        this.vertexArrayID = GL42.glGenVertexArrays();
        try(var meshConfig = new MeshConfig()) {
            meshConfig.setState(this);
            for(var meshBuffer : this.vertexBuffers) {
                meshBuffer.setup();
            }
        }
    }

    private final MeshConfig renderMeshConfig = new MeshConfig();

    public void render() {
        try(var meshStateRef = this.renderMeshConfig.checkpoint()) {
            meshStateRef.setState(this);
            GL42.glDrawElements(
                GL42.GL_TRIANGLES, 
                this.getIndicesCount(), 
                GL42.GL_UNSIGNED_INT, 
                0
            );
        }
    }

    public void destroy() {
        try(var meshConfig = new MeshConfig()) {
            meshConfig.setState(this);
            for(var meshBuffer : this.vertexBuffers) {
                meshBuffer.destroy();
            }

            GL42.glDeleteVertexArrays(this.vertexArrayID);
        }
    }
    
}
