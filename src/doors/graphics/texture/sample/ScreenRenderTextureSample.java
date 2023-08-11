package doors.graphics.texture.sample;

import doors.Config;
import doors.graphics.texture.channel.ScreenRenderTextureChannel;
import doors.utility.vector.Vector2in;

public class ScreenRenderTextureSample extends TextureSample {

    public static ScreenRenderTextureSample SCREEN_RENDER = new ScreenRenderTextureSample();

    public ScreenRenderTextureSample() {
        super(ScreenRenderTextureChannel.SCREEN_RENDER_TEXTURE_CHANNEL, Vector2in.ZERO, Config.CONFIG.getResolution());
    }
    
}
