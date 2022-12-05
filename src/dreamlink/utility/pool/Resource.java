package dreamlink.utility.pool;

public class Resource<T> implements AutoCloseable {
    
    public final T value;
    private final ResourcePool<T> pool;

    public Resource(ResourcePool<T> pool, T value) {
        this.pool = pool;
        this.value = value;
    }

    @Override
    public void close() {
        this.pool.close(this);
    }

}
