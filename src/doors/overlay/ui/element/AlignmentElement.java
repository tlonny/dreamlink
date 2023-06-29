package doors.overlay.ui.element;

public class AlignmentElement extends WrapperElement {

    public enum HorizontalAlignment { LEFT, CENTER, RIGHT };
    public enum VerticalAlignment { TOP, CENTER, BOTTOM };

    public HorizontalAlignment horizontalAlignment;
    public VerticalAlignment verticalAlignment;

    public AlignmentElement(IUIElement child, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        this.child = child;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
    }

    public AlignmentElement(IUIElement child, HorizontalAlignment horizontalAlignment) {
        this(child, horizontalAlignment, VerticalAlignment.CENTER);
    }

    public AlignmentElement(IUIElement child, VerticalAlignment verticalAlignment) {
        this(child, HorizontalAlignment.CENTER, verticalAlignment);
    }

    public AlignmentElement(IUIElement child) {
        this(child, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
    }
}

