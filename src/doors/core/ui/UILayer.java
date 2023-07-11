package doors.core.ui;

public class UILayer {

    public static UILayer NORMAL = new UILayer(0, false);
    public static UILayer INTERACTIVE = new UILayer(1, true);

    public int height;
    public boolean isInteractive;

    public UILayer(int height, boolean isInteractive) {
        this.height = height;
        this.isInteractive = isInteractive;
    }

} 
