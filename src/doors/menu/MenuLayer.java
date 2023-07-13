package doors.menu;

public class MenuLayer {

    public static MenuLayer NORMAL = new MenuLayer(0);
    public static MenuLayer INTERACTIVE = new MenuLayer(1);

    public int height;

    public MenuLayer(int height) {
        this.height = height;
    }

} 
