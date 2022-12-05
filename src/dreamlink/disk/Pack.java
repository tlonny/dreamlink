package dreamlink.disk;

import java.io.File;

public class Pack {

    public final String packName;
    public final File file; 

    public Pack(String packName, File file) {
        this.packName = packName;
        this.file = file;
    }
    
}
