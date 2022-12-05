package dreamlink.config;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import dreamlink.utility.CommonPaths;

public class UserSettingsConfig {

    private static final String configFilename = "user_settings.dat";

    private static final float defaultMouseSensitivity = 0.25f;

    public static final UserSettingsConfig instance = new UserSettingsConfig();

    public String uploadKey;
    public float mouseSensitivity;

    public void load() {
        var file = CommonPaths.instance.dataPath
            .resolve(UserSettingsConfig.configFilename)
            .toFile();

        if(!file.exists()) {
            this.uploadKey = "";
            this.mouseSensitivity = UserSettingsConfig.defaultMouseSensitivity;
            return;
        }

        try(
            var fileInputStream = new FileInputStream(file);
            var bufferedInputStream = new BufferedInputStream(fileInputStream);
            var dataInputStream = new DataInputStream(bufferedInputStream);
        ) {
            this.uploadKey = dataInputStream.readUTF();
            this.mouseSensitivity = dataInputStream.readFloat();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        var file = CommonPaths.instance.dataPath
            .resolve(UserSettingsConfig.configFilename)
            .toFile();

        try(
            var fileOutputStream = new FileOutputStream(file);
            var bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            var dataOutputStream = new DataOutputStream(bufferedOutputStream);
        ) {
            dataOutputStream.writeUTF(this.uploadKey);
            dataOutputStream.writeFloat(this.mouseSensitivity);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
