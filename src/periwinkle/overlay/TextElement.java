package periwinkle.overlay;

import org.joml.Vector2i;
import org.joml.Vector3f;

import periwinkle.graphics.MeshBuffer;

public class TextElement {


    public String text;
    public Vector2i characterDimensions = new Vector2i(16, 32);
    public Vector2i position = new Vector2i();
    public Vector3f color = new Vector3f(1f, 1f, 1f);
    public boolean isUnderlined;

    private SpriteElement spriteBuffer = new SpriteElement();

    public TextElement(String text) {
        this.text = text;
    }

    public TextElement() {
        this(null);
    }

    public void batch(MeshBuffer meshBuffer) {
        spriteBuffer.dimensions.set(this.characterDimensions);
        spriteBuffer.color.set(this.color);
        spriteBuffer.position.set(this.position);

        for(var ix = 0; ix < this.text.length(); ix +=1 ) {
            var character = text.charAt(ix);
            var glyph = Glyph.GLYPH_CHARACTER_LOOKUP.get(character);
            spriteBuffer.texture = this.isUnderlined ? glyph.underlineTexture : glyph.texture;
            spriteBuffer.batch(meshBuffer);
            spriteBuffer.position.x += this.characterDimensions.x;
        }


    }

}
