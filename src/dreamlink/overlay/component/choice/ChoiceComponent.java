package dreamlink.overlay.component.choice;

import java.util.HashMap;
import java.util.Map;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.eventspan.IEventSpanRegistry;

public class ChoiceComponent<T> implements IComponent {

    private final IChoiceComponentProvider<T> provider;
    private final Vector2i initialDimensions = new Vector2i();
    private final Vector2i dimensions = new Vector2i();
    private final Vector2i position = new Vector2i();
    private SpriteHeight spriteHeight;
    private final Map<T, IComponent> componentMap = new HashMap<>();

    public ChoiceComponent(IChoiceComponentProvider<T> provider) {
        this.provider = provider;
    }

    public ChoiceComponent<T> addComponent(T key, IComponent component) {
        this.componentMap.put(key, component);
        return this;
    }

    public IComponent getSelectedComponent() {
        return this.componentMap.get(this.provider.getSelectedChoiceKey());
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
        this.initialDimensions.zero();
        var componentDimensions = new Vector2i();
        for(var component : this.componentMap.values()) {
            component.computeInitialDimensions();
            component.getInitialDimensions(componentDimensions);
            this.initialDimensions.max(componentDimensions);
        }
    }

    @Override
    public void computeDimensions(Vector2ic availableSpace) {
        this.dimensions.zero();

        var componentDimensions = new Vector2i();
        for(var component : this.componentMap.values()) {
            component.computeDimensions(availableSpace);
            component.getDimensions(componentDimensions);
            this.dimensions.max(componentDimensions);
        }
    }

    @Override
    public void computePosition(Vector2ic position, SpriteHeight height) {
        this.position.set(position);
        this.spriteHeight = height;

        for(var component : this.componentMap.values()) {
            component.computePosition(position, height);
        }
    }

    @Override
    public void writeToSpriteBatch(SpriteBatch spriteBatch) {
        var selectedComponent = this.getSelectedComponent();
        if(selectedComponent != null) {
            selectedComponent.writeToSpriteBatch(spriteBatch);
        }
    }

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {
        var selectedComponent = this.getSelectedComponent();
        if(selectedComponent != null) {
            selectedComponent.registerEventSpans(registry);
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
