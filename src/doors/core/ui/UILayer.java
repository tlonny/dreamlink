package doors.core.ui;

public class UILayer {

    public static UILayer BACKGROUND = new UILayer(0);
    public static UILayer NORMAL = new UILayer(1);
    public static UILayer FLOATING = new UILayer(2);

    public int height;

    public UILayer(int height) {
        this.height = height;
    }
    
}
