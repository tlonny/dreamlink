package doors.ui;

import doors.core.ui.IUIElement;
import doors.core.ui.PaddingElement;
import doors.core.ui.WrapperElement;

public class BorderElement extends WrapperElement {

    private static int BORDER_PADDING = 2;

    private PaddingElement paddingChild;
    
    public boolean borderTop;
    public boolean borderBottom;
    public boolean borderLeft;
    public boolean borderRight;

    public BorderElement(IUIElement child) {
        this(child, true, true, true, true);
    }

    public BorderElement(IUIElement child, boolean topBorder, boolean bottomBorder, boolean leftBorder, boolean rightBorder) {
        this.child = this.paddingChild = new PaddingElement(child, BORDER_PADDING);

        this.borderTop = topBorder;
        this.borderBottom = bottomBorder;
        this.borderLeft = leftBorder;
        this.borderRight = rightBorder;
    }

    public void setChild(IUIElement child) {
        this.paddingChild.child = child;
    }

    @Override
    public void writeElement() {
        BoxDesign.BORDER.writeBox(
            this.getPosition(), 
            this.getDimensions(), 
            this.borderTop, 
            this.borderBottom, 
            this.borderLeft, 
            this.borderRight
        );
        super.writeElement();
    }


}
