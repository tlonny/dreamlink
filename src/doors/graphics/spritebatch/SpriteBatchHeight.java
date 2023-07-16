package doors.graphics.spritebatch;

public class SpriteBatchHeight {

    public static SpriteBatchHeight SCREEN = new SpriteBatchHeight(0x000);
    public static SpriteBatchHeight HUD = new SpriteBatchHeight(0x001);

    public static SpriteBatchHeight UI_NORMAL = new SpriteBatchHeight(0x100);
    public static SpriteBatchHeight UI_FLOATING = new SpriteBatchHeight(0x101);
    public static SpriteBatchHeight UI_CURSOR_PAYLOAD = new SpriteBatchHeight(0x102);
    public static SpriteBatchHeight UI_CURSOR = new SpriteBatchHeight(0x103);

    public int height;

    public SpriteBatchHeight(int height) {
        this.height = height;
    }
}
