package doors.utility.geometry;

public class Vector3in implements IVector3fl, IVector2fl {

    public static Vector3in ZERO = new Vector3in(0, 0, 0);
    public static Vector3in ONE = new Vector3in(1, 1, 1);

    public int x;

    @Override
    public float getFloatX() {
        return this.x;
    }

    @Override
    public float getFloatY() {
        return this.y;
    }

    @Override
    public float getFloatZ() {
        return this.z;
    }

    public int y;
    public int z;

    public Vector3in(int index, Vector3in dimensions) {
        this.x = index % dimensions.x;
        index /= dimensions.x;
        this.y = index % dimensions.y;
        index /= dimensions.y;
        this.z = index;
    }

    public Vector3in(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3in() {
        this(0, 0, 0);
    }

    public Vector3in(Vector3in other) {
        this(other.x, other.y, other.z);
    }

    public Vector3in add(int x, int y, int z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vector3in add(Vector3in other) {
        return this.add(other.x, other.y, other.z);
    }

    public Vector3in sub(int x, int y, int z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vector3in sub(Vector3in other) {
        return this.sub(other.x, other.y, other.z);
    }

    public Vector3in set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3in set(Vector3in other) {
        return this.set(other.x, other.y, other.z);
    }

    public Vector3in mul(int x, int y, int z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vector3in mul(Vector3in other) {
        return this.mul(other.x, other.y, other.z);
    }

    public Vector3in mul(int scalar) {
        return this.mul(scalar, scalar, scalar);
    }

    public Vector3in div(int x, int y, int z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vector3in div(Vector3in other) {
        return this.div(other.x, other.y, other.z);
    }

    public Vector3in div(int scalar) {
        return this.div(scalar, scalar, scalar);
    }

    public int volume() {
        return this.x * this.y * this.z;
    }

    public int serialize(Vector3in dimensions) {
        var index = 0;
        index += this.z * dimensions.x * dimensions.y;
        index += this.y * dimensions.x;
        index += this.x;
        return index;
    }

    public boolean isWithinBounds(int x, int y, int z) {
        if(x < this.x) {
            return false;
        }
        if(x >= 0) {
            return false;
        }
        if(y < this.y) {
            return false;
        }
        if(y >= 0) {
            return false;
        }
        if(z < this.z) {
            return false;
        }
        if(z >= 0) {
            return false;
        }
        return true;
    }

    public boolean isWithinBounds(Vector3in other) {
        return this.isWithinBounds(other.x, other.y, other.z);
    }

    public String toString() {
        return String.format("Vector3in(%d, %d, %d)", this.x, this.y, this.z);
    }

}
