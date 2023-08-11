package doors.graphics.texture;

import doors.graphics.texture.channel.EntityTextureChannel;

public class EntityTexture extends ImageTexture {

    private static String TEXTURE_PATH = "data/texture/entity.png";

    public static EntityTexture ENTITY_TEXTURE = new EntityTexture();

    public EntityTexture() {
        super(
            EntityTextureChannel.ENTITY_TEXTURE_CHANNEL,
            TEXTURE_PATH
        );
    }

}
