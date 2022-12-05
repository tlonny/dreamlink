package dreamlink.overlay.component;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.overlay.eventspan.IEventSpanRegistry;

public abstract class WrapperComponent implements IComponent {

    public abstract IComponent getComponent();

    private final Vector2i position = new Vector2i();
    private SpriteHeight spriteHeight;

    @Override
    public Vector2i getInitialDimensions(Vector2i target) {
        var component = this.getComponent();
        return component.getInitialDimensions(target);
    }

    @Override
    public Vector2i getDimensions(Vector2i target) {
        var component = this.getComponent();
        return component.getDimensions(target);
    }

    @Override
    public Vector2i getPosition(Vector2i target) {
        return target.set(this.position);
    }

    @Override
    public SpriteHeight getSpriteHeight() {
        return this.spriteHeight;
    }

    @Override
    public void computeInitialDimensions() {
        var component = this.getComponent();
        component.computeInitialDimensions();
    }

    @Override
    public void computeDimensions(Vector2ic availableSpace) {
        var component = this.getComponent();
        component.computeDimensions(availableSpace);
    }

    @Override
    public void computePosition(Vector2ic position, SpriteHeight spriteHeight) {
        this.position.set(position);
        this.spriteHeight = spriteHeight;

        var component = this.getComponent();
        component.computePosition(position, spriteHeight);
    }

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {
        var component = this.getComponent();
        component.registerEventSpans(registry);
    }

    @Override
    public void writeToSpriteBatch(SpriteBatch spriteBatch) {
        var component = this.getComponent();
        component.writeToSpriteBatch(spriteBatch);
    }

    
}
