package doors.graphics.texture;

import doors.graphics.texture.channel.FontTextureChannel;

public class FontTexture extends ImageTexture {


    public static FontTexture FONT_TEXTURE = new FontTexture();

    public FontTexture() {
        super(
            FontTextureChannel.FONT_TEXTURE_CHANNEL,
            "data/texture/font.png"
        );
    }

}
