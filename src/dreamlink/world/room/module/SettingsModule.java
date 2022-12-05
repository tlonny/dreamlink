package dreamlink.world.room.module;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import dreamlink.utility.maths.FloatMaths;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class SettingsModule {

    private static final Path settingsPath = Paths.get("settings.dat");
    private static final float defaultBaseLight = 0.5f;
    private static final boolean defaultIsLightingEnabled = true;
    private static final boolean defaultIsFogEnabled = false;
    private static final boolean defaultIsSkyBoxEnabled = true;
    private static final boolean defaultIsAmbientSoundEnabled = true;
    private static final float defaultFogStartValue = 8;
    private static final float defaultFogRangeValue = 32;
    private static final float defaultMasterGain = 1f;

    private final IRoomModuleProvider provider;

    public boolean isLightingEnabled;
    public boolean isFogEnabled;
    public boolean isSkyBoxEnabled;
    public boolean isAmbientSoundEnabled;
    public float masterGain;
    public float baseLight;
    public float fogStartDistance;
    public float fogRangeDistance;

    public SettingsModule(IRoomModuleProvider provider) {
        this.provider = provider;
        this.isLightingEnabled = SettingsModule.defaultIsLightingEnabled;
        this.isFogEnabled = SettingsModule.defaultIsFogEnabled;
        this.isSkyBoxEnabled = SettingsModule.defaultIsSkyBoxEnabled;
        this.isAmbientSoundEnabled = SettingsModule.defaultIsAmbientSoundEnabled;
        this.baseLight = SettingsModule.defaultBaseLight;
        this.fogStartDistance = SettingsModule.defaultFogStartValue;
        this.fogRangeDistance = SettingsModule.defaultFogRangeValue;
        this.masterGain = SettingsModule.defaultMasterGain;
    }

    public void loadData() throws RoomLoadException {
        var roomPath = this.provider.getIOModule().getPath();
        var settingsFile = roomPath
            .resolve(SettingsModule.settingsPath)
            .toFile();

        if(!settingsFile.exists()) {
            return;
        }

        try(
            var fileInputStream = new FileInputStream(settingsFile);
            var bufferedInputStream = new BufferedInputStream(fileInputStream);
            var dataInputStream = new DataInputStream(bufferedInputStream);
        ) {
            this.isLightingEnabled = dataInputStream.readBoolean();
            this.isSkyBoxEnabled = dataInputStream.readBoolean();
            this.isFogEnabled = dataInputStream.readBoolean();
            this.isAmbientSoundEnabled = dataInputStream.readBoolean();
            this.masterGain = FloatMaths.clamp(dataInputStream.readFloat(), 0f, 1f);
            this.baseLight = FloatMaths.clamp(dataInputStream.readFloat(), 0f, 1f);
            this.fogStartDistance = dataInputStream.readFloat();
            this.fogRangeDistance = dataInputStream.readFloat();
        } catch (IOException e) {
            throw RoomLoadException.invalidSettings();
        }

    }

    public void saveData() {
        var roomPath = this.provider.getIOModule().getPath();
        var settingsFile = roomPath
            .resolve(SettingsModule.settingsPath)
            .toFile();

        try(
            var fileOutputStream = new FileOutputStream(settingsFile);
            var bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            var dataOutputStream = new DataOutputStream(bufferedOutputStream);
        ) {
            dataOutputStream.writeBoolean(this.isLightingEnabled);
            dataOutputStream.writeBoolean(this.isSkyBoxEnabled);
            dataOutputStream.writeBoolean(this.isFogEnabled);
            dataOutputStream.writeBoolean(this.isAmbientSoundEnabled);
            dataOutputStream.writeFloat(this.masterGain);
            dataOutputStream.writeFloat(this.baseLight);
            dataOutputStream.writeFloat(this.fogStartDistance);
            dataOutputStream.writeFloat(this.fogRangeDistance);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    
}
