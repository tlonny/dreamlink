package dreamlink.graphics.sprite.template;

import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;

public interface ISpriteTemplate {

    public void writeToSpriteBatch(
        SpriteBatch spriteBatch,
        Vector2ic position,
        Vector2ic dimensions,
        SpriteHeight height
    );

}
