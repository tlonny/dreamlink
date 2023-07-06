package doors.core.utility.vector;

import org.joml.Vector3f;
import org.json.JSONArray;

public class Vector3fl implements IVector3fl {

    public static Vector3fl fromHex(int hexCode) {
        var blue = hexCode & 0xFF;
        hexCode /= 0x100;
        var green = hexCode & 0xFF;
        hexCode /= 0x100;
        var red = hexCode & 0xFF;
        return new Vector3fl(red / 255f, green / 255f, blue / 255f);
    }

    private static float EPSILON = 0.0001f;
    private static Vector3f VECTOR_BUFFER = new Vector3f();

    public static Vector3fl ZERO = new Vector3fl(0f);
    public static Vector3fl ONE = new Vector3fl(1f);
    public static Vector3fl WHITE = fromHex(0xFFFFFF);
    public static Vector3fl BLACK = fromHex(0x000000);

    public float x;
    public float y;
    public float z;

    public Vector3fl(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3fl(IVector3fl other) {
        this(other.getFloatX(), other.getFloatY(), other.getFloatZ());
    }

    public Vector3fl(JSONArray array) {
        this(array.getFloat(0), array.getFloat(1), array.getFloat(2));
    }

    public Vector3fl(float scalar) {
        this(scalar, scalar, scalar);
    }

    public Vector3fl() {
        this(0f);
    }

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

    public Vector3fl add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vector3fl add(float scalar) {
        return this.add(scalar, scalar, scalar);
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

    public Vector3fl sub(float scalar) {
        return this.sub(scalar, scalar, scalar);
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

    public Vector3fl set(float scalar) {
        return this.set(scalar, scalar, scalar);
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

    public Vector3fl mul(float scalar) {
        return this.mul(scalar, scalar, scalar);
    }

    public Vector3fl mul(IVector3fl other) {
        return this.mul(other.getFloatX(), other.getFloatY(), other.getFloatZ());
    }

    public Vector3fl mul(double scalar) {
        return this.mul((float)scalar, (float)scalar, (float)scalar);
    }

    public Vector3fl div(float x, float y, float z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vector3fl div(float scalar) {
        return this.div(scalar, scalar, scalar);
    }

    public Vector3fl div(IVector3fl other) {
        return this.div(other.getFloatX(), other.getFloatY(), other.getFloatZ());
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

    public String toString() {
        return String.format("Vector3fl(%.2f, %.2f, %.2f)", this.x, this.y, this.z);
    }

}
