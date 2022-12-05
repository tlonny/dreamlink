package dreamlink.graphics.glconfig;

import dreamlink.graphics.glconfig.state.GLState;

public class GLConfig<T> implements AutoCloseable {

    private T previousValue;
    private final GLState<T> state;

    protected GLConfig(GLState<T> state) {
        this.state = state;
        this.previousValue = this.state.getCurrentValue();
    }

    public GLConfig<T> checkpoint() {
        this.previousValue = this.state.getCurrentValue();
        return this;
    }

    public void setState(T value) {
        this.state.setState(value);
    }
    
    @Override
    public void close() {
        this.setState(this.previousValue);
    }
}
    