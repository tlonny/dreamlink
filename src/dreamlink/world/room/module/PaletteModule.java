package dreamlink.world.room.module;

import dreamlink.world.room.module.stamp.IStamp;

public class PaletteModule {

    public static final int numSlots = 10;
    private final IStamp[] stamps = new IStamp[PaletteModule.numSlots];
    private int selectedSlotIndex;

    public void setStamp(int index, IStamp stamp) {
        this.stamps[index] = stamp;
    }

    public IStamp getStamp(int index) {
        return this.stamps[index];
    }

    public int getSelectedStampSlotIndex() {
        return this.selectedSlotIndex;
    }

    public void setSelectedStampSlotIndex(int index) {
        this.selectedSlotIndex = index;
    }

    public void setSelectedStamp(IStamp stamp) {
        this.stamps[this.selectedSlotIndex] = stamp;
    }

    public IStamp getSelectedStamp() {
        return this.stamps[this.selectedSlotIndex];
    }
    
}
