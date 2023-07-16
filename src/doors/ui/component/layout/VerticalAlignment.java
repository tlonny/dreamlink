package doors.ui.component.layout;

public enum VerticalAlignment {
    TOP,
    CENTER,
    BOTTOM;

    public int getVerticalOffset(int parentHeight, int childHeight) {
        if(this == TOP) {
            return 0;
        }

        if(this == CENTER) {
            return (parentHeight - childHeight) / 2;
        }

        return parentHeight - childHeight;
    }

}
