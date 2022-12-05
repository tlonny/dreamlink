package dreamlink.disk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dreamlink.utility.CommonPaths;
import dreamlink.utility.StringFns;
import dreamlink.utility.file.FileFns;

public class LocalRoomState {

    public static LocalRoomState instance = new LocalRoomState();

    private final Map<String, LocalRoom> roomMap = new HashMap<>();
    private final List<LocalRoom> rooms = new ArrayList<>();

    public void setup() {
        if(!CommonPaths.instance.localRoomsPath.toFile().exists()) {
            return;
        }

        for(var file : CommonPaths.instance.localRoomsPath.toFile().listFiles()) {
            var fileName = file.getName();
            var localRoom = new LocalRoom(StringFns.urlDecode(fileName), file);
            this.roomMap.put(localRoom.roomName, localRoom);
            this.rooms.add(localRoom);
        }
    }

    public int getSize() {
        return this.rooms.size();
    }

    public LocalRoom getRoomByIndex(int index) {
        return this.rooms.get(index);
    }

    public LocalRoom addRoom(String name) {
        var encodedName = StringFns.urlEncode(name);
        var file = CommonPaths.instance.localRoomsPath.resolve(encodedName).toFile();
        file.mkdir();
        var room = new LocalRoom(name, file);
        this.rooms.add(room);
        this.roomMap.put(name, room);
        return room;
    }

    public boolean hasRoom(String name) {
        return this.roomMap.containsKey(name);
    }

    public LocalRoom getRoom(String name) {
        return this.roomMap.get(name);
    }

    public void removeRoom(String name) {
        var room = this.roomMap.remove(name);
        this.rooms.remove(room);
        FileFns.deleteDirectory(room.file);
    }

    public void copyRoom(String sourceName, String targetName) {
        var sourceRoom = this.roomMap.get(sourceName);
        var encodedName = StringFns.urlEncode(targetName);
        var newFile = CommonPaths.instance.localRoomsPath.resolve(encodedName).toFile();
        var newRoom = new LocalRoom(targetName, newFile);
        this.rooms.add(newRoom);
        this.roomMap.put(targetName, newRoom);
        FileFns.copyDirectory(sourceRoom.file, newFile);
    }

    public void renameRoom(String oldName, String newName) {
        var oldRoom = this.roomMap.remove(oldName);
        this.rooms.remove(oldRoom);

        var encodedName = StringFns.urlEncode(newName);
        var newFile = CommonPaths.instance.localRoomsPath.resolve(encodedName).toFile();
        var newRoom = new LocalRoom(newName, newFile);
        oldRoom.file.renameTo(newRoom.file);
        this.rooms.add(newRoom);
        this.roomMap.put(newName, newRoom);
    }
    
}
