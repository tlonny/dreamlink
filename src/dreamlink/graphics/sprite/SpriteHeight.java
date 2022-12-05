package dreamlink.graphics.sprite;

public class SpriteHeight {

    public static SpriteHeight background = new SpriteHeight(0);
    public static SpriteHeight menu = new SpriteHeight(1);
    public static SpriteHeight menuFloating = new SpriteHeight(2);
    public static SpriteHeight cursorPayload = new SpriteHeight(3);
    public static SpriteHeight cursor = new SpriteHeight(4);
    public static SpriteHeight hud = new SpriteHeight(5);

    private static final SpriteHeight[] heights = new SpriteHeight[] {
        SpriteHeight.background,
        SpriteHeight.menu,
        SpriteHeight.cursorPayload,
        SpriteHeight.cursor,
        SpriteHeight.hud
    };

    public static void setup() {
        for(var ix = 0; ix < heights.length; ix += 1) {
            heights[ix].height = ix;
        }
    }

    private int height;

    public SpriteHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return this.height;
    }
    
}
