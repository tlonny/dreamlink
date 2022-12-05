package dreamlink.graphics.texture.sample;


import org.joml.Vector2i;

import dreamlink.graphics.texture.TextureUnit;
import dreamlink.utility.maths.Vector2iMaths;
import dreamlink.window.Window;

public class PortalTextureSample extends TextureSample {

    public static final PortalTextureSample instance = new PortalTextureSample();

    public PortalTextureSample() {
        super(
            TextureUnit.portal,
            Window.instance.getResolution(new Vector2i()),
            Vector2iMaths.zero,
            Window.instance.getResolution(new Vector2i())
        );
    }
    
}
