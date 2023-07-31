package doors.ui.component.layout.box;

public class WrapDimension implements IDimension {

    public int getAvailableSpace(int availableSpace) {
        return availableSpace;
    }

    // We want to wrap the child snugly, so our dimension is just enough to fit the child and
    // thus we ignore the available space.
    public int calculateDimension(int childDimensions, int availableSpace) {
        return childDimensions;
    }

}
