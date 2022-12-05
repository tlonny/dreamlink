package dreamlink.disk;

import java.io.File;

public class Pack {

    public final String packName;
    public final File file; 

    public Pack(String roomName, File file) {
        this.packName = roomName;
        this.file = file;
    }
    
}
