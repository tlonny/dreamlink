package doors.ui.component.layout.alignment;

public enum HorizontalAlignment {
    LEFT,
    CENTER,
    RIGHT;

    public int getHorizontalOffset(int parentWidth, int childWidth) {
        if(this == LEFT) {
            return 0;
        }

        if(this == CENTER) {
            return (parentWidth - childWidth) / 2;
        }

        return parentWidth - childWidth;
    }

}
