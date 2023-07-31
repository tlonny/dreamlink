package doors.ui.component.layout.box;

public class FixedDimension implements IDimension {

    public int value;

    public FixedDimension(int value) {
        this.value = value;
    }

    public int getAvailableSpace(int availableSpace) {
        return this.value;
    }

    public int calculateDimension(int childDimension, int availableSpace) {
        return this.value;
    }
}
