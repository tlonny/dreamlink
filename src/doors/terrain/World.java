package doors.terrain;

import java.util.HashSet;
import java.util.Set;

public class World {

    public Set<Terrain> activeTerrains = new HashSet<>();

    public void render() {
        for(var terrain : this.activeTerrains) {
            terrain.render();
        }
    }

}
