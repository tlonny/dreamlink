package doors.ui.component;

import doors.graphics.font.Font;
import doors.graphics.font.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class TextComponent implements IComponent {

    public String text;
    public FontDecoration fontDecoration;
    public Vector3fl color;
    
    private Vector2in dimensions = new Vector2in();
    private Vector2in position = new Vector2in();

    public TextComponent(String text, FontDecoration fontDecoration, Vector3fl color) {
        this.text = text;
        this.fontDecoration = fontDecoration;
        this.color = new Vector3fl(color);
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void calculateDimensions() {
        this.dimensions.set(Font.CHARACTER_DIMENSIONS).mul(this.text.length(), 1);
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);
    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        Font.FONT.writeText(
            spriteBatch, 
            this.text, 
            this.position, 
            SpriteBatchHeight.UI_NORMAL,
            this.fontDecoration, 
            this.color
        );
    }
    
}
