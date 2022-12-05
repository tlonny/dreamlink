package dreamlink.world.room.module.texturesample;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import dreamlink.graphics.texture.sample.TextureSample;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class TextureSampleModule {

    private static Path samplesDirectory = Paths.get("pack/texture/sample");
    private static String sampleSuffix = ".json";
    
    private final IRoomModuleProvider provider;
    private final TextureSampleLoader textureSampleLoader;
    private final Map<String, TextureSample> textureSampleLookup = new HashMap<>();

    public TextureSampleModule(IRoomModuleProvider provider) {
        this.provider = provider;
        this.textureSampleLoader = new TextureSampleLoader(provider);
    }

    public TextureSample getTextureSample(String name) {
        return this.textureSampleLookup.get(name);
    }

    public void loadData() throws RoomLoadException {
        var roomPath = this.provider.getIOModule().getPath();
        var samplesDirectoryFile = roomPath
            .resolve(TextureSampleModule.samplesDirectory)
            .toFile();

        if(!samplesDirectoryFile.exists()) {
            return;
        }

        for(var sampleFile : samplesDirectoryFile.listFiles(File::isFile)) {
            var fileName = sampleFile.getName(); 
            if(!fileName.endsWith(TextureSampleModule.sampleSuffix)) {
                continue;
            }

            try {
                var textureSample = this.textureSampleLoader.loadFromFile(sampleFile);
                this.textureSampleLookup.put(fileName, textureSample);
            } catch (JSONException e) {
                throw RoomLoadException.invalidTextureSample(fileName);
            }
        }
    }
}
