package doors.overlay;

import doors.Config;
import doors.graphics.RenderTargetTexture;
import doors.graphics.TextureChannel;
import doors.graphics.TextureSample;
import doors.graphics.TextureSampler;
import doors.utility.Color;
import doors.utility.Maths;

public class WorldScreen {

    private static TextureSample TEXTURE_SAMPLE = 
        new TextureSampler(TextureChannel.WORLD_TEXTURE_CHANNEL, RenderTargetTexture.CURRENT_WORLD_RENDER_TARGET_TEXTURE.dimensions).createTextureSample();

    public static WorldScreen WORLD_SCREEN = new WorldScreen();

    public void render() {
        Overlay.OVERLAY.pushSprite(Maths.VEC2I_ZERO, Config.RESOLUTION, TEXTURE_SAMPLE, Color.WHITE);
    }


}
