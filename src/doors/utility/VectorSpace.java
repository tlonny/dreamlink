package doors.utility;

import org.joml.Vector3i;

public class VectorSpace {
    private Vector3i dimensions;

    public VectorSpace(Vector3i dimensions) {
        this.dimensions = dimensions;
    }

    public int getMaxIndex() {
        return this.dimensions.x * this.dimensions.y * this.dimensions.z;
    }

    public int getIndex(Vector3i position) {
        var index = 0;
        index += position.z * this.dimensions.x * this.dimensions.y;
        index += position.y * this.dimensions.x;
        index += position.x;
        return index;
    }

    public boolean isWithinBounds(Vector3i position) {
        if(position.x < 0 || position.x >= this.dimensions.x) {
            return false;
        }
        if(position.y < 0 || position.y >= this.dimensions.y) {
            return false;
        }
        if(position.z < 0 || position.z >= this.dimensions.z) {
            return false;
        }
        return true;
    }

    public Vector3i fromIndex(int index) {
        var position = new Vector3i();
        position.x = index % this.dimensions.x;
        index /= this.dimensions.x;
        position.y = index % this.dimensions.y;
        index /= this.dimensions.y;
        position.z = index;
        return position;
    }

}
