package doors.graphics.texture;

import doors.utility.vector.Vector2in;

public class MenuTextureAtlas extends ImageTexture {

    private static Vector2in TEXTURE_DIMENSIONS = new Vector2in(256, 256);
    private static String TEXTURE_PATH = "data/texture/ui.png";
    private static Vector2in TEXTURE_SCALE = new Vector2in(8, 8);

    public static MenuTextureAtlas MENU_TEXTURE_ATLAS = new MenuTextureAtlas();

    public TextureSample background = this.createTextureSample(new Vector2in(0, 1), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample titleBar = this.createTextureSample(new Vector2in(1, 0), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample highLight = this.createTextureSample(new Vector2in(2, 0), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample windowTopLeft = this.createTextureSample(new Vector2in(1, 6), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample windowLeft = this.createTextureSample(new Vector2in(1, 7), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample windowBottomLeft = this.createTextureSample(new Vector2in(1, 8), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample windowTop = this.createTextureSample(new Vector2in(2, 6), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample windowCenter = this.createTextureSample(new Vector2in(2, 7), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample windowBottom = this.createTextureSample(new Vector2in(2, 8), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample windowTopRight = this.createTextureSample(new Vector2in(3, 6), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample windowRight = this.createTextureSample(new Vector2in(3, 7), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample windowBottomRight = this.createTextureSample(new Vector2in(3, 8), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample buttonTopLeft = this.createTextureSample(new Vector2in(6, 2), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonLeft = this.createTextureSample(new Vector2in(6, 3), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonBottomLeft = this.createTextureSample(new Vector2in(6, 4), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample buttonTop = this.createTextureSample(new Vector2in(7, 2), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonCenter = this.createTextureSample(new Vector2in(7, 3), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonBottom = this.createTextureSample(new Vector2in(7, 4), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample buttonTopRight = this.createTextureSample(new Vector2in(8, 2), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonRight = this.createTextureSample(new Vector2in(8, 3), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonBottomRight = this.createTextureSample(new Vector2in(8, 4), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample buttonPressedTopLeft = this.createTextureSample(new Vector2in(10, 2), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonPressedLeft = this.createTextureSample(new Vector2in(10, 3), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonPressedBottomLeft = this.createTextureSample(new Vector2in(10, 4), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample buttonPressedTop = this.createTextureSample(new Vector2in(11, 2), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonPressedCenter = this.createTextureSample(new Vector2in(11, 3), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonPressedBottom = this.createTextureSample(new Vector2in(11, 4), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample buttonPressedTopRight = this.createTextureSample(new Vector2in(12, 2), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonPressedRight = this.createTextureSample(new Vector2in(12, 3), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonPressedBottomRight = this.createTextureSample(new Vector2in(12, 4), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample buttonDisabledTopLeft = this.createTextureSample(new Vector2in(14, 2), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonDisabledLeft = this.createTextureSample(new Vector2in(14, 3), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonDisabledBottomLeft = this.createTextureSample(new Vector2in(14, 4), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample buttonDisabledTop = this.createTextureSample(new Vector2in(15, 2), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonDisabledCenter = this.createTextureSample(new Vector2in(15, 3), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonDisabledBottom = this.createTextureSample(new Vector2in(15, 4), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample buttonDisabledTopRight = this.createTextureSample(new Vector2in(16, 2), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonDisabledRight = this.createTextureSample(new Vector2in(16, 3), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample buttonDisabledBottomRight = this.createTextureSample(new Vector2in(16, 4), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample dialogBlurTopLeft = this.createTextureSample(new Vector2in(6, 6), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogBlurLeft = this.createTextureSample(new Vector2in(6, 7), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogBlurBottomLeft = this.createTextureSample(new Vector2in(6, 8), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample dialogBlurTop = this.createTextureSample(new Vector2in(7, 6), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogBlurCenter = this.createTextureSample(new Vector2in(7, 7), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogBlurBottom = this.createTextureSample(new Vector2in(7, 8), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample dialogBlurTopRight = this.createTextureSample(new Vector2in(8, 6), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogBlurRight = this.createTextureSample(new Vector2in(8, 7), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogBlurBottomRight = this.createTextureSample(new Vector2in(8, 8), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample dialogFocusTopLeft = this.createTextureSample(new Vector2in(10, 6), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogFocusLeft = this.createTextureSample(new Vector2in(10, 7), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogFocusBottomLeft = this.createTextureSample(new Vector2in(10, 8), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample dialogFocusTop = this.createTextureSample(new Vector2in(11, 6), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogFocusCenter = this.createTextureSample(new Vector2in(11, 7), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogFocusBottom = this.createTextureSample(new Vector2in(11, 8), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample dialogFocusTopRight = this.createTextureSample(new Vector2in(12, 6), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogFocusRight = this.createTextureSample(new Vector2in(12, 7), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogFocusBottomRight = this.createTextureSample(new Vector2in(12, 8), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample dialogDisabledTopLeft = this.createTextureSample(new Vector2in(14, 6), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogDisabledLeft = this.createTextureSample(new Vector2in(14, 7), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogDisabledBottomLeft = this.createTextureSample(new Vector2in(14, 8), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample dialogDisabledTop = this.createTextureSample(new Vector2in(15, 6), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogDisabledCenter = this.createTextureSample(new Vector2in(15, 7), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogDisabledBottom = this.createTextureSample(new Vector2in(15, 8), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample dialogDisabledTopRight = this.createTextureSample(new Vector2in(16, 6), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogDisabledRight = this.createTextureSample(new Vector2in(16, 7), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample dialogDisabledBottomRight = this.createTextureSample(new Vector2in(16, 8), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample borderTopLeft = this.createTextureSample(new Vector2in(6, 10), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample borderLeft = this.createTextureSample(new Vector2in(6, 11), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample borderBottomLeft = this.createTextureSample(new Vector2in(6, 12), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample borderTop = this.createTextureSample(new Vector2in(7, 10), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample borderCenter = this.createTextureSample(new Vector2in(7, 11), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample borderBottom = this.createTextureSample(new Vector2in(7, 12), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample borderTopRight = this.createTextureSample(new Vector2in(8, 10), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample borderRight = this.createTextureSample(new Vector2in(8, 11), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample borderBottomRight = this.createTextureSample(new Vector2in(8, 12), Vector2in.ONE, TEXTURE_SCALE);

    public TextureSample cursorPointer = this.createTextureSample(new Vector2in(1, 1), new Vector2in(2, 2), TEXTURE_SCALE);
    public TextureSample cursorArrow = this.createTextureSample(new Vector2in(1, 3), new Vector2in(2, 2), TEXTURE_SCALE);
    public TextureSample cursorForbidden = this.createTextureSample(new Vector2in(3, 1), new Vector2in(2, 2), TEXTURE_SCALE);

    public MenuTextureAtlas() {
        super(
            TextureChannel.TEXTURE_CHANNEL_MENU, 
            TEXTURE_DIMENSIONS, 
            TEXTURE_PATH
        );
    }


}
