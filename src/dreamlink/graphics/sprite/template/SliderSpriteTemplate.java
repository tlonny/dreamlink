package dreamlink.graphics.sprite.template;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.utility.maths.Vector4fMaths;

public class SliderSpriteTemplate implements ISpriteTemplate {

    public static final SliderSpriteTemplate instance = new SliderSpriteTemplate();

    private final Vector2i writeToSpriteBatchPosition = new Vector2i();
    private final Vector2i writeToSpriteBatchDimensions = new Vector2i();

    @Override
    public void writeToSpriteBatch(
        SpriteBatch spriteBatch,
        Vector2ic position, 
        Vector2ic dimensions,
        SpriteHeight height
    ) {
        var middleDimensions = this.writeToSpriteBatchDimensions.set(dimensions);
        middleDimensions.y = MenuTextureSample.sliderMiddle.dimensions.y();
        var middlePosition = this.writeToSpriteBatchPosition.set(position);
        middlePosition.y += (dimensions.y() - middleDimensions.y) / 2;

        spriteBatch.writeTextureSample(
            middlePosition,
            middleDimensions,
            height,
            MenuTextureSample.sliderMiddle,
            Vector4fMaths.white
        );

        var leftDimensions = this.writeToSpriteBatchDimensions.set(MenuTextureSample.sliderLeft.dimensions);
        leftDimensions.y = MenuTextureSample.sliderLeft.dimensions.y();
        var leftPosition = this.writeToSpriteBatchPosition.set(position);
        leftPosition.y += (dimensions.y() - leftDimensions.y) / 2;

        spriteBatch.writeTextureSample(
            leftPosition,
            leftDimensions,
            height,
            MenuTextureSample.sliderLeft,
            Vector4fMaths.white
        );

        var rightDimensions = this.writeToSpriteBatchDimensions.set(MenuTextureSample.sliderRight.dimensions);
        rightDimensions.y = MenuTextureSample.sliderRight.dimensions.y();
        var rightPosition = this.writeToSpriteBatchPosition.set(position);
        rightPosition.x += dimensions.x() - rightDimensions.x();
        rightPosition.y += (dimensions.y() - rightDimensions.y) / 2;

        spriteBatch.writeTextureSample(
            rightPosition,
            rightDimensions,
            height,
            MenuTextureSample.sliderRight,
            Vector4fMaths.white
        );
    }
    
}
