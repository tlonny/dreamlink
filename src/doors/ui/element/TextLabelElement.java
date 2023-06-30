package doors.ui.element;

import doors.graphics.sprite.SpriteBatch;
import doors.graphics.sprite.font.FontTextureAtlas;
import doors.utility.geometry.Vector2in;
import doors.utility.geometry.Vector3fl;

public class TextLabelElement implements IUIElement {

    private String[] linesOfText;
    public boolean isUnderlined;
    public Vector3fl color;

    private Vector2in position;
    private Vector2in dimensions;

    public TextLabelElement(String text, boolean isUnderlined, Vector3fl color) {
        this.setText(text);
        this.isUnderlined = isUnderlined;
        this.color = color;
        this.position = new Vector2in();
        this.dimensions = new Vector2in();
    }

    public void setText(String text) {
        this.linesOfText = text.split("\n");
    }

    @Override
    public Vector2in getPosition() {
        return this.position;
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void calculateDimensions() {
        this.dimensions.set(0);
        for(var line : this.linesOfText) {
            this.dimensions.x = Math.max(this.dimensions.x, FontTextureAtlas.CHARACTER_DIMENSIONS.x * line.length());
            this.dimensions.y += FontTextureAtlas.CHARACTER_DIMENSIONS.y;
        }
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.position.set(origin);
    }

    @Override
    public void update() {
    }


    @Override
    public void writeElement() {
        var originCursor = new Vector2in(this.position);
        for(var line : this.linesOfText) {
            SpriteBatch.SPRITE_BATCH.writeText(line, originCursor, this.isUnderlined, this.color);
            originCursor.y += FontTextureAtlas.CHARACTER_DIMENSIONS.y;
        }
    }

}
