package doors.overlay;

import org.joml.Vector2i;
import doors.Config;
import doors.utility.Maths;

public class Reticule {

    private static Vector2i RETICULE_SIZE = new Vector2i(8, 8);

    public static Reticule RETICULE = new Reticule();

    public void render() {
        var reticulePosition = new Vector2i();

        reticulePosition.set(
            Config.RESOLUTION.x / 2 - RETICULE_SIZE.x / 2,
            Config.RESOLUTION.y / 2 - RETICULE_SIZE.y / 2
        );
        Overlay.OVERLAY.pushSprite(reticulePosition, RETICULE_SIZE, SystemTextureAtlas.RETICULE, Maths.VEC3F_ONE);
    }


}
