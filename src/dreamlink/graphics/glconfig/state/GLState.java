package dreamlink.graphics.glconfig.state;

public class GLState<T> {

    private IGLStateStrategy<T> strategy;
    private T value;

    public GLState(IGLStateStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void setState(T value) {
        if(this.value != value) {
            this.value = value;
            this.strategy.setState(value);
        }
    }

    public T getCurrentValue() {
        return this.value;
    }

    
}
