package periwinkle.component;

import org.joml.Vector3f;

public class Spatial {

    public Vector3f position = new Vector3f();
    public Vector3f dimensions = new Vector3f();
    public Vector3f rotation = new Vector3f();

    public Vector3f velocity = new Vector3f();

    public void simulate() {
        this.position.add(velocity);
    }

    public boolean isIntersection(Spatial other) {
        if(this.position.x + this.dimensions.x < other.position.x)
            return false;
        if(this.position.x > other.position.x + other.dimensions.x)
            return false;
        if(this.position.y + this.dimensions.y < other.position.y)
            return false;
        if(this.position.y > other.position.y + other.dimensions.y)
            return false;
        if(this.position.z + this.dimensions.z < other.position.z)
            return false;
        if(this.position.z > other.position.z + other.dimensions.z)
            return false;
        return true;
    }

}
