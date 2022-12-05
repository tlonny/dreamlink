package dreamlink.overlay.home.edit;

import dreamlink.disk.LocalRoom;

public interface IHomeEditComponentProvider {

    public void gotoList();

    public void gotoCreate();

    public void gotoEdit(LocalRoom room);
    
}
