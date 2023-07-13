package doors.menu;

public class ComponentLayer {

    public static ComponentLayer NORMAL = new ComponentLayer(0);
    public static ComponentLayer INTERACTIVE = new ComponentLayer(1);

    public int height;

    public ComponentLayer(int height) {
        this.height = height;
    }

} 
