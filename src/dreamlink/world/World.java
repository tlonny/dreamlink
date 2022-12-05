package dreamlink.world;

import dreamlink.world.room.Room;
import dreamlink.world.room.module.door.Door;

public class World {

    private class InternalWorldModuleProvider implements IWorldModuleProvider {

        @Override
        public RoomStateModule getRoomStateModule() {
            return World.this.roomStateModule;
        }

        @Override
        public RoomCacheModule getRoomCacheModule() {
            return World.this.roomCacheModule;
        }

    }

    public static final World instance = new World();

    private final RoomStateModule roomStateModule;
    private final RoomCacheModule roomCacheModule;
    private final AdjacencyModule adjacencyModule;
    private final RenderModule renderModule;

    public World() {
        var provider = new InternalWorldModuleProvider();
        this.roomCacheModule = new RoomCacheModule(provider);
        this.roomStateModule = new RoomStateModule(provider);
        this.adjacencyModule = new AdjacencyModule(provider);
        this.renderModule = new RenderModule(provider);
    }

    public void teleport() {
        this.roomStateModule.teleport();
    }

    public Room getRoom() {
        return this.roomStateModule.getRoom();
    }

    public Door getOpenDoor() {
        return this.roomStateModule.getOpenDoor();
    }

    public float getDoorOpenFactor() {
        return this.roomStateModule.getDoorOpenFactor();
    }

    public void setRoom(String name) {
        this.roomStateModule.setRoom(name);
    }

    public void update() {
        this.roomCacheModule.update();
        this.roomStateModule.update();
        this.adjacencyModule.update();
    }

    public void clear() {
        this.roomCacheModule.clear();
    }

    public void render() {
        this.renderModule.render();
    }
    
}
