package dreamlink.worker;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerPool {

    public static final WorkerPool instance = new WorkerPool();

    private ExecutorService executor;

    public void setup() {
        var numThreads = Runtime.getRuntime().availableProcessors();
        this.executor = Executors.newFixedThreadPool(numThreads);
    }

    public <T>WorkerTask<T> submit(Callable<T> task) {
        var future = this.executor.submit(task);
        return new WorkerTask<T>(future);
    }

    public WorkerTask<?> submit(Runnable task) {
        var future = this.executor.submit(task);
        return new WorkerTask<>(future);
    }

    public void destroy() {
        this.executor.shutdown();
    }
    
}
