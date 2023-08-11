package doors.graphics.texture;

import doors.graphics.texture.channel.ScreenRenderTextureChannel;

public class ScreenRenderTexture extends FrameBufferTexture {

    public static ScreenRenderTexture SCREEN_RENDER_TEXTURE = new ScreenRenderTexture();

    public ScreenRenderTexture() {
        super(ScreenRenderTextureChannel.SCREEN_RENDER_TEXTURE_CHANNEL);
    }
    
}
