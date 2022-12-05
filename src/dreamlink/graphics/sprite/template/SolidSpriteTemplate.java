package dreamlink.graphics.sprite.template;

import org.joml.Vector2ic;
import org.joml.Vector4f;
import org.joml.Vector4fc;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.utility.maths.Vector4fMaths;

public class SolidSpriteTemplate implements ISpriteTemplate {

    public static final SolidSpriteTemplate white = new SolidSpriteTemplate(Vector4fMaths.white);
    public static final SolidSpriteTemplate magenta = new SolidSpriteTemplate(Vector4fMaths.magenta);
    public static final SolidSpriteTemplate black = new SolidSpriteTemplate(Vector4fMaths.black);
    public static final SolidSpriteTemplate overlayHighlight = new SolidSpriteTemplate(Vector4fMaths.overlayHighlight);
    public static final SolidSpriteTemplate overlayBackground = new SolidSpriteTemplate(Vector4fMaths.overlayBackground);
    public static final SolidSpriteTemplate overlayWindow = new SolidSpriteTemplate(Vector4fMaths.overlayWindow);

    private final Vector4fc color;

    public SolidSpriteTemplate(Vector4fc color) {
        this.color = new Vector4f(color);
    }

    @Override
    public void writeToSpriteBatch(
        SpriteBatch spriteBatch,
        Vector2ic position, 
        Vector2ic dimensions,
        SpriteHeight height
    ) {
        spriteBatch.writeTextureSample(
            position,
            dimensions,
            height,
            MenuTextureSample.white,
            this.color
        );
    }
    
}
