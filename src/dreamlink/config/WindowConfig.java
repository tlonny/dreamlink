package dreamlink.config;

import java.io.IOException;

import dreamlink.utility.CommonPaths;
import dreamlink.utility.file.FileFns;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.json.JSONAccessException;
import dreamlink.utility.json.JSONDecodeException;

public class WindowConfig {

    private static final String configFilename = "window.json";
    private static final String isBorderlessKey = "is_borderless";
    private static final boolean defaultIsBorderless = true;

    public static final WindowConfig instance = new WindowConfig();

    private boolean isBorderless;

    public void load() {
        var file = CommonPaths.instance.configPath
            .resolve(WindowConfig.configFilename)
            .toFile();

        if(!file.exists()) {
            this.isBorderless = WindowConfig.defaultIsBorderless;
            return;
        }

        try {
            var data = FileFns.readStringFromFile(file);
            var config = new CheckedJSONObject(data);
            this.isBorderless = config.getBoolean(WindowConfig.isBorderlessKey);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONDecodeException e) {
            throw new RuntimeException(e);
        } catch (JSONAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getIsBorderless() {
        return this.isBorderless;
    }
    
}
