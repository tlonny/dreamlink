package dreamlink.world.room.module;

import dreamlink.worker.WorkerPool;
import dreamlink.worker.WorkerTask;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class LoadModule {

    private final IRoomModuleProvider provider;
    private WorkerTask<String> task;
    private boolean isLoading;
    private boolean isLoaded;
    private String error;

    public LoadModule(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    public boolean isLoaded() {
        return this.isLoaded && this.error == null;
    }

    public boolean isLoading() {
        return this.isLoading;
    }

    public String getError() {
        return this.error;
    }

    public void load() {
        if(!this.isLoading) {
            this.error = null;
            this.isLoading = true;
            this.task = WorkerPool.instance.submit(this::loadData);
        }
    }

    private String loadData() {
        try {
            this.provider.getIOModule().loadData();
            this.provider.getTextureAtlasModule().loadData();
            this.provider.getTextureSampleModule().loadData();
            this.provider.getSkyBoxModule().loadData();
            this.provider.getSoundModule().loadData();
            this.provider.getColorModule().loadData();
            this.provider.getUserBlockModule().loadData();
            this.provider.getDoorTemplateModule().loadData();
            this.provider.getDoorModule().loadData();
            this.provider.getTerrainModule().loadData();
            this.provider.getSettingsModule().loadData();
            this.provider.getAmbienceModule().loadData();
            return null;
        } catch (RoomLoadException e) {
            return e.getMessage();
        }
    }

    public boolean doWork() {
        if(!this.isLoading || this.isLoaded || this.error != null) {
            return false;
        }

        if(!this.task.isDone()) {
            return false;
        }

        this.error = this.task.join();
        this.isLoaded = true;
        return true;
    }
    
}
