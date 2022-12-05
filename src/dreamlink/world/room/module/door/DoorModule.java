package dreamlink.world.room.module.door;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.joml.Vector3i;

import dreamlink.utility.maths.Orientation;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;
import dreamlink.world.room.module.doortemplate.DoorTemplate;

public class DoorModule {

    private static final Path dataPath = Paths.get("door.dat");

    private final Queue<Integer> blockIDQueue = new ArrayDeque<>();
    private final Map<String, Door> doorNameMap = new HashMap<>();
    private final Map<Integer, Door> doorBlockIDMap = new HashMap<>();
    private final List<Door> doors = new ArrayList<>();
    private final IRoomModuleProvider provider;

    private static final int blockIDStart = 0x20;
    private static final int blockIDEnd = 0x3F;

    public DoorModule(IRoomModuleProvider provider) {
        this.provider = provider;

        for(var ix = DoorModule.blockIDStart; ix < DoorModule.blockIDEnd; ix += 1) {
            this.blockIDQueue.add(ix);
        }
    }

    private void registerDoor(Door door) {
        var blockModule = this.provider.getBlockModule();
        var stampModule = this.provider.getStampModule();
        blockModule.addBlock(door.block);
        stampModule.addStamp(door);
        this.doorBlockIDMap.put(door.block.getBlockID(), door);
        this.doorNameMap.put(door.name, door);
        this.doors.add(door);
    }

    public Door createDoor(
        DoorTemplate template,
        String name
    ) {
        var blockID = this.blockIDQueue.remove();
        var door = new Door(
            this.provider,
            new DoorBlock(provider, blockID),
            template,
            name
        );

        this.registerDoor(door);
        return door;
    }

    public void removeDoor(Door door) {
        var blockModule = this.provider.getBlockModule();
        var stampModule = this.provider.getStampModule();

        this.doors.remove(door);
        this.doorBlockIDMap.remove(door.block.getBlockID());
        this.doorNameMap.remove(door.name);
        this.blockIDQueue.add(door.block.getBlockID());
        stampModule.removeStamp(door);
        blockModule.removeBlock(door.block);
    }

    public Door getDoorByName(String name) {
        return this.doorNameMap.get(name);
    }

    public Door getDoorByBlockID(int blockID) {
        return this.doorBlockIDMap.get(blockID);
    }

    public int getSize() {
        return this.doors.size();
    }

    public Door getDoorByIndex(int index) {
        return this.doors.get(index);
    }

    public void loadData() throws RoomLoadException{
        var roomPath = this.provider.getIOModule().getPath();

        var doorsDataFile = roomPath
            .resolve(DoorModule.dataPath)
            .toFile();

        if(!doorsDataFile.exists()) {
            return;
        }

        try(
            var fileInputStream = new FileInputStream(doorsDataFile);
            var bufferedInputStream = new BufferedInputStream(fileInputStream);
            var dataInputStream = new DataInputStream(bufferedInputStream);
        ) {
            var numEntries = dataInputStream.readInt();
            for(var ix = 0; ix < numEntries; ix += 1) {
                var blockID = dataInputStream.readInt();
                var templateName = dataInputStream.readUTF();
                var name = dataInputStream.readUTF();
                var targetRoom = dataInputStream.readUTF();
                var targetName = dataInputStream.readUTF();
                var isPlaced = dataInputStream.readBoolean();

                var doorTemplate = this.provider.getDoorTemplateModule().getDoorTemplateByName(templateName);

                if(doorTemplate == null) {
                    throw RoomLoadException.invalidDoorData();
                }

                var door = new Door(
                    this.provider,
                    new DoorBlock(provider, blockID),
                    doorTemplate,
                    name
                );

                door.targetDoorName = targetName;
                door.targetRoomName = targetRoom;
                this.registerDoor(door);

                if(!this.blockIDQueue.remove(blockID)) {
                    throw RoomLoadException.invalidDoorData();
                }

                if(!isPlaced) {
                    continue;
                }

                door.setPlacement(
                    new Vector3i(
                        dataInputStream.readInt(),
                        dataInputStream.readInt(),
                        dataInputStream.readInt()
                    ),
                    Orientation.get(dataInputStream.readInt())
                );
            }

        } catch(IOException e) {
            throw RoomLoadException.invalidDoorData();
        }
    }

    public void saveData() {
        var roomPath = this.provider.getIOModule().getPath();

        var dataFile = roomPath
            .resolve(DoorModule.dataPath)
            .toFile();

        try(
            var fileOutputStream = new FileOutputStream(dataFile);
            var bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            var dataOutputStream = new DataOutputStream(bufferedOutputStream);
        ) {
            dataOutputStream.writeInt(this.doors.size());
            for(var ix = 0; ix < this.doors.size(); ix += 1) {
                var door = this.doors.get(ix);
                var isPlaced = door.isPlaced();
                dataOutputStream.writeInt(door.block.getBlockID());
                dataOutputStream.writeUTF(door.template.name);
                dataOutputStream.writeUTF(door.name);
                dataOutputStream.writeUTF(door.targetRoomName);
                dataOutputStream.writeUTF(door.targetDoorName);
                dataOutputStream.writeBoolean(isPlaced);

                if(isPlaced) {
                    var centerPosition = door.getCenterPosition(new Vector3i());
                    dataOutputStream.writeInt(centerPosition.x);
                    dataOutputStream.writeInt(centerPosition.y);
                    dataOutputStream.writeInt(centerPosition.z);
                    dataOutputStream.writeInt(door.getOrientation().getIndex());
                }
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
