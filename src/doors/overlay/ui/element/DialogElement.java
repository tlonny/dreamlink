package doors.overlay.ui.element;

import doors.overlay.ui.BoxDesign;
import doors.utility.geometry.Vector2in;

public class DialogElement implements IUIElement {

    private static int DIALOG_PADDING = 4;

    private PaddingElement paddingChild;

    private Vector2in position;
    private Vector2in dimensions;

    public DialogElement(IUIElement child) {
        this.paddingChild = new PaddingElement(child, DIALOG_PADDING);

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
        BoxDesign.DIALOG.writeBox(this.position, this.dimensions);
        this.paddingChild.writeElement();
    }


}
