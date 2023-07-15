package doors.core.graphics.sprite;

import doors.core.graphics.font.AbstractFont;
import doors.core.graphics.font.FontDecoration;
import doors.core.graphics.mesh.MeshBuffer;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public abstract class AbstractTextSprite implements ISprite  {

    private Vector2in dimensions;
    private String text;
    private FontDecoration fontDecoration;
    private Vector3fl color;

    public AbstractTextSprite(String text, FontDecoration fontDecoration, Vector3fl color) {
        this.fontDecoration = fontDecoration;
        this.color = new Vector3fl(color);
        this.text = text;
        var font = this.getFont();
        this.dimensions = new Vector2in(font.characterDimensions)
            .mul(text.length(), 1);
    }

    protected abstract AbstractFont getFont();

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void writeSprite(MeshBuffer meshBuffer, Vector2in position) {
        var font = this.getFont();
        font.writeText(meshBuffer, text, position, this.fontDecoration, this.color);
    }

    
}
