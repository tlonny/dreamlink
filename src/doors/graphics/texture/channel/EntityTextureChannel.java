package doors.graphics.texture.channel;

public class EntityTextureChannel extends AbstractTextureChannel {

    public static EntityTextureChannel ENTITY_TEXTURE_CHANNEL = new EntityTextureChannel();

    @Override
    public int getTextureUnitID() {
        return 3;
    }
    
}
