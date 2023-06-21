package doors.overlay.ui.element;

import doors.utility.geometry.Vector2in;

public class AlignmentElement implements IUIElement {

    public enum HorizontalAlignment { LEFT, CENTER, RIGHT };
    public enum VerticalAlignment { TOP, CENTER, BOTTOM };

    public IUIElement child;
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

    @Override
    public Vector2in getDimensions() {
        return this.child.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.child.getPosition();
    }

    @Override
    public void rebuild() {
        this.child.rebuild();
    }

    @Override
    public void orient(Vector2in origin) {
        this.child.orient(origin);
    }

    @Override
    public void update() {
        this.child.update();
    }

    @Override
    public void writeElement() {
        this.child.writeElement();
    }
}

