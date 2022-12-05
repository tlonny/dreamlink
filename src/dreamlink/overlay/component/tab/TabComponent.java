package dreamlink.overlay.component.tab;

import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.border.tab.TabBorderComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.eventspan.IEvent;
import dreamlink.overlay.eventspan.IEventSpan;
import dreamlink.overlay.eventspan.IEventSpanRegistry;
import dreamlink.overlay.eventspan.event.PressStartEvent;

public class TabComponent extends WrapperComponent implements IEventSpan {

    private static final int padding = 2;

    private final ITabComponentProvider provider;
    private final TabBorderComponent component;

    public TabComponent(
        ITabComponentProvider provider,
        IComponent component
    ) {
        this.provider = provider;
        this.component = new TabBorderComponent(
            this.provider::isTabSelected,
            new BoxComponent(
                new PaddingComponent(component, padding),
                BoxDimension.grow(),
                BoxDimension.wrap()
            )
        );
    }

    @Override
    public void computePosition(Vector2ic position, SpriteHeight spriteHeight) {
        super.computePosition(position, spriteHeight);
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    @Override
    public void onEvent(IEvent event) {
        if(event instanceof PressStartEvent)  {
            this.provider.onTabSelect();
        }
    }

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {
        if(!this.provider.isTabSelected()) {
            registry.register(this);
        }
    }

    @Override
    public int getDragDistanceThreshold() {
        return Integer.MAX_VALUE;
    }

}
