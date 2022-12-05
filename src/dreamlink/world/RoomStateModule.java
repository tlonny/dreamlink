package dreamlink.world;

import dreamlink.world.room.Room;
import dreamlink.world.room.module.door.Door;
import dreamlink.world.room.module.door.DoorLinkData;

public class RoomStateModule {

    private static final float doorOpenFactorDelta = 0.05f;

    private final IWorldModuleProvider provider;

    private Room room;
    private Door openDoor;
    private boolean isDoorPull;

    private Door nextOpenDoor;
    private boolean nextIsDoorPull;
    private boolean nextHasUpdate;
    private float doorOpenFactor;

    public RoomStateModule(IWorldModuleProvider provider) {
        this.provider = provider;
    }

    public void setRoom(String name) {
        this.room = this.provider.getRoomCacheModule().getRoom(name);
        this.openDoor = null;
        this.nextHasUpdate = false;
    }

    private final DoorLinkData teleportLinkData = new DoorLinkData();

    public void teleport() {
        var linkData = this.openDoor.resolveLink(this.teleportLinkData);
        this.room = linkData.targetRoom;
        this.openDoor = linkData.targetDoor;
        this.isDoorPull = !this.isDoorPull;
        this.nextHasUpdate = false;
    }

    public Room getRoom() {
        return this.room;
    }

    public Door getOpenDoor() {
        return this.openDoor;
    }

    public float getDoorOpenFactor() {
        return this.doorOpenFactor;
    }

    public boolean isDoorPull() {
        return this.isDoorPull;
    }

    public void setOpenDoor(Door door, boolean isDoorPull) {
        this.nextOpenDoor = door == this.openDoor ? null : door;
        this.nextIsDoorPull = isDoorPull;
        this.nextHasUpdate = true;
    }

    private DoorLinkData updateLinkData = new DoorLinkData();

    public void update() {
        if(this.nextHasUpdate) {
            this.doorOpenFactor = Math.max(0f, this.doorOpenFactor - doorOpenFactorDelta);
        } else if(this.openDoor != null) {
            this.doorOpenFactor = Math.min(1f, this.doorOpenFactor + doorOpenFactorDelta);
        }

        if(this.doorOpenFactor == 0f && this.nextHasUpdate) {
            if(this.nextOpenDoor != null) {
                var linkData = this.nextOpenDoor.resolveLink(this.updateLinkData);
                this.nextOpenDoor.setPortalLight();
                linkData.targetDoor.setPortalLight();
            }

            this.nextHasUpdate = false;
            this.openDoor = this.nextOpenDoor;
            this.isDoorPull = this.nextIsDoorPull;
        }
    }
    
}
