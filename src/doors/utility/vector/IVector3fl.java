package doors.utility.vector;

public interface IVector3fl {

    public float getFloatX();

    public float getFloatY();

    public float getFloatZ();

    public default float getDistance(float x, float y, float z) {
        var deltaX = this.getFloatX() - x;
        var deltaY = this.getFloatY() - y;
        var deltaZ = this.getFloatZ() - z;
        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
    }

    public default float getDistance(IVector3fl other) {
        return this.getDistance(other.getFloatX(), other.getFloatY(), other.getFloatZ());
    }

    public default float getDot(float x, float y, float z) {
        return this.getFloatX() * x + this.getFloatY() * y + this.getFloatZ() * z;
    }

    public default float getDot(IVector3fl other) {
        return this.getDot(other.getFloatX(), other.getFloatY(), other.getFloatZ());
    }

    public default float getFloatVolume() {
        return this.getFloatX() * this.getFloatY() * this.getFloatZ();
    }
    
    public default boolean isWithinBounds(IVector3fl position, IVector3fl dimensions) {
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
        if(this.getFloatZ() < position.getFloatZ()) {
            return false;
        }
        if(this.getFloatZ() >= position.getFloatZ() + dimensions.getFloatZ()) {
            return false;
        }
        return true;
    }

}
