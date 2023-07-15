package doors.queue;

import java.util.LinkedList;
import java.util.Queue;

import doors.utility.Functional.IAction0;

public class WorkQueue {

    public static WorkQueue WORK_QUEUE = new WorkQueue(10);

    private Queue<IAction0> workQueue = new LinkedList<>();
    private int jobLimitMs;

    public WorkQueue(int jobLimitMs) {
        this.jobLimitMs = jobLimitMs;
    }

    public void addWorkUnit(IAction0 action) {
        this.workQueue.add(action);
    }

    public void update() {
        var currentTime = System.currentTimeMillis();
        while(!this.workQueue.isEmpty()) {
            if(System.currentTimeMillis() - currentTime > this.jobLimitMs) {
                break;
            }
            this.workQueue.remove().invoke();
        }
    }

}
