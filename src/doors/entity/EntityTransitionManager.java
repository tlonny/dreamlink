package doors.entity;

import java.util.HashSet;
import java.util.Set;

public class EntityTransitionManager {

    private Set<Entity> entitiesToRemove = new HashSet<>(); 
    private Set<Entity> entitiesToAdd = new HashSet<>(); 

    public void addEntity(Entity entity) {
        this.entitiesToAdd.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entitiesToRemove.add(entity);
    }

    public void transition() {
        for(var entity : this.entitiesToAdd) {
            entity.setup();
        }
        for(var entity : this.entitiesToRemove) {
            entity.tearDown();
        }
        this.entitiesToAdd.clear();
        this.entitiesToRemove.clear();
    }

}
