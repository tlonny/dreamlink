package doors.core.graphics.sprite;

import doors.core.graphics.texture.TextureSample;

public class FontCharacter {

    private TextureSample normalTextureSample;
    private TextureSample boldTextureSample;
    private TextureSample italicTextureSample;
    private TextureSample underlineTextureSample;

    public FontCharacter(
        TextureSample normalTextureSample,
        TextureSample boldTextureSample,
        TextureSample italicTextureSample,
        TextureSample underlineTextureSample
    ) {
        this.normalTextureSample = normalTextureSample;
        this.boldTextureSample = boldTextureSample;
        this.italicTextureSample = italicTextureSample;
        this.underlineTextureSample = underlineTextureSample;
    }

    public TextureSample getTextureSample(FontDecoration decoration) {
        if(decoration == FontDecoration.NORMAL) {
            return this.normalTextureSample;
        }

        if(decoration == FontDecoration.BOLD) {
            return this.boldTextureSample;
        }

        if(decoration == FontDecoration.ITALIC) {
            return this.italicTextureSample;
        }

        if(decoration == FontDecoration.UNDERLINE) {
            return this.underlineTextureSample;
        }

        throw new RuntimeException("Unknown font decoration: " + decoration);
    }


}
