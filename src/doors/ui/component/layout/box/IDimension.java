package doors.ui.component.layout.box;

public interface IDimension {

    // Given a certain amount of available space, determine how much of that space
    // is available to the child.
    int getAvailableSpace(int availableSpace);

    // Determine the size of the dimension using the child's dimensions and additional available space
    int calculateDimension(int childDimensions, int availableSpace);
    
}
