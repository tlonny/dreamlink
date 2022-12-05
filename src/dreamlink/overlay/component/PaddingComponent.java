package dreamlink.overlay.component;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.overlay.eventspan.IEventSpanRegistry;

public class PaddingComponent implements IComponent {

    public final int paddingTop;
    public final int paddingBottom;
    public final int paddingLeft;
    public final int paddingRight;

    private final Vector2i initialDimensions = new Vector2i();
    private final Vector2i dimensions = new Vector2i();
    private final Vector2i position = new Vector2i();
    private SpriteHeight spriteHeight;
    private final IComponent component;

    public PaddingComponent(IComponent component, int paddingTop, int paddingBottom, int paddingLeft, int paddingRight) {
        this.component = component;
        this.paddingTop = paddingTop;
        this.paddingBottom = paddingBottom;
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
    }

    public PaddingComponent(IComponent content, int padding) {
        this(content, padding, padding, padding, padding);
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
    public Vector2i getPosition(Vector2i target) {
        return target.set(this.position);
    }

    @Override
    public void computeInitialDimensions() {
        var horizontalPadding = this.paddingLeft + this.paddingRight;
        var verticalPadding = this.paddingTop + this.paddingBottom;

        this.component.computeInitialDimensions();
        this.component.getInitialDimensions(this.initialDimensions);
        this.initialDimensions.add(horizontalPadding, verticalPadding);
    }

    @Override
    public void computeDimensions(Vector2ic availableSpace) {
        var horizontalPadding = this.paddingLeft + this.paddingRight;
        var verticalPadding = this.paddingTop + this.paddingBottom;

        this.component.computeDimensions(
            new Vector2i(availableSpace).sub(horizontalPadding, verticalPadding)
        );

        this.component.getDimensions(this.dimensions);
        this.dimensions.add(horizontalPadding, verticalPadding);
    }

    @Override
    public void computePosition(Vector2ic position, SpriteHeight spriteHeight) {
        this.spriteHeight = spriteHeight;
        this.position.set(position);
        this.component.computePosition(
            new Vector2i(position).add(this.paddingLeft, this.paddingTop),
            spriteHeight
        );
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
    public SpriteHeight getSpriteHeight() {
        return this.spriteHeight;
    }
    
}
