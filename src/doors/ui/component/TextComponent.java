package doors.ui.component;

import doors.graphics.text.FontDecoration;
import doors.graphics.text.Glyph;
import doors.graphics.text.TextFragment;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class TextComponent implements IComponent {

    public FontDecoration fontDecoration;
    public Vector3fl color = new Vector3fl();
    
    private Vector2in dimensions = new Vector2in();
    private Vector2in position = new Vector2in();
    private TextFragment textFragment = new TextFragment();
    private String text;

    public TextComponent(String text, FontDecoration fontDecoration, Vector3fl color) {
        this.text = text;
        this.textFragment.pushText(text, fontDecoration, color);
        this.fontDecoration = fontDecoration;
        this.color.set(color);
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.textFragment.clear();
        this.textFragment.pushText(text, this.fontDecoration, this.color);
        this.text = text;
    }

    @Override
    public void calculateDimensions() {
        this.dimensions.set(Glyph.GLYPH_DIMENSIONS).mul(this.text.length(), 1);
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.textFragment.writeTextFragmentToSpriteBatch(
            spriteBatch,
            this.position,
            SpriteBatchHeight.UI_NORMAL
        );
    }
    
}
