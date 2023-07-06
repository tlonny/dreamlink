package doors.core.ui.alignment;

public enum HorizontalAlignment { 

    LEFT, 
    CENTER, 
    RIGHT;

    public int getOffset(int parentWidth, int width) {
        if(this == HorizontalAlignment.LEFT) {
            return 0;
        } 

        if(this == HorizontalAlignment.CENTER) {
            return (parentWidth - width) / 2;
        } 

        return parentWidth - width;
    }

};
