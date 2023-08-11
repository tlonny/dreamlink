package doors.graphics.texture.sample;

import doors.graphics.texture.channel.MenuTextureChannel;
import doors.utility.vector.Vector2in;

public class MenuTextureSample extends TextureSample {

    private static Vector2in TEXTURE_SCALE = new Vector2in(8, 8);

    public static MenuTextureSample BACKGROUND = new MenuTextureSample(new Vector2in(0, 0).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample WINDOW_BACKGROUND = new MenuTextureSample(new Vector2in(2, 0).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample HIGHLIGHT = new MenuTextureSample(new Vector2in(1, 0).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample WHITE = new MenuTextureSample(new Vector2in(3, 0).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BLACK = new MenuTextureSample(new Vector2in(4, 0).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample ICON_FOLDER = new MenuTextureSample(new Vector2in(1, 10).mul(TEXTURE_SCALE), new Vector2in(Vector2in.TWO).mul(TEXTURE_SCALE));
    public static MenuTextureSample ICON_FILE = new MenuTextureSample(new Vector2in(1, 12).mul(TEXTURE_SCALE), new Vector2in(Vector2in.TWO).mul(TEXTURE_SCALE));
    public static MenuTextureSample ICON_RESTRICT = new MenuTextureSample(new Vector2in(1, 14).mul(TEXTURE_SCALE), new Vector2in(Vector2in.TWO).mul(TEXTURE_SCALE));
    public static MenuTextureSample ICON_PERMIT = new MenuTextureSample(new Vector2in(1, 16).mul(TEXTURE_SCALE), new Vector2in(Vector2in.TWO).mul(TEXTURE_SCALE));
    public static MenuTextureSample ICON_CABLE = new MenuTextureSample(new Vector2in(1, 18).mul(TEXTURE_SCALE), new Vector2in(Vector2in.TWO).mul(TEXTURE_SCALE));

    public static MenuTextureSample WINDOW_TOP_LEFT = new MenuTextureSample(new Vector2in(1, 6).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample WINDOW_LEFT = new MenuTextureSample(new Vector2in(1, 7).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample WINDOW_BOTTOM_LEFT = new MenuTextureSample(new Vector2in(1, 8).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample WINDOW_TOP = new MenuTextureSample(new Vector2in(2, 6).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample WINDOW_CENTER = new MenuTextureSample(new Vector2in(2, 7).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample WINDOW_BOTTOM = new MenuTextureSample(new Vector2in(2, 8).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample WINDOW_TOP_RIGHT = new MenuTextureSample(new Vector2in(3, 6).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample WINDOW_RIGHT = new MenuTextureSample(new Vector2in(3, 7).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample WINDOW_BOTTOM_RIGHT = new MenuTextureSample(new Vector2in(3, 8).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample BUTTON_TOP_LEFT = new MenuTextureSample(new Vector2in(6, 2).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_LEFT = new MenuTextureSample(new Vector2in(6, 3).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_BOTTOM_LEFT = new MenuTextureSample(new Vector2in(6, 4).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample BUTTON_TOP = new MenuTextureSample(new Vector2in(7, 2).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_CENTER = new MenuTextureSample(new Vector2in(7, 3).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_BOTTOM = new MenuTextureSample(new Vector2in(7, 4).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample BUTTON_TOP_RIGHT = new MenuTextureSample(new Vector2in(8, 2).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_RIGHT = new MenuTextureSample(new Vector2in(8, 3).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_BOTTOM_RIGHT = new MenuTextureSample(new Vector2in(8, 4).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample BUTTON_PRESSED_TOP_LEFT = new MenuTextureSample(new Vector2in(10, 2).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_PRESSED_LEFT = new MenuTextureSample(new Vector2in(10, 3).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_PRESSED_BOTTOM_LEFT = new MenuTextureSample(new Vector2in(10, 4).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample BUTTON_PRESSED_TOP = new MenuTextureSample(new Vector2in(11, 2).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_PRESSED_CENTER = new MenuTextureSample(new Vector2in(11, 3).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_PRESSED_BOTTOM = new MenuTextureSample(new Vector2in(11, 4).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample BUTTON_PRESSED_TOP_RIGHT = new MenuTextureSample(new Vector2in(12, 2).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_PRESSED_RIGHT = new MenuTextureSample(new Vector2in(12, 3).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_PRESSED_BOTTOM_RIGHT = new MenuTextureSample(new Vector2in(12, 4).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample BUTTON_DISABLED_TOP_LEFT = new MenuTextureSample(new Vector2in(14, 2).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_DISABLED_LEFT = new MenuTextureSample(new Vector2in(14, 3).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_DISABLED_BOTTOM_LEFT = new MenuTextureSample(new Vector2in(14, 4).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample BUTTON_DISABLED_TOP = new MenuTextureSample(new Vector2in(15, 2).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_DISABLED_CENTER = new MenuTextureSample(new Vector2in(15, 3).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_DISABLED_BOTTOM = new MenuTextureSample(new Vector2in(15, 4).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample BUTTON_DISABLED_TOP_RIGHT = new MenuTextureSample(new Vector2in(16, 2).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_DISABLED_RIGHT = new MenuTextureSample(new Vector2in(16, 3).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample BUTTON_DISABLED_BOTTOM_RIGHT = new MenuTextureSample(new Vector2in(16, 4).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample DIALOG_BLURRED_TOP_LEFT = new MenuTextureSample(new Vector2in(6, 6).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_BLURRED_LEFT = new MenuTextureSample(new Vector2in(6, 7).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_BLURRED_BOTTOM_LEFT = new MenuTextureSample(new Vector2in(6, 8).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample DIALOG_BLURRED_TOP = new MenuTextureSample(new Vector2in(7, 6).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_BLURRED_CENTER = new MenuTextureSample(new Vector2in(7, 7).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_BLURRED_BOTTOM = new MenuTextureSample(new Vector2in(7, 8).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample DIALOG_BLURRED_TOP_RIGHT = new MenuTextureSample(new Vector2in(8, 6).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_BLURRED_RIGHT = new MenuTextureSample(new Vector2in(8, 7).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_BLURRED_BOTTOM_RIGHT = new MenuTextureSample(new Vector2in(8, 8).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample DIALOG_FOCUSED_TOP_LEFT = new MenuTextureSample(new Vector2in(10, 6).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_FOCUSED_LEFT = new MenuTextureSample(new Vector2in(10, 7).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_FOCUSED_BOTTOM_LEFT = new MenuTextureSample(new Vector2in(10, 8).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample DIALOG_FOCUSED_TOP = new MenuTextureSample(new Vector2in(11, 6).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_FOCUSED_CENTER = new MenuTextureSample(new Vector2in(11, 7).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_FOCUSED_BOTTOM = new MenuTextureSample(new Vector2in(11, 8).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample DIALOG_FOCUSED_TOP_RIGHT = new MenuTextureSample(new Vector2in(12, 6).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_FOCUSED_RIGHT = new MenuTextureSample(new Vector2in(12, 7).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_FOCUSED_BOTTOM_RIGHT = new MenuTextureSample(new Vector2in(12, 8).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample DIALOG_DISABLED_TOP_LEFT = new MenuTextureSample(new Vector2in(14, 6).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_DISABLED_LEFT = new MenuTextureSample(new Vector2in(14, 7).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_DISABLED_BOTTOM_LEFT = new MenuTextureSample(new Vector2in(14, 8).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample DIALOG_DISABLED_TOP = new MenuTextureSample(new Vector2in(15, 6).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_DISABLED_CENTER = new MenuTextureSample(new Vector2in(15, 7).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_DISABLED_BOTTOM = new MenuTextureSample(new Vector2in(15, 8).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample DIALOG_DISABLED_TOP_RIGHT = new MenuTextureSample(new Vector2in(16, 6).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_DISABLED_RIGHT = new MenuTextureSample(new Vector2in(16, 7).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample DIALOG_DISABLED_BOTTOM_RIGHT = new MenuTextureSample(new Vector2in(16, 8).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample SEPERATOR_TOP_LEFT = new MenuTextureSample(new Vector2in(6, 10).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample SEPERATOR_LEFT = new MenuTextureSample(new Vector2in(6, 11).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample SEPERATOR_BOTTOM_LEFT = new MenuTextureSample(new Vector2in(6, 12).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample SEPERATOR_TOP = new MenuTextureSample(new Vector2in(7, 10).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample SEPERATOR_CENTER = new MenuTextureSample(new Vector2in(7, 11).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample SEPERATOR_BOTTOM = new MenuTextureSample(new Vector2in(7, 12).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample SEPERATOR_TOP_RIGHT = new MenuTextureSample(new Vector2in(8, 10).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample SEPERATOR_RIGHT = new MenuTextureSample(new Vector2in(8, 11).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static MenuTextureSample SEPERATOR_BOTTOM_RIGHT = new MenuTextureSample(new Vector2in(8, 12).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));

    public static MenuTextureSample CURSOR_POINTER = new MenuTextureSample(new Vector2in(1, 1).mul(TEXTURE_SCALE), new Vector2in(Vector2in.TWO).mul(TEXTURE_SCALE));
    public static MenuTextureSample CURSOR_ARROW = new MenuTextureSample(new Vector2in(1, 3).mul(TEXTURE_SCALE), new Vector2in(Vector2in.TWO).mul(TEXTURE_SCALE));
    public static MenuTextureSample CURSOR_GRAB = new MenuTextureSample(new Vector2in(3, 3).mul(TEXTURE_SCALE), new Vector2in(Vector2in.TWO).mul(TEXTURE_SCALE));
    public static MenuTextureSample CURSOR_FORBIDDEN = new MenuTextureSample(new Vector2in(3, 1).mul(TEXTURE_SCALE), new Vector2in(Vector2in.TWO).mul(TEXTURE_SCALE));

    public MenuTextureSample(Vector2in position, Vector2in dimensions) {
        super(MenuTextureChannel.MENU_TEXTURE_CHANNEL, position, dimensions);
    }
    
}
