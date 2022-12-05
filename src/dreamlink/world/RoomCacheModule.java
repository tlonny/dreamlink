package dreamlink.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dreamlink.world.room.Room;

public class RoomCacheModule {

    private record RoomCacheKey(String roomKey, boolean isShadowCopy) { }

    private static final int cacheMaxSize = 32;

    private final IWorldModuleProvider provider;
    private final ArrayList<RoomCacheKey> accessList = new ArrayList<>();
    private final Map<RoomCacheKey,Room> roomLookup = new HashMap<>();

    public RoomCacheModule(IWorldModuleProvider provider) {
        this.provider = provider;
    }

    public void clear() {
        for(var room : this.roomLookup.values()) {
            room.destroy();
        }
        this.accessList.clear();
        this.roomLookup.clear();
    }

    public Room getRoom(String roomKey) {
        return this.getRoom(roomKey, false);
    }

    public Room getRoom(String name, boolean isShadowCopy) {
        var cacheKey = new RoomCacheKey(name, isShadowCopy);
        if(!this.roomLookup.containsKey(cacheKey)) {
            var room = new Room(this.provider, name, isShadowCopy);
            this.roomLookup.put(cacheKey, room);

        // If the room already exists, remove it from the access list (it might be
        // near the end), so that we can re-add it to the front.
        } else {
            this.accessList.remove(cacheKey);
        }

        var room = this.roomLookup.get(cacheKey);
        this.accessList.add(cacheKey);

        // If our cache is too big, start removing rooms at the end of our
        // access list (LRU behavior).
        while(this.accessList.size() > RoomCacheModule.cacheMaxSize) {
            var lastCacheKey = this.accessList.remove(0);
            var lastRoom = this.roomLookup.remove(lastCacheKey);
            lastRoom.destroy();
        }

        return room;
    }

    public void update() {
        for(var ix = 0; ix < this.accessList.size(); ix += 1) {
            var cacheKey = this.accessList.get(ix);
            if(this.roomLookup.get(cacheKey).doWork()) {
                break;
            }
        }
    }
}