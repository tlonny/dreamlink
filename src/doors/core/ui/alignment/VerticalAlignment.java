package doors.core.ui.alignment;

public enum VerticalAlignment { 

    TOP, 
    CENTER, 
    BOTTOM;
   
    public int getOffset(int parentHeight, int height) {
        if(this == VerticalAlignment.TOP) {
            return 0;
        } 

        if(this == VerticalAlignment.CENTER) {
            return (parentHeight - height) / 2;
        } 

        return parentHeight - height;
    }

};
