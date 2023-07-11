package doors.core;

import java.util.LinkedList;
import java.util.Queue;

import doors.core.utility.Functional.IAction0;

public class WorkQueue {

    private static int JOB_LIMIT_MS = 10;

    public static WorkQueue WORK_QUEUE = new WorkQueue();

    private Queue<IAction0> workQueue = new LinkedList<>();

    public void addWorkUnit(IAction0 action) {
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
