package dreamlink.overlay.component.text;

import org.joml.Vector4f;
import org.joml.Vector4fc;

import dreamlink.graphics.text.CharacterTextureSampleLookup;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.sample.TextureSample;

public class CharacterData {

    public final Vector4f color = new Vector4f();
    public final Vector4f highlightColor = new Vector4f();
    public boolean isHighlighted;
    public char character;
    public FontDecoration fontDecoration;
    public boolean isUsed;

    public CharacterData set(
        char character, 
        FontDecoration fontDecoration, 
        Vector4fc color
    ) {
        this.character = character;
        this.fontDecoration = fontDecoration;
        this.color.set(color);
        this.isHighlighted = false;
        this.isUsed = true;
        return this;
    }

    public CharacterData set(
        char character, 
        FontDecoration fontDecoration, 
        Vector4fc color,
        Vector4fc highlightColor
    ) {
        this.character = character;
        this.fontDecoration = fontDecoration;
        this.color.set(color);
        this.highlightColor.set(highlightColor);
        this.isHighlighted = true;
        this.isUsed = true;
        return this;
    }

    public TextureSample getGlyph() {
        if(!this.isUsed) {
            return null;
        }

        return CharacterTextureSampleLookup.instance.getTextureSample(
            this.character, 
            this.fontDecoration
        );
    }

    public CharacterData clear() {
        this.isUsed = false;
        return this;
    }
    
}
