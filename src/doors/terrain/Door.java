package doors.terrain;

import org.joml.Vector3f;

import doors.graphics.ModelMesh;
import doors.graphics.Shader;
import doors.utility.CubeFace;
import doors.utility.Maths;

public class Door {

    public String target;
    public Vector3f position;
    public CubeFace orientation;

    public Door(String target, Vector3f position, CubeFace orientation) {
        this.target = target;
        this.position = position;
        this.orientation = orientation;
    }

    public void render() {
        Shader.setModel(this.position, Maths.VEC3F_ONE);
        ModelMesh.PORTAL.render();
    }

}
