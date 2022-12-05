package dreamlink.graphics.text;

import dreamlink.graphics.texture.sample.TextureSample;

public class CharacterTextureConfig {

    public final TextureSample normalTextureSample;
    public final TextureSample underlineTextureSample;

    public CharacterTextureConfig(
        TextureSample normalTextureSample,
        TextureSample underlineTextureSample
    ) {
        this.normalTextureSample = normalTextureSample;
        this.underlineTextureSample = underlineTextureSample;
    }
    
}
