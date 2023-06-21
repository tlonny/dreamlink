package doors.overlay.ui.element;

import doors.utility.geometry.Vector2in;

public class WrapperElement implements IUIElement {

    protected IUIElement child;

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
