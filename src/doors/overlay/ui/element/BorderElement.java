package doors.overlay.ui.element;

import doors.overlay.ui.BoxDesign;
import doors.utility.geometry.Vector2in;

public class BorderElement implements IUIElement {

    private static int BORDER_PADDING = 2;

    private PaddingElement paddingChild;

    private Vector2in position;
    private Vector2in dimensions;
    
    public boolean topBorder;
    public boolean bottomBorder;
    public boolean leftBorder;
    public boolean rightBorder;

    public BorderElement(IUIElement child) {
        this(child, true, true, true, true);
    }

    public BorderElement(IUIElement child, boolean topBorder, boolean bottomBorder, boolean leftBorder, boolean rightBorder) {
        this.paddingChild = new PaddingElement(child, BORDER_PADDING);

        this.position = new Vector2in();
        this.dimensions = new Vector2in();

        this.topBorder = topBorder;
        this.bottomBorder = bottomBorder;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
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
        BoxDesign.BORDER.writeBox(this.position, this.dimensions, this.topBorder, this.bottomBorder, this.leftBorder, this.rightBorder);
        this.paddingChild.writeElement();
    }


}
