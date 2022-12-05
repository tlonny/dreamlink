package dreamlink.overlay.eventspan;

import org.joml.Vector2i;

import dreamlink.graphics.sprite.SpriteHeight;

public interface IEventSpan {

    public Vector2i getDimensions(Vector2i target);

    public Vector2i getPosition(Vector2i target);

    public SpriteHeight getSpriteHeight();

    public int getDragDistanceThreshold();

    public void onEvent(IEvent event);
    
}
