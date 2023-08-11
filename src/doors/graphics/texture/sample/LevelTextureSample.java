package doors.graphics.texture.sample;

import org.json.JSONArray;

import doors.graphics.texture.channel.EntityTextureChannel;
import doors.utility.vector.Vector2in;

public class LevelTextureSample extends TextureSample {

    public LevelTextureSample(Vector2in position, Vector2in dimensions) {
        super(EntityTextureChannel.ENTITY_TEXTURE_CHANNEL, position, dimensions);
    }

    public LevelTextureSample(JSONArray jsonArray) {
        super(
            EntityTextureChannel.ENTITY_TEXTURE_CHANNEL, 
            new Vector2in(jsonArray.getInt(0), jsonArray.getInt(1)),
            new Vector2in(jsonArray.getInt(2), jsonArray.getInt(3))
        );
    }
    
}
