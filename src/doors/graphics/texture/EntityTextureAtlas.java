package doors.graphics.texture;

import doors.utility.vector.Vector2in;

public class EntityTextureAtlas extends ImageTexture {

    private static Vector2in TEXTURE_DIMENSIONS = new Vector2in(512, 512);
    private static String TEXTURE_PATH = "data/texture/entity.png";
    private static Vector2in TEXTURE_SCALE = new Vector2in(8, 8);

    public static EntityTextureAtlas ENTITY_TEXTURE_ATLAS = new EntityTextureAtlas();

    public TextureSample reticule = this.createTextureSample(new Vector2in(0, 1), Vector2in.ONE, TEXTURE_SCALE);
    public TextureSample doorLeftFace = this.createTextureSample(new Vector2in(1, 0), new Vector2in(2, 4), TEXTURE_SCALE);
    public TextureSample doorRightFace = this.createTextureSample(new Vector2in(3, 0), new Vector2in(2, 4), TEXTURE_SCALE);
    public TextureSample doorLockedDoubleFace = this.createTextureSample(new Vector2in(1, 4), new Vector2in(4, 4), TEXTURE_SCALE);
    public TextureSample doorSide = this.createTextureSample(new Vector2in(5, 0), new Vector2in(1, 4), TEXTURE_SCALE);
    public TextureSample doorEnd = this.createTextureSample(new Vector2in(5, 4), new Vector2in(1, 2), TEXTURE_SCALE);

    public EntityTextureAtlas() {
        super(
            TextureChannel.TEXTURE_CHANNEL_ENTITY,
            TEXTURE_DIMENSIONS,
            TEXTURE_PATH
        );
    }

}
