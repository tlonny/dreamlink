package dreamlink.overlay.simulation.edit.door;

import dreamlink.world.room.module.door.Door;

public interface IDoorComponentProvider {

    public void gotoList();

    public void gotoCreate();

    public void gotoEdit(Door door);
    
}
