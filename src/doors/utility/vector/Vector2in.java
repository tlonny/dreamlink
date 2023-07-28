package doors.utility.vector;

import org.json.JSONArray;

public class Vector2in implements IVector2fl {

    public static final Vector2in WHITE = null;
    public static Vector2in ZERO = new Vector2in(0);
    public static Vector2in ONE = new Vector2in(1);
    public static Vector2in TWO = new Vector2in(2);
    public static Vector2in MAX = new Vector2in(Integer.MAX_VALUE, Integer.MAX_VALUE);
    public static Vector2in MAX_X = new Vector2in(Integer.MAX_VALUE, 0);
    public static Vector2in MAX_Y = new Vector2in(0, Integer.MAX_VALUE);

    public int x;
    public int y;

    public Vector2in(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2in(int scalar) {
        this(scalar, scalar);
    }

    public Vector2in(Vector2in other) {
        this(other.x, other.y);
    }

    public Vector2in(JSONArray array) {
        this(array.getInt(0), array.getInt(1));
    }

    public Vector2in() {
        this(0);
    }

    @Override
    public float getFloatX() {
        return this.x;
    }

    @Override
    public float getFloatY() {
        return this.y;
    }

    public Vector2in add(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2in add(int scalar) {
        return this.add(scalar, scalar);
    }

    public Vector2in add(Vector2in toAdd) {
        return this.add(toAdd.x, toAdd.y);
    }

    public Vector2in sub(int x, int y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2in sub(int scalar) {
        return this.sub(scalar, scalar);
    }

    public Vector2in sub(Vector2in toSub) {
        return this.sub(toSub.x, toSub.y);
    }

    public Vector2in mul(int x, int y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vector2in mul(int scalar) {
        return this.mul(scalar, scalar);
    }

    public Vector2in mul(Vector2in toDivide) {
        return this.mul(toDivide.x, toDivide.y);
    }

    public Vector2in div(int x, int y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vector2in div(int scalar) {
        return this.div(scalar, scalar);
    }

    public Vector2in div(Vector2in toDivide) {
        return this.div(toDivide.x, toDivide.y);
    }

    public Vector2in set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2in set(int scalar) {
        return this.set(scalar, scalar);
    }

    public Vector2in set(Vector2in other) {
        return this.set(other.x, other.y);
    }

    public Vector2in set(Vector2fl other) {
        return this.set((int)other.x, (int)other.y);
    }

    public String toString() {
        return String.format("Vector2in(%d, %d)", this.x, this.y);
    }

    public Vector2in max(int x, int y) {
        this.x = Math.max(this.x, x);
        this.y = Math.max(this.y, y);
        return this;
    }

    public Vector2in max(Vector2in other) {
        return this.max(other.x, other.y);
    }

    public Vector2in min(int x, int y) {
        this.x = Math.min(this.x, x);
        this.y = Math.min(this.y, y);
        return this;
    }

    public Vector2in min(Vector2in other) {
        return this.min(other.x, other.y);
    }

    public boolean isWithinBounds(Vector2in position, Vector2in dimensions) {
        if(this.x < position.x || this.x >= position.x + dimensions.x) {
            return false;
        }
        if(this.y < position.y || this.y >= position.y + dimensions.y) {
            return false;
        }
        return true;
    }

    public int getIntArea() {
        return this.x * this.y;
    }

}
