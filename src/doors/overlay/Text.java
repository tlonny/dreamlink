package doors.overlay;

import org.joml.Vector2i;

import doors.graphics.MeshBuffer;

public class Text {

    public String text;
    public Vector2i characterDimensions = new Vector2i(16, 32);
    public Vector2i position = new Vector2i();
    public boolean isUnderlined;

    private Sprite sprite = new Sprite();

    public void write(MeshBuffer meshBuffer) {
        this.sprite.dimensions.set(this.characterDimensions);
        this.sprite.position.set(this.position);

        for(var ix = 0; ix < this.text.length(); ix +=1 ) {
            var character = text.charAt(ix);
            var glyph = Glyph.GLYPH_CHARACTER_LOOKUP.get(character);
            this.sprite.textureSample = this.isUnderlined ? glyph.underlineTextureSample : glyph.textureSample;
            this.sprite.write(meshBuffer);
            this.sprite.position.x += this.characterDimensions.x;
        }


    }

}
