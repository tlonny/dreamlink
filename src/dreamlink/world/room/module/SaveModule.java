package dreamlink.world.room.module;

import dreamlink.worker.WorkerPool;
import dreamlink.worker.WorkerTask;
import dreamlink.world.room.IRoomModuleProvider;

public class SaveModule {

    private final IRoomModuleProvider provider;
    private boolean isSaving;
    private WorkerTask<?> task;

    public SaveModule(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    public void save() {
        if(!this.isSaving) {
            this.isSaving = true;
            this.task = WorkerPool.instance.submit(this::saveData);
        }
    }

    private void saveData() {
        this.provider.getUserBlockModule().saveData();
        this.provider.getTerrainModule().saveData();
        this.provider.getDoorModule().saveData();
        this.provider.getSettingsModule().saveData();
    }

    public boolean doWork() {
        if(this.isSaving && this.task.isDone()) {
            this.isSaving = false;
            return true;
        }

        return false;
    }

    public boolean isSaving() {
        return this.isSaving;
    }
    
}
