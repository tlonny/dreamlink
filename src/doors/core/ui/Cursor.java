package doors.core.ui;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.sprite.ISprite;
import doors.core.io.Mouse;
import doors.utility.vector.Vector2in;

public class Cursor {

    public static Cursor USED_CURSOR = null;

    private ISprite sprite;
    private Vector2in offset;
    private Vector2in position = new Vector2in();

    public Cursor(ISprite sprite, Vector2in offset) {
        this.sprite = sprite;
        this.offset = new Vector2in(offset);
    }

    public void use() {
        USED_CURSOR = this;
    }

    public void writeCursor(MeshBuffer meshBuffer) {
        this.position.set(Mouse.MOUSE.position).sub(this.offset);
        this.sprite.writeSprite(meshBuffer, this.position);
    }

}
