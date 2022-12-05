package dreamlink.graphics.glconfig;

import org.lwjgl.opengl.GL42;

import dreamlink.graphics.glconfig.state.GLState;
import dreamlink.graphics.mesh.Mesh;

public class MeshConfig extends GLConfig<Mesh> {

    private static void setMesh(Mesh mesh) {
        GL42.glBindVertexArray(mesh == null ? 0 : mesh.getVertexArrayID());
    }

    private static final GLState<Mesh> state = new GLState<>(
        MeshConfig::setMesh
    );

    public static void setup() {
        MeshConfig.state.setState(null);
    }

    public MeshConfig() {
        super(MeshConfig.state);
    }
    
}
