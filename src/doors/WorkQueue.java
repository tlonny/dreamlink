package doors;

import java.util.LinkedList;
import java.util.Queue;

import doors.utility.Functional.IAction;

public class WorkQueue {

    private static int JOB_LIMIT_MS = 10;

    public static WorkQueue WORK_QUEUE = new WorkQueue();

    private Queue<IAction> workQueue = new LinkedList<>();

    public void addWorkUnit(IAction action) {
        this.workQueue.add(action);
    }

    public void update() {
        var currentTime = System.currentTimeMillis();
        while(!this.workQueue.isEmpty()) {
            if(System.currentTimeMillis() - currentTime > JOB_LIMIT_MS) {
                break;
            }
            this.workQueue.remove().invoke();
        }
    }



}
