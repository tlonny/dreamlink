package dreamlink.graphics.texture.sample;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.graphics.texture.TextureUnit;
import dreamlink.utility.maths.Vector2fMaths;
import dreamlink.utility.maths.Vector2iMaths;
import dreamlink.utility.maths.Vector4fMaths;

public class TextureSample implements ISpriteTemplate {

    private static Vector2ic[] windingMasks = new Vector2ic[] {
        new Vector2i(0, 0),
        new Vector2i(0, 1),
        new Vector2i(1, 1),
        new Vector2i(1, 0)
    };

    public final Vector2ic position;
    public final Vector2ic dimensions;

    public final TextureUnit textureUnit;
    public final int animationTotalFrames;
    public final int animationRowFrames;
    public final int animationStartFrame;
    public final int animationSpeed;

    public final Vector2ic animationStride;
    public final Vector2ic atlasDimensions;

    public TextureSample(
        TextureUnit textureUnit,
        Vector2ic atlasDimensions,
        Vector2ic position, 
        Vector2ic dimensions
    ) {
        this(
            textureUnit,
            atlasDimensions,
            position,
            dimensions,
            Vector2iMaths.zero,
            1,
            1,
            0,
            0
        );
    }

    public TextureSample(
        TextureUnit textureUnit,
        Vector2ic atlasDimensions,
        Vector2ic position, 
        Vector2ic dimensions,
        Vector2ic animationStride,
        int animationTotalFrames,
        int animationRowFrames,
        int animationStartFrame,
        int animationSpeed
    ) {
        this.textureUnit = textureUnit;
        this.atlasDimensions = new Vector2i(atlasDimensions);
        this.position = new Vector2i(position);
        this.dimensions = new Vector2i(dimensions);
        this.animationStride = new Vector2i(animationStride);
        
        this.animationTotalFrames = animationTotalFrames;
        this.animationStartFrame = animationStartFrame;
        this.animationRowFrames = animationRowFrames;
        this.animationSpeed = animationSpeed;
    }

    public Vector2f getAnimationStrideOffset(Vector2f target) {
        target.set(this.animationStride);
        return Vector2fMaths.div(target, this.atlasDimensions);
    }

    public Vector2f getTextureOffset(int windingIndex, Vector2f textureOffset) {
        var windingMask = TextureSample.windingMasks[windingIndex];
        textureOffset.set(this.dimensions);
        Vector2fMaths.mul(textureOffset, windingMask);
        Vector2fMaths.add(textureOffset, this.position);
        return Vector2fMaths.div(textureOffset, this.atlasDimensions);
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
            this,
            Vector4fMaths.white
        );
    }

}

