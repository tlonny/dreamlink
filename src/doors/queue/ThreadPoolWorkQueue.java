package doors.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import doors.utility.Functional.IAction0;

public class ThreadPoolWorkQueue {

    private static int POOL_SIZE = 8;

    public static ThreadPoolWorkQueue THREAD_POOL_WORK_QUEUE = new ThreadPoolWorkQueue();

    private ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);

    public void submitTask(IAction0 task) {
        this.threadPool.submit(task::invoke);
    }

    public void tearDown() {
        this.threadPool.shutdown();
    }
    
}
