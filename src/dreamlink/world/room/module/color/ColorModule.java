package dreamlink.world.room.module.color;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.joml.Vector3f;

import dreamlink.utility.file.FileFns;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.json.JSONAccessException;
import dreamlink.utility.json.JSONDecodeException;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class ColorModule {

    private static final Path environmentConfigPath = Paths.get("pack/color.json");
    private static final String colorKeyTemplate = "color.%s";

    private final IRoomModuleProvider provider;
    private final Vector3f[] colors;

    public ColorModule(IRoomModuleProvider provider) {
        this.provider = provider;
        this.colors = new Vector3f[ColorConfig.getSize()];
        for(var ix = 0; ix < this.colors.length; ix += 1) {
            this.colors[ix] = new Vector3f();
        }
    }
    
    public Vector3f getColor(Vector3f target, ColorConfig config) {
        return target.set(this.colors[config.getIndex()]);
    }


    public void loadData() throws RoomLoadException {
        var roomPath = this.provider.getIOModule().getPath();
        var configFile = roomPath
            .resolve(ColorModule.environmentConfigPath)
            .toFile();

        if(!configFile.exists()) {
            return;
        }

        try {
            var JSONData = FileFns.readStringFromFile(configFile);
            var config = new CheckedJSONObject(JSONData);
            for(var ix = 0; ix < this.colors.length; ix += 1) {
                var colorConfig = ColorConfig.get(ix);
                var configKey = String.format(
                    ColorModule.colorKeyTemplate, 
                    colorConfig.name
                );

                var array = config.optJSONArray(configKey);
                this.colors[ix].set(
                    array == null
                        ? colorConfig.defaultColor
                        : array.getVector3f(new Vector3f())
                );
            }
        } catch (IOException e) {
            throw RoomLoadException.invalidEnvironment();
        } catch (JSONDecodeException e) {
            throw RoomLoadException.invalidEnvironment();
        } catch (JSONAccessException e) {
            throw RoomLoadException.invalidEnvironment();
        }
    }

}
