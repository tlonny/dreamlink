package dreamlink.overlay.component.box;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.eventspan.IEventSpanRegistry;

public class BoxComponent implements IComponent {

    private final Vector2i position = new Vector2i();
    private final Vector2i initialDimensions = new Vector2i();
    private final Vector2i dimensions = new Vector2i();
    private final BoxDimension width;
    private final BoxDimension height;
    private final IComponent component;
    private SpriteHeight spriteHeight;

    public BoxComponent(
        IComponent component, 
        BoxDimension width, 
        BoxDimension height
    ) {
        this.width = width;
        this.height = height;
        this.component = component;
    }

    @Override
    public Vector2i getInitialDimensions(Vector2i target) {
        return target.set(this.initialDimensions);
    }

    @Override
    public Vector2i getDimensions(Vector2i target) {
        return target.set(this.dimensions);
    }

    @Override
    public void computeInitialDimensions() {
        this.component.computeInitialDimensions();
        var componentInitialDimensions = this.component.getInitialDimensions(new Vector2i());
        this.initialDimensions.set(
            Math.max(this.width.minValue, componentInitialDimensions.x),
            Math.max(this.height.minValue, componentInitialDimensions.y)
        );
    }

    @Override
    public void computeDimensions(Vector2ic availableSpace) {
        this.dimensions.set(
            Math.max(this.initialDimensions.x, Math.min(this.width.maxValue, availableSpace.x())),
            Math.max(this.initialDimensions.y, Math.min(this.height.maxValue, availableSpace.y()))
        );
        this.component.computeDimensions(this.dimensions);
    }

    @Override
    public void computePosition(Vector2ic position, SpriteHeight spriteHeight) {
        var innerDimensions = this.component.getDimensions(new Vector2i());
        var adjustedPosition = new Vector2i(
            position.x() + this.width.alignment.getOffset(this.dimensions.x, innerDimensions.x),
            position.y() + this.height.alignment.getOffset(this.dimensions.y, innerDimensions.y)
        );

        this.position.set(position);
        this.spriteHeight = spriteHeight;
        this.component.computePosition(adjustedPosition, spriteHeight);
    }

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {
        this.component.registerEventSpans(registry);
    }

    @Override
    public void writeToSpriteBatch(SpriteBatch spriteBatch) {
        this.component.writeToSpriteBatch(spriteBatch);
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
