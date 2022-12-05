package dreamlink.world.room.module.doortemplate;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class DoorTemplateModule {

    private static final Path doorTemplateDirectory = Paths.get("pack/doortemplate");
    private static final String configSuffix = ".json";

    private final List<DoorTemplate> doorTemplates = new ArrayList<>();
    private final Map<String, DoorTemplate> doorTemplateLookup = new HashMap<>();
    private final DoorTemplateLoader doorTemplateLoader;
    private final IRoomModuleProvider provider;
    private boolean isWorkCompleted;

    public DoorTemplateModule(IRoomModuleProvider provider) {
        this.provider = provider;
        this.doorTemplateLoader = new DoorTemplateLoader(provider);
    }

    public void loadData() throws RoomLoadException {
        var roomPath = this.provider.getIOModule().getPath();
        var doorTemplateDirectoryFile = roomPath
            .resolve(DoorTemplateModule.doorTemplateDirectory)
            .toFile();
            
        this.doorTemplateLookup.put(DoorTemplate.defaultDoorTemplate.name, DoorTemplate.defaultDoorTemplate);
        this.doorTemplates.add(DoorTemplate.defaultDoorTemplate);

        if(!doorTemplateDirectoryFile.exists()) {
            return;
        }

        for(var templateFile : doorTemplateDirectoryFile.listFiles(File::isFile)) {
            var fileName = templateFile.getName();
            if(!fileName.endsWith(DoorTemplateModule.configSuffix)) {
                continue;
            }

            var doorTemplate = this.doorTemplateLoader.loadFromFile(templateFile);
            this.doorTemplateLookup.put(fileName, doorTemplate);
            this.doorTemplates.add(doorTemplate);
        }
    }

    public int getSize() {
        return this.doorTemplateLookup.size();
    }

    public DoorTemplate getDoorTemplateByIndex(int index) {
        return this.doorTemplates.get(index);
    }

    public DoorTemplate getDoorTemplateByName(String name) {
        return this.doorTemplateLookup.get(name);
    }

    public boolean doWork() {
        if(this.isWorkCompleted) {
            return false;
        }

        for(var doorTemplate : this.doorTemplateLookup.values()) {
            doorTemplate.setup();
        }
        
        this.isWorkCompleted = true;
        return true;
    }

    public void destroy() {
        if(this.isWorkCompleted) {
            for(var doorTemplate : this.doorTemplateLookup.values()) {
                doorTemplate.destroy();
            }
        }
    }
    
}
