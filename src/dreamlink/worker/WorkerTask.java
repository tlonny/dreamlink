package dreamlink.worker;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class WorkerTask<T> {

    private Future<T> future;

    public WorkerTask(Future<T> future) {
        this.future = future;
    }

    public boolean isDone() {
        return this.future.isDone();
    }

    public T join() {
        try {
            return this.future.get();
        } catch(ExecutionException e) {
            var rootException = e.getCause();
            throw new RuntimeException(rootException);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
}
