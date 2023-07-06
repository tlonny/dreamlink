package doors.core.utility.vector;

public interface IVector2fl {

    public float getFloatX();

    public float getFloatY();

    public default float getDistance(float x, float y) {
        var deltaX = this.getFloatX() - x;
        var deltaY = this.getFloatY() - y;
        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public default float getDistance(IVector2fl other) {
        return this.getDistance(other.getFloatX(), other.getFloatY());
    }

    public default float getDot(float x, float y) {
        return this.getFloatX() * x + this.getFloatY() * y;
    }

    public default float getDot(IVector2fl other) {
        return this.getDot(other.getFloatX(), other.getFloatY());
    }

    public default float getFloatArea() {
        return this.getFloatX() * this.getFloatY();
    }

    public default float getAspectRatio() {
        return this.getFloatX() / this.getFloatY();
    }

    public default boolean isWithinBounds(IVector2fl position, IVector2fl dimensions) {
        if(this.getFloatX() < position.getFloatX()) {
            return false;
        }
        if(this.getFloatX() >= position.getFloatX() + dimensions.getFloatX()) {
            return false;
        }
        if(this.getFloatY() < position.getFloatY()) {
            return false;
        }
        if(this.getFloatY() >= position.getFloatY() + dimensions.getFloatY()) {
            return false;
        }
        return true;
    }

}
