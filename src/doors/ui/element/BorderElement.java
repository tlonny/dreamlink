package doors.ui.element;

import doors.ui.BoxDesign;

public class BorderElement extends WrapperElement {

    private static int BORDER_PADDING = 2;

    private PaddingElement paddingChild;
    
    public boolean topBorder;
    public boolean bottomBorder;
    public boolean leftBorder;
    public boolean rightBorder;

    public BorderElement(IUIElement child) {
        this(child, true, true, true, true);
    }

    public BorderElement(IUIElement child, boolean topBorder, boolean bottomBorder, boolean leftBorder, boolean rightBorder) {
        this.child = this.paddingChild = new PaddingElement(child, BORDER_PADDING);

        this.topBorder = topBorder;
        this.bottomBorder = bottomBorder;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    public void setChild(IUIElement child) {
        this.paddingChild.child = child;
    }

    @Override
    public void writeElement() {
        BoxDesign.BORDER.writeBox(
            this.getPosition(), 
            this.getDimensions(), 
            this.topBorder, 
            this.bottomBorder, 
            this.leftBorder, 
            this.rightBorder
        );
        super.writeElement();
    }


}
