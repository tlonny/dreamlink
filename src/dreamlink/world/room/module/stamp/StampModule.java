package dreamlink.world.room.module.stamp;

import java.util.ArrayList;
import java.util.List;

public class StampModule {

    private final List<IStamp> stamps = new ArrayList<>();

    public IStamp getStampByIndex(int index) {
        return this.stamps.get(index);
    }

    public int getSize() {
        return this.stamps.size();
    }

    public void addStamp(IStamp stamp) {
        this.stamps.add(stamp);
    }

    public void removeStamp(IStamp stamp) {
        this.stamps.remove(stamp);
    }
    
}
