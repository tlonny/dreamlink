package doors.graphics.texture.channel;

public class ScreenRenderTextureChannel extends AbstractTextureChannel {

    public static ScreenRenderTextureChannel SCREEN_RENDER_TEXTURE_CHANNEL = new ScreenRenderTextureChannel();

    @Override
    public int getTextureUnitID() {
        return 6;
    }
    
}
