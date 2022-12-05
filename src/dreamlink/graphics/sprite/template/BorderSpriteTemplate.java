package dreamlink.graphics.sprite.template;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.graphics.texture.sample.TextureSample;
import dreamlink.utility.maths.Vector4fMaths;

public class BorderSpriteTemplate implements ISpriteTemplate {

    public static final BorderSpriteTemplate button = new BorderSpriteTemplate(
        MenuTextureSample.buttonTop,
        MenuTextureSample.buttonBottom,
        MenuTextureSample.buttonLeft,
        MenuTextureSample.buttonRight,
        MenuTextureSample.buttonCenter,
        MenuTextureSample.buttonTopLeft,
        MenuTextureSample.buttonTopRight,
        MenuTextureSample.buttonBottomLeft,
        MenuTextureSample.buttonBottomRight
    );

    public static final BorderSpriteTemplate buttonPressed = new BorderSpriteTemplate(
        MenuTextureSample.buttonPressedTop,
        MenuTextureSample.buttonPressedBottom,
        MenuTextureSample.buttonPressedLeft,
        MenuTextureSample.buttonPressedRight,
        MenuTextureSample.buttonPressedCenter,
        MenuTextureSample.buttonPressedTopLeft,
        MenuTextureSample.buttonPressedTopRight,
        MenuTextureSample.buttonPressedBottomLeft,
        MenuTextureSample.buttonPressedBottomRight
    );

    public static final BorderSpriteTemplate buttonDisabled = new BorderSpriteTemplate(
        MenuTextureSample.buttonDisabledTop,
        MenuTextureSample.buttonDisabledBottom,
        MenuTextureSample.buttonDisabledLeft,
        MenuTextureSample.buttonDisabledRight,
        MenuTextureSample.buttonDisabledCenter,
        MenuTextureSample.buttonDisabledTopLeft,
        MenuTextureSample.buttonDisabledTopRight,
        MenuTextureSample.buttonDisabledBottomLeft,
        MenuTextureSample.buttonDisabledBottomRight
    );

    public static final BorderSpriteTemplate dialog = new BorderSpriteTemplate(
        MenuTextureSample.dialogTop,
        MenuTextureSample.dialogBottom,
        MenuTextureSample.dialogLeft,
        MenuTextureSample.dialogRight,
        MenuTextureSample.dialogCenter,
        MenuTextureSample.dialogTopLeft,
        MenuTextureSample.dialogTopRight,
        MenuTextureSample.dialogBottomLeft,
        MenuTextureSample.dialogBottomRight
    );

    public static final BorderSpriteTemplate dialogDisabled = new BorderSpriteTemplate(
        MenuTextureSample.dialogDisabledTop,
        MenuTextureSample.dialogDisabledBottom,
        MenuTextureSample.dialogDisabledLeft,
        MenuTextureSample.dialogDisabledRight,
        MenuTextureSample.dialogDisabledCenter,
        MenuTextureSample.dialogDisabledTopLeft,
        MenuTextureSample.dialogDisabledTopRight,
        MenuTextureSample.dialogDisabledBottomLeft,
        MenuTextureSample.dialogDisabledBottomRight
    );

    public static final BorderSpriteTemplate window = new BorderSpriteTemplate(
        MenuTextureSample.windowTop,
        MenuTextureSample.windowBottom,
        MenuTextureSample.windowLeft,
        MenuTextureSample.windowRight,
        MenuTextureSample.windowCenter,
        MenuTextureSample.windowTopLeft,
        MenuTextureSample.windowTopRight,
        MenuTextureSample.windowBottomLeft,
        MenuTextureSample.windowBottomRight
    );

    public static final BorderSpriteTemplate dialogBlurred = new BorderSpriteTemplate(
        MenuTextureSample.dialogBlurredTop,
        MenuTextureSample.dialogBlurredBottom,
        MenuTextureSample.dialogBlurredLeft,
        MenuTextureSample.dialogBlurredRight,
        MenuTextureSample.dialogBlurredCenter,
        MenuTextureSample.dialogBlurredTopLeft,
        MenuTextureSample.dialogBlurredTopRight,
        MenuTextureSample.dialogBlurredBottomLeft,
        MenuTextureSample.dialogBlurredBottomRight
    );

    public static final BorderSpriteTemplate tab = new BorderSpriteTemplate(
        MenuTextureSample.tabTop,
        MenuTextureSample.tabBottom,
        MenuTextureSample.tabLeft,
        MenuTextureSample.tabRight,
        MenuTextureSample.tabCenter,
        MenuTextureSample.tabTopLeft,
        MenuTextureSample.tabTopRight,
        MenuTextureSample.tabBottomLeft,
        MenuTextureSample.tabBottomRight
    );

    public static final BorderSpriteTemplate tabBody = new BorderSpriteTemplate(
        MenuTextureSample.buttonCenter,
        MenuTextureSample.buttonBottom,
        MenuTextureSample.buttonLeft,
        MenuTextureSample.buttonRight,
        MenuTextureSample.buttonCenter,
        MenuTextureSample.buttonLeft,
        MenuTextureSample.buttonRight,
        MenuTextureSample.buttonBottomLeft,
        MenuTextureSample.buttonBottomRight
    );

    public static final BorderSpriteTemplate tabSelected = new BorderSpriteTemplate(
        MenuTextureSample.tabTop,
        MenuTextureSample.tabCenter,
        MenuTextureSample.tabLeft,
        MenuTextureSample.tabRight,
        MenuTextureSample.tabCenter,
        MenuTextureSample.tabTopLeft,
        MenuTextureSample.tabTopRight,
        MenuTextureSample.tabLeft,
        MenuTextureSample.tabRight
    );

    public static final BorderSpriteTemplate separator = new BorderSpriteTemplate(
        MenuTextureSample.separatorTop,
        MenuTextureSample.separatorBottom,
        MenuTextureSample.separatorLeft,
        MenuTextureSample.separatorRight,
        MenuTextureSample.separatorCenter,
        MenuTextureSample.separatorTopLeft,
        MenuTextureSample.separatorTopRight,
        MenuTextureSample.separatorBottomLeft,
        MenuTextureSample.separatorBottomRight
    );

    private final TextureSample topTextureSample;
    private final TextureSample bottomTextureSample;
    private final TextureSample leftTextureSample;
    private final TextureSample rightTextureSample;
    private final TextureSample centerTextureSample;
    private final TextureSample topLeftTextureSample;
    private final TextureSample topRightTextureSample;
    private final TextureSample bottomLeftTextureSample;
    private final TextureSample bottomRightTextureSample;

    public BorderSpriteTemplate(
        TextureSample topTextureSample,
        TextureSample bottomTextureSample,
        TextureSample leftTextureSample,
        TextureSample rightTextureSample,
        TextureSample centerTextureSample,
        TextureSample topLeftTextureSample,
        TextureSample topRightTextureSample,
        TextureSample bottomLeftTextureSample,
        TextureSample bottomRightTextureSample
    ) {
        this.topTextureSample = topTextureSample;
        this.bottomTextureSample = bottomTextureSample;
        this.leftTextureSample = leftTextureSample;
        this.rightTextureSample = rightTextureSample;
        this.centerTextureSample = centerTextureSample;
        this.topLeftTextureSample = topLeftTextureSample;
        this.topRightTextureSample = topRightTextureSample;
        this.bottomLeftTextureSample = bottomLeftTextureSample;
        this.bottomRightTextureSample = bottomRightTextureSample;
    }

    private final Vector2i writeToSpriteBatchPosition = new Vector2i();
    private final Vector2i writeToSpriteBatchDimensions = new Vector2i();

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
            this.centerTextureSample, 
            Vector4fMaths.white
        );


        var topDimensions = this.topTextureSample.dimensions;
        spriteBatch.writeTextureSample(
            position,
            this.writeToSpriteBatchDimensions.set(dimensions.x(), topDimensions.y()),
            height,
            this.topTextureSample, 
            Vector4fMaths.white
        );

        var bottomDimensions = this.bottomTextureSample.dimensions;
        spriteBatch.writeTextureSample(
            this.writeToSpriteBatchPosition.set(position.x(), position.y() + dimensions.y() - bottomDimensions.y()),
            this.writeToSpriteBatchDimensions.set(dimensions.x(), bottomDimensions.y()),
            height,
            this.bottomTextureSample, 
            Vector4fMaths.white
        );

        var leftDimensions = this.leftTextureSample.dimensions;
        spriteBatch.writeTextureSample(
            position,
            this.writeToSpriteBatchDimensions.set(leftDimensions.x(), dimensions.y()),
            height,
            this.leftTextureSample, 
            Vector4fMaths.white
        );

        var rightDimensions = this.rightTextureSample.dimensions;
        spriteBatch.writeTextureSample(
            this.writeToSpriteBatchPosition.set(position.x() + dimensions.x() - rightDimensions.x(), position.y()),
            this.writeToSpriteBatchDimensions.set(rightDimensions.x(), dimensions.y()),
            height,
            this.rightTextureSample, 
            Vector4fMaths.white
        );
        
        var topLeftDimensions = this.topLeftTextureSample.dimensions;
        spriteBatch.writeTextureSample(
            position,
            topLeftDimensions,
            height,
            this.topLeftTextureSample, 
            Vector4fMaths.white
        );

        var topRightDimensions = this.topRightTextureSample.dimensions;
        spriteBatch.writeTextureSample(
            this.writeToSpriteBatchPosition.set(position.x() + dimensions.x() - topRightDimensions.x(), position.y()),
            topRightDimensions,
            height,
            this.topRightTextureSample, 
            Vector4fMaths.white
        );

        var bottomLeftDimensions = this.bottomLeftTextureSample.dimensions;
        spriteBatch.writeTextureSample(
            this.writeToSpriteBatchPosition.set(position.x(), position.y() + dimensions.y() - bottomLeftDimensions.y()),
            bottomLeftDimensions,
            height,
            this.bottomLeftTextureSample, 
            Vector4fMaths.white
        );

        var bottomRightDimensions = this.bottomRightTextureSample.dimensions;
        spriteBatch.writeTextureSample(
            this.writeToSpriteBatchPosition.set(position.x() + dimensions.x() - bottomRightDimensions.x(), position.y() + dimensions.y() - bottomRightDimensions.y()),
            bottomRightDimensions,
            height,
            this.bottomRightTextureSample, 
            Vector4fMaths.white
        );
    }
}
