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

public class VerticalSpanComponent implements IComponent {

    private final List<IComponent> components = new ArrayList<>();
    private final Vector2i initialDimensions = new Vector2i();
    private final Vector2i dimensions = new Vector2i();
    private final Vector2i position = new Vector2i();
    private final int spacing;
    private final Alignment alignment;
    private SpriteHeight spriteHeight;

    public VerticalSpanComponent(Alignment alignment, int spacing) {
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

    public VerticalSpanComponent addComponent(IComponent component) {
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
            this.initialDimensions.x = Math.max(this.initialDimensions.x, componentDimensions.x);
            this.initialDimensions.y += componentDimensions.y;
        }

        var spacing = this.spacing * Math.max(this.components.size() - 1, 0);
        this.initialDimensions.y += spacing;
    }

    @Override
    public void computeDimensions(Vector2ic availableSpace) {
        var extraHeight = availableSpace.y() - this.initialDimensions.y;
        var requestedExtraHeight = 0;

        this.dimensions.zero();

        var componentDimensions = new Vector2i();
        var componentInitialDimensions = new Vector2i();
        var componentAvailableSpace = new Vector2i();
        for(var ix = 0; ix < this.components.size(); ix += 1) {
            var component = this.components.get(ix);
            component.getInitialDimensions(componentInitialDimensions);
            var initialHeight = componentInitialDimensions.y;

            componentAvailableSpace.set(availableSpace);
            componentAvailableSpace.y = initialHeight + extraHeight;
            component.computeDimensions(componentAvailableSpace);
            component.getDimensions(componentDimensions);
            var refinedHeight = componentDimensions.y;
            var componentExtraHeight = refinedHeight - initialHeight;
            requestedExtraHeight += componentExtraHeight;
            this.dimensions.x = Math.max(this.dimensions.x, componentDimensions.x);
            this.dimensions.y += refinedHeight;
        }

        if(requestedExtraHeight > extraHeight) {
            var reRequestedExtraHeight = 0;
            this.dimensions.zero();
            for(var ix = 0; ix < this.components.size(); ix += 1) {
                var component = this.components.get(ix);
                component.getInitialDimensions(componentInitialDimensions);
                component.getDimensions(componentDimensions);
                var componentExtraHeight = componentDimensions.y - componentInitialDimensions.y;
                var componentExtraHeightRatio = (float) componentExtraHeight / requestedExtraHeight;
                var componentExtraHeightShare = ix == this.components.size() - 1
                    ? extraHeight - reRequestedExtraHeight
                    : (int)(componentExtraHeightRatio * extraHeight);

                componentAvailableSpace.set(availableSpace);
                componentAvailableSpace.y = componentInitialDimensions.y + componentExtraHeightShare;
                component.computeDimensions(componentAvailableSpace);
                component.getDimensions(componentDimensions);
                this.dimensions.x = Math.max(this.dimensions.x, componentDimensions.x);
                this.dimensions.y += componentDimensions.y;
                reRequestedExtraHeight += componentExtraHeightShare;
            }
        }

        this.dimensions.y += this.spacing * Math.max(this.components.size() - 1, 0);
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
            positionCursor.x = this.alignment.getOffset(
                dimensions.x,
                componentDimensions.x
            ) + position.x();
            component.computePosition(positionCursor, spriteHeight);
            positionCursor.y += componentDimensions.y + this.spacing;
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
