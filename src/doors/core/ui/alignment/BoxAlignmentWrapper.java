package doors.core.ui.alignment;

import doors.core.ui.IUIElement;
import doors.core.utility.vector.Vector2in;

public class BoxAlignmentWrapper {

    public VerticalAlignment verticalAlignment; 
    public HorizontalAlignment horizontalAlignment; 
    public IUIElement element;

    public BoxAlignmentWrapper(IUIElement element, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        this.element = element;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
    }

    public BoxAlignmentWrapper(IUIElement element, HorizontalAlignment horizontalAlignment) {
        this(element, horizontalAlignment, VerticalAlignment.CENTER);
    }

    public BoxAlignmentWrapper(IUIElement element, VerticalAlignment verticalAlignment) {
        this(element, HorizontalAlignment.CENTER, verticalAlignment);
    }

    public BoxAlignmentWrapper(IUIElement element) {
        this(element, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
    }

    public Vector2in getOffset(Vector2in parentDimensions) {
        return new Vector2in(
            this.horizontalAlignment.getOffset(parentDimensions.x, this.element.getDimensions().x),
            this.verticalAlignment.getOffset(parentDimensions.y, this.element.getDimensions().y)
        );
    }

}
