package doors.utility.geometry;

import org.joml.Vector3f;

public class Vector3fl implements IVector3fl, IVector2fl {

    private static float EPSILON = 0.0001f;
    private static Vector3f VECTOR_BUFFER = new Vector3f();

    public static Vector3fl ZERO = new Vector3fl(0f, 0f, 0f);
    public static Vector3fl ONE = new Vector3fl(1f, 1f, 1f);

    public float x;

    @Override
    public float getFloatX() {
        return this.x;
    }

    public float y;

    @Override
    public float getFloatY() {
        return this.y;
    }

    public float z;
    
    @Override
    public float getFloatZ() {
        return this.z;
    }

    public Vector3fl(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3fl() {
        this(0, 0, 0);
    }

    public Vector3fl(IVector3fl other) {
        this(other.getFloatX(), other.getFloatY(), other.getFloatZ());
    }

    public Vector3fl add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vector3fl add(IVector3fl other) {
        return this.add(other.getFloatX(), other.getFloatY(), other.getFloatZ());
    }

    public Vector3fl sub(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vector3fl sub(IVector3fl other) {
        return this.sub(other.getFloatX(), other.getFloatY(), other.getFloatZ());
    }

    public Vector3fl set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3fl set(IVector3fl other) {
        return this.set(other.getFloatX(), other.getFloatY(), other.getFloatZ());
    }

    public Vector3fl zeroFuzz() {
        if(Math.abs(this.x) < EPSILON) {
            this.x = 0;
        }
        if(Math.abs(this.y) < EPSILON) {
            this.y = 0;
        }
        if(Math.abs(this.z) < EPSILON) {
            this.z = 0;
        }
        return this;
    }

    public Vector3fl mul(float x, float y, float z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Vector3fl mul(IVector3fl other) {
        return this.mul(other.getFloatX(), other.getFloatY(), other.getFloatZ());
    }

    public Vector3fl mul(float scalar) {
        return this.mul(scalar, scalar, scalar);
    }

    public Vector3fl mul(double scalar) {
        return this.mul((float)scalar, (float)scalar, (float)scalar);
    }

    public Vector3fl rotateX(float angle) {
        VECTOR_BUFFER.set(this.x, this.y, this.z);
        VECTOR_BUFFER.rotateX(angle);
        return this.set(VECTOR_BUFFER.x, VECTOR_BUFFER.y, VECTOR_BUFFER.z);
    }

    public Vector3fl rotateY(float angle) {
        VECTOR_BUFFER.set(this.x, this.y, this.z);
        VECTOR_BUFFER.rotateY(angle);
        return this.set(VECTOR_BUFFER.x, VECTOR_BUFFER.y, VECTOR_BUFFER.z);
    }

    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public float distance(float x, float y, float z) {
        var deltaX = this.x - x;
        var deltaY = this.y - y;
        var deltaZ = this.z - z;
        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
    }

    public float distance(IVector3fl other) {
        return this.distance(other.getFloatX(), other.getFloatY(), other.getFloatZ());
    }

    public String toString() {
        return String.format("Vector3fl(%.2f, %.2f, %.2f)", this.x, this.y, this.z);
    }

}
