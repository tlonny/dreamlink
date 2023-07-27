package doors.queue;

import java.util.LinkedList;
import java.util.Queue;

import doors.utility.Functional.IAction0;

public class IncrementalWorkQueue {

    public static IncrementalWorkQueue INCREMENTAL_WORK_QUEUE = new IncrementalWorkQueue(10);

    private Queue<IAction0> workQueue = new LinkedList<>();
    private int jobLimitMs;

    public IncrementalWorkQueue(int jobLimitMs) {
        this.jobLimitMs = jobLimitMs;
    }

    public synchronized void submitTask(IAction0 action) {
        this.workQueue.add(action);
    }

    private synchronized IAction0 getNextTask() {
        if(this.workQueue.isEmpty()) {
            return null;
        }
        return this.workQueue.remove();
    }

    public void update() {
        var currentTime = System.currentTimeMillis();
        while(true) {
            if(System.currentTimeMillis() - currentTime > this.jobLimitMs) {
                break;
            }

            var nextTask = this.getNextTask();
            if(nextTask == null) {
                break;
            }

            nextTask.invoke();
        }
    }

}
