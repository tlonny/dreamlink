package dreamlink.overlay.component.span;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.eventspan.IEventSpanRegistry;

public class HorizontalSpanComponent implements IComponent {

    private final List<IComponent> components = new ArrayList<>();
    private final Vector2i initialDimensions = new Vector2i();
    private final Vector2i dimensions = new Vector2i();
    private final Vector2i position = new Vector2i();
    private final int spacing;
    private final Alignment alignment;
    private SpriteHeight spriteHeight;

    public HorizontalSpanComponent(Alignment alignment, int spacing) {
        this.alignment = alignment;
        this.spacing = spacing;
    }

    @Override
    public Vector2i getInitialDimensions(Vector2i target) {
        return target.set(this.initialDimensions);
    }

    @Override
    public Vector2i getDimensions(Vector2i target) {
        return target.set(this.dimensions);
    }

    public HorizontalSpanComponent addComponent(IComponent component) {
        this.components.add(component);
        return this;
    }

    @Override
    public void computeInitialDimensions() {
        this.initialDimensions.zero();

        var componentDimensions = new Vector2i();
        for(var ix = 0; ix < this.components.size(); ix += 1) {
            var component = this.components.get(ix);
            component.computeInitialDimensions();
            component.getInitialDimensions(componentDimensions);
            this.initialDimensions.x += componentDimensions.x;
            this.initialDimensions.y = Math.max(this.initialDimensions.y, componentDimensions.y);
        }

        var spacing = this.spacing * Math.max(this.components.size() - 1, 0);
        this.initialDimensions.x += spacing;
    }

    @Override
    public void computeDimensions(Vector2ic availableSpace) {
        var extraWidth = availableSpace.x() - this.initialDimensions.x;
        var requestedExtraWidth = 0;

        this.dimensions.zero();

        var componentDimensions = new Vector2i();
        var componentInitialDimensions = new Vector2i();
        var componentAvailableSpace = new Vector2i();
        for(var ix = 0; ix < this.components.size(); ix += 1) {
            var component = this.components.get(ix);
            component.getInitialDimensions(componentInitialDimensions);
            var initialWidth = componentInitialDimensions.x;

            componentAvailableSpace.set(availableSpace);
            componentAvailableSpace.x = initialWidth + extraWidth;
            component.computeDimensions(componentAvailableSpace);
            component.getDimensions(componentDimensions);
            var refinedWidth = componentDimensions.x;
            var componentExtraWidth = refinedWidth - initialWidth;
            requestedExtraWidth += componentExtraWidth;
            this.dimensions.x += refinedWidth;
            this.dimensions.y = Math.max(this.dimensions.y, componentDimensions.y);
        }

        if(requestedExtraWidth > extraWidth) {
            var reRequestedExtraWidth = 0;
            this.dimensions.zero();
            for(var ix = 0; ix < this.components.size(); ix += 1) {
                var component = this.components.get(ix);
                component.getInitialDimensions(componentInitialDimensions);
                component.getDimensions(componentDimensions);
                var componentExtraWidth = componentDimensions.x - componentInitialDimensions.x;
                var componentExtraWidthRatio = (float) componentExtraWidth / requestedExtraWidth;
                var componentExtraWidthShare = ix == this.components.size() - 1 
                    ? extraWidth - reRequestedExtraWidth
                    : (int)(componentExtraWidthRatio * extraWidth);
                componentAvailableSpace.set(availableSpace);
                componentAvailableSpace.x = componentInitialDimensions.x + componentExtraWidthShare;
                component.computeDimensions(componentAvailableSpace);
                component.getDimensions(componentDimensions);
                this.dimensions.x += componentDimensions.x;
                this.dimensions.y = Math.max(this.dimensions.y, componentDimensions.y);
                reRequestedExtraWidth += componentExtraWidthShare;
            }
        }

        this.dimensions.x += this.spacing * Math.max(this.components.size() - 1, 0);
    }

    @Override
    public void computePosition(Vector2ic position, SpriteHeight spriteHeight) {
        this.position.set(position);
        this.spriteHeight = spriteHeight;
        var dimensions = this.getDimensions(new Vector2i());
        var componentDimensions = new Vector2i();
        var positionCursor = new Vector2i(position);
        for(var ix = 0; ix < this.components.size(); ix += 1) {
            var component = this.components.get(ix);
            component.getDimensions(componentDimensions);
            positionCursor.y = this.alignment.getOffset(
                dimensions.y,
                componentDimensions.y
            ) + position.y();
            component.computePosition(positionCursor, spriteHeight);
            positionCursor.x += componentDimensions.x + this.spacing;
        }
    }

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {
        for(var ix = 0; ix < this.components.size(); ix += 1) {
            this.components.get(ix).registerEventSpans(registry);
        }
    }

    @Override
    public void writeToSpriteBatch(SpriteBatch spriteBatch) {
        for(var ix = 0; ix < this.components.size(); ix += 1) {
            this.components.get(ix).writeToSpriteBatch(spriteBatch);
        }
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
