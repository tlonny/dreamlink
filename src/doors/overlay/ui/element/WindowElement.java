package doors.overlay.ui.element;

import doors.overlay.ui.BoxDesign;
import doors.utility.geometry.Vector2in;

public class WindowElement implements IUIElement {

    private static int WINDOW_PADDING = 2;

    private PaddingElement paddingChild;

    private Vector2in position;
    private Vector2in dimensions;

    public WindowElement(IUIElement child) {
        this.paddingChild = new PaddingElement(child, WINDOW_PADDING);

        this.position = new Vector2in();
        this.dimensions = new Vector2in();
    }

    public void setChild(IUIElement child) {
        this.paddingChild.child = child;
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public Vector2in getPosition() {
        return this.position;
    }

    @Override
    public void rebuild() {
        this.paddingChild.rebuild();
        this.dimensions.set(this.paddingChild.getDimensions());
    }

    @Override
    public void orient(Vector2in origin) {
        this.position.set(origin);
        this.paddingChild.orient(origin);
    }

    @Override
    public void update() {
        this.paddingChild.update();
    }

    @Override
    public void writeElement() {
        BoxDesign.WINDOW.writeBox(this.position, this.dimensions);
        this.paddingChild.writeElement();
    }


}
