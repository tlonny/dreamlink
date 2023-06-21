package doors.utility.geometry;

public class Vector2fl implements IVector2fl {

    public static Vector2fl ZERO = new Vector2fl(0f, 0f);

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

    public Vector2fl(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2fl() {
        this(0f, 0f);
    }

    public Vector2fl(IVector2fl other) {
        this(other.getFloatX(), other.getFloatY());
    }

    public Vector2fl add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2fl add(Vector2fl toAdd) {
        return this.add(toAdd.x, toAdd.y);
    }

    public Vector2fl sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2fl sub(Vector2fl other) {
        return this.sub(other.getFloatX(), other.getFloatY());
    }

    public Vector2fl mul(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vector2fl mul(float scalar) {
        return this.mul(scalar, scalar);
    }

    public Vector2fl mul(IVector2fl other) {
        return this.mul(other.getFloatX(), other.getFloatY());
    }

    public Vector2fl mul(Vector2in toDivide) {
        return this.mul(toDivide.x, toDivide.y);
    }

    public Vector2fl div(float x, float y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vector2fl div(float scalar) {
        return this.div(scalar, scalar);
    }

    public Vector2fl div(IVector2fl other) {
        return this.div(other.getFloatX(), other.getFloatY());
    }

    public Vector2fl set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2fl set(IVector2fl other) {
        return this.set(other.getFloatX(), other.getFloatY());
    }

    public String toString() {
        return String.format("Vector2fl(%.2f, %.2f)", this.x, this.y);
    }

}
