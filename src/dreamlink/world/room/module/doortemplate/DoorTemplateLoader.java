package dreamlink.world.room.module.doortemplate;

import java.io.File;
import java.io.IOException;

import dreamlink.graphics.texture.sample.EntityTextureSample;
import dreamlink.utility.file.FileFns;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.json.JSONAccessException;
import dreamlink.utility.json.JSONDecodeException;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class DoorTemplateLoader {

    private static final String textureSampleKey = "texture.sample";
    private static final String iconTextureSampleKey = "texture.sample.icon";

    private final IRoomModuleProvider provider;

    public DoorTemplateLoader(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    public DoorTemplate loadFromFile(File file) throws RoomLoadException {
        try {
            var textureSampleModule = this.provider.getTextureSampleModule();
            var data = FileFns.readStringFromFile(file);
            var config = new CheckedJSONObject(data);

            var textureSampleKey = config.getString(DoorTemplateLoader.textureSampleKey);
            var textureSample = textureSampleModule.getTextureSample(textureSampleKey);
            if(textureSample == null) {
                throw RoomLoadException.missingTexture(DoorTemplateLoader.textureSampleKey);
            }

            var iconSampleKey = config.optString(DoorTemplateLoader.iconTextureSampleKey, null);
            var iconSample = textureSampleModule.getTextureSample(iconSampleKey);

            return new DoorTemplate(
                file.getName(),
                textureSample,
                iconSample == null ? EntityTextureSample.placeholder : iconSample
            );
        } catch (IOException e) {
            throw RoomLoadException.invalidDoorTemplate(file.getName());
        } catch (JSONDecodeException e) {
            throw RoomLoadException.invalidDoorTemplate(file.getName());
        } catch (JSONAccessException e) {
            throw RoomLoadException.invalidDoorTemplate(file.getName());
        }
    }
    
}
