package doors.utility;

public class BoxedValue<T> {

    public T value;

    public BoxedValue(T value) {
        this.value = value;
    }

    public BoxedValue() {
        this(null);
    }
    
}
