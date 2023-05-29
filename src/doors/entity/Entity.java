package doors.entity;

import doors.Game;

public abstract class Entity {

    public Entity() {
        Game.ENTITY_TRANSITION_MANAGER.addEntity(this);
    }

    public void destroy() {
        Game.ENTITY_TRANSITION_MANAGER.removeEntity(this);
    }

    public abstract void setup();

    public abstract void tearDown();

}
