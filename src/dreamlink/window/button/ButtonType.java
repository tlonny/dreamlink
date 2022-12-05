package dreamlink.window.button;

public class ButtonType {

    public static final ButtonType mouse = new ButtonType(0);
    public static final ButtonType keyboard = new ButtonType(64);

    public final int offset;

    public ButtonType(int offset) {
        this.offset = offset;
    }

}
