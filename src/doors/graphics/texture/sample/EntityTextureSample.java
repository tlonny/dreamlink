package doors.graphics.texture.sample;

import doors.graphics.texture.channel.EntityTextureChannel;
import doors.utility.vector.Vector2in;

public class EntityTextureSample extends TextureSample {

    private static Vector2in TEXTURE_SCALE = new Vector2in(8, 8);

    public static EntityTextureSample DEBUG = new EntityTextureSample(new Vector2in(0, 0).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static EntityTextureSample RETICULE = new EntityTextureSample(new Vector2in(0, 1).mul(TEXTURE_SCALE), new Vector2in(Vector2in.ONE).mul(TEXTURE_SCALE));
    public static EntityTextureSample DOOR_LEFT_FACE = new EntityTextureSample(new Vector2in(1, 0).mul(TEXTURE_SCALE), new Vector2in(2, 4).mul(TEXTURE_SCALE));
    public static EntityTextureSample DOOR_RIGHT_FACE = new EntityTextureSample(new Vector2in(3, 0).mul(TEXTURE_SCALE), new Vector2in(2, 4).mul(TEXTURE_SCALE));
    public static EntityTextureSample DOOR_LOCKED_DOUBLE_FACE = new EntityTextureSample(new Vector2in(1, 4).mul(TEXTURE_SCALE), new Vector2in(4, 4).mul(TEXTURE_SCALE));
    public static EntityTextureSample DOOR_WALL = new EntityTextureSample(new Vector2in(5, 0).mul(TEXTURE_SCALE), new Vector2in(2, 4).mul(TEXTURE_SCALE));
    public static EntityTextureSample DOOR_CEILING = new EntityTextureSample(new Vector2in(5, 2).mul(TEXTURE_SCALE), new Vector2in(4, 2).mul(TEXTURE_SCALE));
    public static EntityTextureSample DOOR_BACK = new EntityTextureSample(new Vector2in(5, 0).mul(TEXTURE_SCALE), new Vector2in(4, 4).mul(TEXTURE_SCALE));

    public EntityTextureSample(Vector2in position, Vector2in dimensions) {
        super(EntityTextureChannel.ENTITY_TEXTURE_CHANNEL, position, dimensions);
    }
    
}
