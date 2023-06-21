package doors.overlay.ui;

import doors.graphics.texture.TextureSample;
import doors.graphics.texture.TextureSampler;
import doors.utility.geometry.Vector2in;

public class UITextureAtlas {

    private static Vector2in TEXTURE_SCALE = new Vector2in(8, 8);
    private static Vector2in TEXTURE_DIMENSIONS = new Vector2in(256, 256);
    private static TextureSampler SAMPLER = new TextureSampler(TEXTURE_DIMENSIONS, TEXTURE_SCALE);

    public static TextureSample BACKGROUND = SAMPLER.createTextureSample(new Vector2in(0, 0));

    public static TextureSample WINDOW_TOP_LEFT = SAMPLER.createTextureSample(new Vector2in(1, 6));
    public static TextureSample WINDOW_LEFT = SAMPLER.createTextureSample(new Vector2in(1, 7));
    public static TextureSample WINDOW_BOTTOM_LEFT = SAMPLER.createTextureSample(new Vector2in(1, 8));

    public static TextureSample WINDOW_TOP = SAMPLER.createTextureSample(new Vector2in(2, 6));
    public static TextureSample WINDOW_CENTER = SAMPLER.createTextureSample(new Vector2in(2, 7));
    public static TextureSample WINDOW_BOTTOM = SAMPLER.createTextureSample(new Vector2in(2, 8));

    public static TextureSample WINDOW_TOP_RIGHT = SAMPLER.createTextureSample(new Vector2in(3, 6));
    public static TextureSample WINDOW_RIGHT = SAMPLER.createTextureSample(new Vector2in(3, 7));
    public static TextureSample WINDOW_BOTTOM_RIGHT = SAMPLER.createTextureSample(new Vector2in(3, 8));

    public static TextureSample BUTTON_TOP_LEFT = SAMPLER.createTextureSample(new Vector2in(6, 2));
    public static TextureSample BUTTON_LEFT = SAMPLER.createTextureSample(new Vector2in(6, 3));
    public static TextureSample BUTTON_BOTTOM_LEFT = SAMPLER.createTextureSample(new Vector2in(6, 4));

    public static TextureSample BUTTON_TOP = SAMPLER.createTextureSample(new Vector2in(7, 2));
    public static TextureSample BUTTON_CENTER = SAMPLER.createTextureSample(new Vector2in(7, 3));
    public static TextureSample BUTTON_BOTTOM = SAMPLER.createTextureSample(new Vector2in(7, 4));

    public static TextureSample BUTTON_TOP_RIGHT = SAMPLER.createTextureSample(new Vector2in(8, 2));
    public static TextureSample BUTTON_RIGHT = SAMPLER.createTextureSample(new Vector2in(8, 3));
    public static TextureSample BUTTON_BOTTOM_RIGHT = SAMPLER.createTextureSample(new Vector2in(8, 4));

    public static TextureSample BUTTON_PRESSED_TOP_LEFT = SAMPLER.createTextureSample(new Vector2in(6, 6));
    public static TextureSample BUTTON_PRESSED_LEFT = SAMPLER.createTextureSample(new Vector2in(6, 7));
    public static TextureSample BUTTON_PRESSED_BOTTOM_LEFT = SAMPLER.createTextureSample(new Vector2in(6, 8));

    public static TextureSample BUTTON_PRESSED_TOP = SAMPLER.createTextureSample(new Vector2in(7, 6));
    public static TextureSample BUTTON_PRESSED_CENTER = SAMPLER.createTextureSample(new Vector2in(7, 7));
    public static TextureSample BUTTON_PRESSED_BOTTOM = SAMPLER.createTextureSample(new Vector2in(7, 8));

    public static TextureSample BUTTON_PRESSED_TOP_RIGHT = SAMPLER.createTextureSample(new Vector2in(8, 6));
    public static TextureSample BUTTON_PRESSED_RIGHT = SAMPLER.createTextureSample(new Vector2in(8, 7));
    public static TextureSample BUTTON_PRESSED_BOTTOM_RIGHT = SAMPLER.createTextureSample(new Vector2in(8, 8));

    public static TextureSample BORDER_TOP_LEFT = SAMPLER.createTextureSample(new Vector2in(10, 2));
    public static TextureSample BORDER_LEFT = SAMPLER.createTextureSample(new Vector2in(10, 3));
    public static TextureSample BORDER_BOTTOM_LEFT = SAMPLER.createTextureSample(new Vector2in(10, 4));

    public static TextureSample BORDER_TOP = SAMPLER.createTextureSample(new Vector2in(11, 2));
    public static TextureSample BORDER_CENTER = SAMPLER.createTextureSample(new Vector2in(11, 3));
    public static TextureSample BORDER_BOTTOM = SAMPLER.createTextureSample(new Vector2in(11, 4));

    public static TextureSample BORDER_TOP_RIGHT = SAMPLER.createTextureSample(new Vector2in(12, 2));
    public static TextureSample BORDER_RIGHT = SAMPLER.createTextureSample(new Vector2in(12, 3));
    public static TextureSample BORDER_BOTTOM_RIGHT = SAMPLER.createTextureSample(new Vector2in(12, 4));

    public static TextureSample DIALOG_TOP_LEFT = SAMPLER.createTextureSample(new Vector2in(10, 6));
    public static TextureSample DIALOG_LEFT = SAMPLER.createTextureSample(new Vector2in(10, 7));
    public static TextureSample DIALOG_BOTTOM_LEFT = SAMPLER.createTextureSample(new Vector2in(10, 8));

    public static TextureSample DIALOG_TOP = SAMPLER.createTextureSample(new Vector2in(11, 6));
    public static TextureSample DIALOG_CENTER = SAMPLER.createTextureSample(new Vector2in(11, 7));
    public static TextureSample DIALOG_BOTTOM = SAMPLER.createTextureSample(new Vector2in(11, 8));

    public static TextureSample DIALOG_TOP_RIGHT = SAMPLER.createTextureSample(new Vector2in(12, 6));
    public static TextureSample DIALOG_RIGHT = SAMPLER.createTextureSample(new Vector2in(12, 7));
    public static TextureSample DIALOG_BOTTOM_RIGHT = SAMPLER.createTextureSample(new Vector2in(12, 8));

    public static TextureSample CURSOR_ARROW = SAMPLER.createTextureSample(new Vector2in(1, 3), new Vector2in(2, 2));
    public static TextureSample CURSOR_POINTER = SAMPLER.createTextureSample(new Vector2in(1, 1), new Vector2in(2, 2));

    public static TextureSample ICON_EXPLORE = SAMPLER.createTextureSample(new Vector2in(29, 2), new Vector2in(2, 2));
    public static TextureSample ICON_EDITOR = SAMPLER.createTextureSample(new Vector2in(29, 5), new Vector2in(2, 2));
    public static TextureSample ICON_HELP = SAMPLER.createTextureSample(new Vector2in(29, 8), new Vector2in(2, 2));
    public static TextureSample ICON_SETTINGS = SAMPLER.createTextureSample(new Vector2in(29, 11), new Vector2in(2, 2));
    public static TextureSample ICON_QUIT = SAMPLER.createTextureSample(new Vector2in(29, 14), new Vector2in(2, 2));
    public static TextureSample ICON_LOGO = SAMPLER.createTextureSample(new Vector2in(29, 17), new Vector2in(2, 2));
}
