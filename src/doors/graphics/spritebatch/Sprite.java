package doors.graphics.spritebatch;

import doors.graphics.texture.sample.TextureSample;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class Sprite {
    public Vector2in position = new Vector2in();
    public Vector2in dimensions = new Vector2in();
    public Vector3fl color = new Vector3fl();
    public SpriteBatchHeight height = null;
    public TextureSample textureSample = null;
    
}
