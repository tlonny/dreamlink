package doors.core.ui.alignment;

import doors.core.ui.IUIElement;

public class HorizontalAlignmentWrapper {

    public HorizontalAlignment horizontalAlignment; 
    public IUIElement element;

    public HorizontalAlignmentWrapper(IUIElement element, HorizontalAlignment alignment) {
        this.element = element;
        this.horizontalAlignment = alignment;
    }

    public HorizontalAlignmentWrapper(IUIElement element) {
        this(element, HorizontalAlignment.CENTER);
    }

    public int getHorizontalOffset(int parentWidth) {
        return this.horizontalAlignment.getOffset(parentWidth, this.element.getDimensions().x);
    }

}
