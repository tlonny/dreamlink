package doors.ui.element;

import doors.ui.BoxDesign;

public class DialogElement extends WrapperElement {

    private static int DIALOG_PADDING = 4;

    private PaddingElement paddingChild;

    public DialogElement(IUIElement child) {
        this.child = this.paddingChild = new PaddingElement(child, DIALOG_PADDING);
    }

    public void setChild(IUIElement child) {
        this.paddingChild.child = child;
    }

    @Override
    public void writeElement() {
        BoxDesign.DIALOG.writeBox(this.getPosition(), this.getDimensions());
        super.writeElement();
    }


}
