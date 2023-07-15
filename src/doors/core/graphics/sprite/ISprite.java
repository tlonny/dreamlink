package doors.core.graphics.sprite;

import doors.core.graphics.mesh.MeshBuffer;
import doors.utility.vector.Vector2in;

public interface ISprite {

    public Vector2in getDimensions();

    public void writeSprite(MeshBuffer meshBuffer, Vector2in position);
    
}
