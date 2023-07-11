package doors.entity;

import doors.Doors;
import doors.core.config.Config;
import doors.core.graphics.texture.TextureSample;
import doors.core.utility.vector.Vector2in;
import doors.core.utility.vector.Vector3fl;

public class Billboard {

    public static Billboard CURRENT_BILLBOARD = new Billboard(Doors.RENDER_TARGET_CURRENT.screenSample);

    private TextureSample textureSample;

    public Billboard(TextureSample textureSample) {
        this.textureSample = textureSample;
    }

    public void update() {
        Doors.UI_QUAD_BATCH.writeQuad(
            this.textureSample,
            Vector2in.ZERO,
            Config.CONFIG.getResolution(),
            Vector3fl.WHITE
        );
    }
}
