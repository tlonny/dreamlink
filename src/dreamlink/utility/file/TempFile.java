package dreamlink.utility.file;

import java.io.File;
import java.io.IOException;

public class TempFile implements AutoCloseable {

    public final File file; 

    public TempFile(String suffix) {
        try {
            this.file = File.createTempFile("tmp", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if(this.file.exists()) {
            this.file.delete();
        }
    }
    
}
