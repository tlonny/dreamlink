package doors.core.config;

import java.lang.reflect.InvocationTargetException;

import doors.core.utility.FileIO;
import doors.core.utility.vector.Vector2in;

public class Config implements IConfigData {

    private static final String CONFIG_DATA_CLASS_REF_PATH = "data/configDataClass";
    public static Config CONFIG = new Config();

    private IConfigData configData;

    public Config() {
        try {
            var fullyQualifiedClassName = FileIO.loadText(CONFIG_DATA_CLASS_REF_PATH).strip();
            this.configData = (IConfigData)Class.forName(fullyQualifiedClassName).getConstructors()[0].newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch(InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch(IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch(InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Vector2in getResolution() {
        return this.configData.getResolution();
    }

    @Override
    public Vector2in getMaxImageTextureDimensions() {
        return this.configData.getMaxImageTextureDimensions();
    }

}
