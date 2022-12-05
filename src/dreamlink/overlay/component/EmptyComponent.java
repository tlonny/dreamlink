package dreamlink.overlay.component;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.overlay.eventspan.IEventSpanRegistry;
import dreamlink.utility.maths.Vector2iMaths;

public class EmptyComponent implements IComponent {

    private final Vector2i position = new Vector2i();
    private SpriteHeight spriteHeight;

    @Override
    public Vector2i getInitialDimensions(Vector2i target) {
        return target.set(Vector2iMaths.zero);
    }

    @Override
    public Vector2i getDimensions(Vector2i target) {
        return target.set(Vector2iMaths.zero);
    }

    @Override
    public void computeInitialDimensions() {
    
    }

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {

    }

    @Override
    public void writeToSpriteBatch(SpriteBatch spriteBatch) {

    }

    @Override
    public void computeDimensions(Vector2ic availableSpace) {

    }

    @Override
    public void computePosition(Vector2ic position, SpriteHeight spriteHeight) {
        this.position.set(position);
        this.spriteHeight = spriteHeight;
    }

    @Override
    public Vector2i getPosition(Vector2i target) {
        return target.set(this.position);
    }

    @Override
    public SpriteHeight getSpriteHeight() {
        return this.spriteHeight;
    }

}
