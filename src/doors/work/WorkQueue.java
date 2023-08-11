package doors.work;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import doors.Config;

public class WorkQueue {

    private static int JOB_LIMIT_MS = 15;

    public static WorkQueue WORK_QUEUE = new WorkQueue();

    private List<WorkUnit> outstandingJobs = new ArrayList<>();
    private ExecutorService threadPool;

    public WorkQueue() {
        var numThreads = Config.CONFIG.getNumThreads();
        this.threadPool = Executors.newFixedThreadPool(numThreads);
    }
    
    public void tearDown() {
        this.threadPool.shutdown();
    }

    public void submitJob(WorkUnit job) {
        this.outstandingJobs.add(job);
    }

    private WorkUnit getNextReadyJob() {
        for(var job : this.outstandingJobs) {
            if(job.isReady()) {
                return job;
            }
        }
        return null;
    }

    public void update() {
        this.outstandingJobs.sort(Comparator.comparing(WorkUnit::getDependencyCount));
        var currentTime = System.currentTimeMillis();

        while(!this.outstandingJobs.isEmpty()) {
            if(System.currentTimeMillis() > currentTime + JOB_LIMIT_MS) {
                break;
            }

            var job = this.getNextReadyJob();
            if(job != null) {
                this.outstandingJobs.remove(job);
            } else {
                break;
            }

            if(job.useWorkerThread) {
                //this.threadPool.submit(job::invoke);
                job.invoke();
            } else {
                job.invoke();
            }
        }
    }

    
}
