package doors.core.ui.alignment;

import doors.core.ui.IUIElement;

public class VerticalAlignmentWrapper {

    public VerticalAlignment verticalAlignment; 
    public IUIElement element;

    public VerticalAlignmentWrapper(IUIElement element, VerticalAlignment alignment) {
        this.element = element;
        this.verticalAlignment = alignment;
    }

    public VerticalAlignmentWrapper(IUIElement element) {
        this(element, VerticalAlignment.CENTER);
    }

    public int getVerticalOffset(int parentHeight) {
        return this.verticalAlignment.getOffset(parentHeight, this.element.getDimensions().y);
    }

}
