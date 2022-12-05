package dreamlink.overlay.eventspan;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.window.Window;

public class Cursor {

    public static final Cursor arrowCursor = new Cursor(
        MenuTextureSample.cursorArrow, 
        MenuTextureSample.cursorArrow.dimensions, 
        new Vector2i(3, 0)
    );

    public static final Cursor pointerCursor = new Cursor(
        MenuTextureSample.cursorPointer, 
        MenuTextureSample.cursorPointer.dimensions, 
        new Vector2i(7, 0)
    );

    public static final Cursor dragCursor = new Cursor(
        MenuTextureSample.cursorGrab, 
        MenuTextureSample.cursorGrab.dimensions,
        new Vector2i(8, 3)
    );

    private final Vector2ic dimensions;
    private final Vector2ic offset;
    private final ISpriteTemplate sprite;

    public Cursor(ISpriteTemplate sprite, Vector2ic dimensions, Vector2ic offset) {
        this.sprite = sprite;
        this.dimensions = new Vector2i(dimensions);
        this.offset = new Vector2i(offset);
    }

    private final Vector2i writeToSpriteBatchMousePosition = new Vector2i();

    public void writeToSpriteBatch(SpriteBatch spriteBatch) {
        var mousePosition = Window.instance.getMousePosition(this.writeToSpriteBatchMousePosition);
        var offsetMousePosition = new Vector2i(mousePosition).sub(this.offset);
        this.sprite.writeToSpriteBatch(
            spriteBatch,
            offsetMousePosition,
            this.dimensions, 
            SpriteHeight.cursor
        );
    }

}
