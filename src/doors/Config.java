package doors;

import org.json.JSONObject;

import doors.utility.FileIO;
import doors.utility.vector.Vector2in;

public class Config {

    public static Config CONFIG = new Config();

    private Vector2in resolution;
    private String workspacePath;
    private String cachePath;

    public Config() {
        var configPath = System.getenv("CONFIG_PATH");
        var configString = FileIO.loadText(configPath);
        var config = new JSONObject(configString);

        var resolutionArray = config.getJSONArray("resolution");
        this.resolution = new Vector2in(
            resolutionArray.getInt(0),
            resolutionArray.getInt(1)
        );

        this.workspacePath = config.getString("workspacePath");
        this.cachePath = config.getString("cachePath");
    }

    public Vector2in getResolution() {
        return this.resolution;
    }

    public String getWorkspacePath() {
        return this.workspacePath;
    }

    public String getCachePath() {
        return this.cachePath;
    }

}
