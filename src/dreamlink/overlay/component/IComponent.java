package dreamlink.overlay.component;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.overlay.eventspan.IEventSpanRegistry;

public interface IComponent {

    public Vector2i getInitialDimensions(Vector2i target);

    public Vector2i getDimensions(Vector2i target);

    public Vector2i getPosition(Vector2i target);

    public SpriteHeight getSpriteHeight();

    public void computeInitialDimensions();

    public void computeDimensions(Vector2ic availableSpace);

    public void computePosition(Vector2ic position, SpriteHeight spriteHeight);

    public void registerEventSpans(IEventSpanRegistry registry);

    public void writeToSpriteBatch(SpriteBatch spriteBatch);

}
