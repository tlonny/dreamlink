package doors.entity;

import doors.Doors;
import doors.core.graphics.texture.TextureSample;
import doors.core.utility.vector.Vector2in;

public class EntityTextureAtlas {

    private static Vector2in TEXTURE_SCALE = new Vector2in(8, 8);

    public static TextureSample DEBUG_RED = Doors.TEXTURE_ENTITY.createTextureSample(new Vector2in(0, 0), Vector2in.ONE, TEXTURE_SCALE);
    public static TextureSample DOOR_HALF_FACE_LEFT = Doors.TEXTURE_ENTITY.createTextureSample(new Vector2in(1, 0), new Vector2in(2, 4), TEXTURE_SCALE);
    public static TextureSample DOOR_HALF_FACE_RIGHT = Doors.TEXTURE_ENTITY.createTextureSample(new Vector2in(3, 0), new Vector2in(2, 4), TEXTURE_SCALE);
    public static TextureSample DOOR_LOCKED_HALF_FACE_LEFT = Doors.TEXTURE_ENTITY.createTextureSample(new Vector2in(1, 4), new Vector2in(2, 4), TEXTURE_SCALE);
    public static TextureSample DOOR_LOCKED_HALF_FACE_RIGHT = Doors.TEXTURE_ENTITY.createTextureSample(new Vector2in(3, 4), new Vector2in(2, 4), TEXTURE_SCALE);
    public static TextureSample DOOR_SIDE = Doors.TEXTURE_ENTITY.createTextureSample(new Vector2in(5, 0), new Vector2in(1, 4), TEXTURE_SCALE);
    public static TextureSample DOOR_END = Doors.TEXTURE_ENTITY.createTextureSample(new Vector2in(5, 4), new Vector2in(1, 2), TEXTURE_SCALE);

}
