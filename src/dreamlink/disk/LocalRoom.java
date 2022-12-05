package dreamlink.disk;

import java.io.File;

public class LocalRoom {

    public final String roomName;
    public final File file; 

    public LocalRoom(String roomName, File file) {
        this.roomName = roomName;
        this.file = file;
    }
    
}
