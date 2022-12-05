package dreamlink.world.room.module.skybox;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import dreamlink.utility.file.FileFns;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.json.JSONDecodeException;
import dreamlink.utility.maths.CubeFace;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class SkyBoxModule {

    private static final Path skyboxPath = Paths.get("pack/skybox.json");
    private static final String textureSampleKeyTemplate = "texture.sample.%s";

    private final IRoomModuleProvider provider;
    private boolean isWorkCompleted;
    private final SkyBox skyBox = new SkyBox();

    public SkyBoxModule(IRoomModuleProvider provider) {
        this.provider = provider;
        this.isWorkCompleted = false;
    }

    public SkyBox getSkyBox() {
        return this.skyBox;
    }

    public void loadData() throws RoomLoadException {
        var zonePath = this.provider.getIOModule().getPath();
        var configFile = zonePath
            .resolve(SkyBoxModule.skyboxPath)
            .toFile();

        if(!configFile.exists()) {
            return;
        }

        try {
            var data = FileFns.readStringFromFile(configFile);
            var config = new CheckedJSONObject(data);

            for(var ix = 0; ix < CubeFace.getSize(); ix +=1 ) {
                var cubeFace = CubeFace.get(ix);
                var textureSampleKey = String.format(
                    SkyBoxModule.textureSampleKeyTemplate,
                    cubeFace.name
                );

                var textureSampleName = config.optString(textureSampleKey, null);
                if(textureSampleName == null) {
                    continue;
                }

                var textureSample = this.provider.getTextureSampleModule().getTextureSample(textureSampleName);
                if(textureSample == null) {
                    throw RoomLoadException.missingTexture(textureSampleName);
                }

                this.skyBox.setSkyBoxTextureSample(cubeFace, textureSample);
            }
        } catch (IOException e) {
            throw RoomLoadException.invalidSkyBoxConfig();
        } catch (JSONDecodeException e) {
            throw RoomLoadException.invalidSkyBoxConfig();
        }
    }

    public boolean doWork() {
        if(this.isWorkCompleted) {
            return false;
        }

        this.skyBox.setup();
        this.isWorkCompleted = true;
        return true;
    }

    public void destroy() {
        if(this.isWorkCompleted) {
            this.skyBox.destroy();
        }
    }
}