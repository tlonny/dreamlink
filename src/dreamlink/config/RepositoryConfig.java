package dreamlink.config;

import java.io.IOException;
import java.net.URI;

import dreamlink.utility.CommonPaths;
import dreamlink.utility.file.FileFns;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.json.JSONAccessException;
import dreamlink.utility.json.JSONDecodeException;

public class RepositoryConfig {

    private static final String configFilename = "repository.json";
    private static final String originKey = "origin";
    private static final URI defaultOrigin = URI.create("https://dreamlink.lonny.io");

    public static final RepositoryConfig instance = new RepositoryConfig();

    private URI origin;

    public void load() {
        var file = CommonPaths.instance.configPath
            .resolve(RepositoryConfig.configFilename)
            .toFile();

        if(!file.exists()) {
            this.origin = RepositoryConfig.defaultOrigin;
            return;
        }

        try {
            var data = FileFns.readStringFromFile(file);
            var config = new CheckedJSONObject(data);
            this.origin = URI.create(config.getString(RepositoryConfig.originKey));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONDecodeException e) {
            throw new RuntimeException(e);
        } catch (JSONAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public URI getOrigin() {
        return this.origin;
    }
    
}
