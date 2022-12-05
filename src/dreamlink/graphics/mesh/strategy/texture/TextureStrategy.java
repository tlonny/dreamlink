package dreamlink.graphics.mesh.strategy.texture;

import org.joml.Vector2f;

public class TextureStrategy {

    public static final int animationFramePeriod = 20;

    private static final int textureChannelIDOffset = 0;
    private static final int animationTotalFramesOffset = 4;
    private static final int animationRowFramesOffset = 12;
    private static final int animationStartFrameOffset = 20;
    private static final int animationSpeedOffset = 28;
    
    private static final int textureUnitIDMask = 0xF;
    private static final int animationFrameMask = 0xFF;
    private static final int animationSpeedMask = 0xF;

    private ITextureStategyProvider provider;

    public TextureStrategy(ITextureStategyProvider provider) {
        this.provider = provider;
    }

    public void add(ITextureQuad quad) {
        var textureDataBuffer = new Vector2f();
        var textureSample = quad.getTextureSample();
        var windingOffset = quad.getWindingOffset();

        var packedData = 0;
        packedData |= (textureSample.textureUnit.getIndex() & TextureStrategy.textureUnitIDMask) << TextureStrategy.textureChannelIDOffset;
        packedData |= (textureSample.animationTotalFrames & TextureStrategy.animationFrameMask) << TextureStrategy.animationTotalFramesOffset;
        packedData |= (textureSample.animationRowFrames & TextureStrategy.animationFrameMask) << TextureStrategy.animationRowFramesOffset;
        packedData |= (textureSample.animationStartFrame & TextureStrategy.animationFrameMask) << TextureStrategy.animationStartFrameOffset;
        packedData |= (textureSample.animationSpeed & TextureStrategy.animationSpeedMask) << TextureStrategy.animationSpeedOffset;

        textureSample.getAnimationStrideOffset(textureDataBuffer);
        var animStrideX = textureDataBuffer.x;
        var animStrideY = textureDataBuffer.y;

        for(var ix = 0; ix < 4; ix += 1) {
            var offsetIndex = (ix + 4 - windingOffset) % 4;
            textureSample.getTextureOffset(offsetIndex, textureDataBuffer);
            this.provider.addTextureOffsets(
                textureDataBuffer.x,
                textureDataBuffer.y,
                animStrideX,
                animStrideY
            );
            this.provider.addMetadata(packedData);
        }
    }
    
}
