package doors.graphics.texture;

import doors.core.graphics.texture.ImageTexture;
import doors.graphics.texture.channel.FontTextureChannel;
import doors.utility.vector.Vector2in;

public class FontTexture extends ImageTexture {

    private static Vector2in TEXTURE_DIMENSIONS = new Vector2in(256, 96);
    public static Vector2in CHARACTER_DIMENSIONS = new Vector2in(8, 16);

    public static FontTexture FONT_TEXTURE = new FontTexture();

    public FontTexture() {
        super(
            FontTextureChannel.FONT_TEXTURE_CHANNEL,
            TEXTURE_DIMENSIONS,
            "data/texture/font.png"
        );
    }

}
