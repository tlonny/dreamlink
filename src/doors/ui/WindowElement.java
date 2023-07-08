package doors.ui;

import doors.core.ui.IUIElement;
import doors.core.ui.PaddingElement;
import doors.core.ui.WrapperElement;
import doors.ui.BoxDesign;

public class WindowElement extends WrapperElement {

    private static int WINDOW_PADDING = 3;

    private PaddingElement paddingChild;

    public WindowElement(IUIElement child) {
        this.child = this.paddingChild = new PaddingElement(child, WINDOW_PADDING);
    }

    public void setChild(IUIElement child) {
        this.paddingChild.child = child;
    }

    @Override
    public void writeElement() {
        BoxDesign.WINDOW.writeBox(this.getPosition(), this.getDimensions());
        super.writeElement();
    }


}
