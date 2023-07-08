package doors.ui;

import doors.Screen;
import doors.core.graphics.sprite.FontDecoration;
import doors.core.graphics.sprite.FontMeshBufferWriter;
import doors.core.ui.IUIElement;
import doors.core.utility.vector.Vector3fl;
import doors.graphics.ui.StandardFont;
import doors.core.utility.vector.Vector2in;

public class TextLabelElement implements IUIElement {

    private String[] linesOfText;
    public FontDecoration fontDecoration;
    public Vector3fl color;

    private Vector2in position;
    private Vector2in dimensions;
    private FontMeshBufferWriter fontWriter;

    public TextLabelElement(String text, FontDecoration fontDecoration, Vector3fl color) {
        this.setText(text);
        this.fontDecoration = fontDecoration;
        this.color = color;
        this.position = new Vector2in();
        this.dimensions = new Vector2in();
        this.fontWriter = new FontMeshBufferWriter(Screen.SCREEN.meshBuffer);
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
    public void determineDimensions() {
        this.dimensions.set(0);
        for(var line : this.linesOfText) {
            this.dimensions.x = Math.max(this.dimensions.x, StandardFont.CHARACTER_DIMENSIONS.x * line.length());
            this.dimensions.y += StandardFont.CHARACTER_DIMENSIONS.y;
        }
    }

    @Override
    public void determinePosition(Vector2in origin) {
        this.position.set(origin);
    }

    @Override
    public void update() {
    }


    @Override
    public void writeElement() {
        var originCursor = new Vector2in(this.position);
        for(var line : this.linesOfText) {
            this.fontWriter.writeText(
                StandardFont.STANDARD_FONT,
                line, 
                originCursor, 
                this.fontDecoration, this.color
            );
            originCursor.y += StandardFont.CHARACTER_DIMENSIONS.y;
        }
    }

}
