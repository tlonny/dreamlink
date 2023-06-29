package doors.overlay.ui.element;

import doors.overlay.ui.BoxDesign;

public class WindowElement extends WrapperElement {

    private static int WINDOW_PADDING = 2;

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
