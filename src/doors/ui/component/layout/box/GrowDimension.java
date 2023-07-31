package doors.ui.component.layout.box;

public class GrowDimension implements IDimension {

    // We want to grow to fill the available space, so our dimension is all
    // the available space vs. just enough to fit the child.
    public int calculateDimension(int childDimension, int availableSpace) {
        return availableSpace;
    }

    public int getAvailableSpace(int availableSpace) {
        return availableSpace;
    }

}
