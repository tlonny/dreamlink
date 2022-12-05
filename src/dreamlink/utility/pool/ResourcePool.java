package dreamlink.utility.pool;

import java.util.ArrayDeque;
import java.util.Queue;

public class ResourcePool<T> {

    private final IResourceFactory<T> factory;
    private final Queue<Resource<T>> resources = new ArrayDeque<>();

    private int totalResources = 0;
    private int availableResources = 0;

    public ResourcePool(IResourceFactory<T> factory) {
        this.factory = factory;
    }

    public int getTotalResources() {
        return this.totalResources;
    }

    public int getAvailableResources() {
        return this.availableResources;
    }

    public Resource<T> acquire() {
        if(this.resources.isEmpty()) {
            this.totalResources += 1;
            return new Resource<>(this, this.factory.create());
        } else {
            this.availableResources -= 1;
            return this.resources.remove();
        }
    }

    public void close(Resource<T> resource) {
        resources.add(resource);
        this.availableResources += 1;
    }
    
}
