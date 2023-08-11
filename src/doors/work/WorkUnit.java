package doors.work;

import java.util.Collection;
import java.util.HashSet;

import doors.utility.Functional.IAction0;

public class WorkUnit {

    public boolean useWorkerThread;
    public IAction0 workFunction;

    private boolean isDone = false;
    private Collection<WorkUnit> dependencies = new HashSet<>();

    public WorkUnit(IAction0 workFunction, boolean useWorkerThread) {
        this.workFunction = workFunction;
        this.useWorkerThread = useWorkerThread;
    }

    public WorkUnit(IAction0 jobFunction) {
        this(jobFunction, false);
    }

    public WorkUnit() {
        this(null, false);
    }

    public void registerDependency(WorkUnit dependency) {
        this.dependencies.add(dependency);
    }

    public int getDependencyCount() {
        return this.dependencies.size();
    }

    public boolean isReady() {
        var isReady = true;
        for(var dependency : this.dependencies) {
            if(!dependency.isDone()) {
                isReady = false;
                break;
            }

            // Remove the dependency if it is done which will
            // save us from having to check it again in the future.
            this.dependencies.remove(dependency);
        }
        return isReady;
    }

    public boolean isDone() {
        synchronized(this) {
            return this.isDone;
        }
    }

    private void setDone() {
        synchronized(this) {
            this.isDone = true;
        }
    }

    public void invoke() {
        if(this.workFunction != null) {
            this.workFunction.invoke();
        }
        this.setDone();
    }

    public void submit() {
        WorkQueue.WORK_QUEUE.submitJob(this);
    }
    
}
