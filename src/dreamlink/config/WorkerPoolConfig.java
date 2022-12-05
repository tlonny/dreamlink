package dreamlink.config;

import java.io.IOException;

import dreamlink.utility.CommonPaths;
import dreamlink.utility.file.FileFns;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.json.JSONAccessException;
import dreamlink.utility.json.JSONDecodeException;

public class WorkerPoolConfig {

    private static final String configFilename = "worker_pool.json";
    private static final String numThreadsKey = "num_threads";
    private static final int defaultNumThreads = Runtime.getRuntime().availableProcessors();
    private static final int minNumThreads = 1;

    public static final WorkerPoolConfig instance = new WorkerPoolConfig();

    private int numThreads;

    public void load() {
        var file = CommonPaths.instance.configPath
            .resolve(WorkerPoolConfig.configFilename)
            .toFile();

        if(!file.exists()) {
            this.numThreads = WorkerPoolConfig.defaultNumThreads;
            return;
        }

        try {
            var data = FileFns.readStringFromFile(file);
            var config = new CheckedJSONObject(data);
            this.numThreads = Math.max(
                config.getInt(WorkerPoolConfig.numThreadsKey),
                WorkerPoolConfig.minNumThreads
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONDecodeException e) {
            throw new RuntimeException(e);
        } catch (JSONAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNumThreads() {
        return this.numThreads;
    }
    
}
