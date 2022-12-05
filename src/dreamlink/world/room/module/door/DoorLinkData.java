package dreamlink.world.room.module.door;

import dreamlink.world.room.Room;

public class DoorLinkData {

    public Room targetRoom;
    public Door targetDoor;

    public DoorLinkData set(Room targetRoom, Door targetDoor) {
        this.targetRoom = targetRoom;
        this.targetDoor = targetDoor;
        return this;
    }

    public DoorLinkData set(DoorLinkData source) {
        this.targetRoom = source.targetRoom;
        this.targetDoor = source.targetDoor;
        return this;
    }
    
}
