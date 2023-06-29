package doors.overlay.ui.element;

import doors.utility.geometry.Vector2in;

public class WrapperElement implements IUIElement {

    /* N.B.
     * Please don't try and get every element sub-classing from this
     * Lets only limit ourselves to using a WrapperElement for subclasses
     * that need *at most* to override the writeElement() method
     *
     * If we're starting to do overrides for setDimensions/setPositions,
     * we should probably just implement the interface cleanly from scratch.
     * Always prefer verbosity over terseness!
     */

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
    public void setDimensions() {
        this.child.setDimensions();
    }

    @Override
    public void setPosition(Vector2in origin) {
        this.child.setPosition(origin);
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
