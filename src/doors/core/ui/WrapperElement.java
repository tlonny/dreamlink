package doors.core.ui;

import doors.core.utility.vector.Vector2in;

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

    public WrapperElement() {
        this.child = null;
    }

    public WrapperElement(IUIElement child) {
        this.child = child;
    }

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
    public void determineDimensions() {
        this.child.determineDimensions();
    }

    @Override
    public void determinePosition(Vector2in origin) {
        this.child.determinePosition(origin);
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
