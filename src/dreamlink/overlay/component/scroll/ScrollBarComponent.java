package dreamlink.overlay.component.scroll;

import dreamlink.window.Window;

import org.joml.Vector2i;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.template.BorderSpriteTemplate;
import dreamlink.graphics.sprite.template.SolidSpriteTemplate;
import dreamlink.overlay.component.EmptyComponent;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.background.BackgroundComponent;
import dreamlink.overlay.component.background.StaticBackgroundComponentProvider;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.eventspan.EventSpanHandler;
import dreamlink.overlay.eventspan.IEvent;
import dreamlink.overlay.eventspan.IEventSpan;
import dreamlink.overlay.eventspan.IEventSpanRegistry;
import dreamlink.overlay.eventspan.event.DragEvent;
import dreamlink.overlay.eventspan.event.PressStartEvent;
import dreamlink.utility.maths.FloatMaths;

public class ScrollBarComponent extends WrapperComponent implements IEventSpan {

    private static final int scrollBarWidth = 16;

    private final IScrollBarComponentProvider provider;
    private final IComponent component;
    private final EventSpanHandler eventHandler = new EventSpanHandler();

    private float startScrollFactor;
    private int startScrollMousePosition;

    public ScrollBarComponent(IScrollBarComponentProvider provider) {
        this.provider = provider;
        this.component = new BackgroundComponent(
            new StaticBackgroundComponentProvider(SolidSpriteTemplate.overlayWindow),
            new BoxComponent(
                new EmptyComponent(),
                BoxDimension.fixed(scrollBarWidth),
                BoxDimension.grow()
            )
        );
    }

    @Override
    public int getDragDistanceThreshold() {
        return 0;
    }

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {
        if(!this.provider.isScrollBarDisabled()) {
            registry.register(this);
        }

    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    private final Vector2i onEventMousePosition = new Vector2i();
    private final Vector2i onEventPosition = new Vector2i();
    private final Vector2i onEventDimensions = new Vector2i();

    @Override
    public void onEvent(IEvent event) {
        this.eventHandler.onEvent(event);

        var position = this.getPosition(this.onEventPosition);
        var scrollFactor = this.provider.getScrollFactor();
        var scrollHandleHeightFactor = this.provider.getScrollHandleHeightFactor();
        var mousePosition = Window.instance.getMousePosition(this.onEventMousePosition);
        var dimensions = this.component.getDimensions(this.onEventDimensions);

        var scrollHandleLength = (int)(dimensions.y * scrollHandleHeightFactor);
        var scrollRange = dimensions.y - scrollHandleLength;
        var scrollHandleStart = (int)(position.y + scrollRange * scrollFactor);
        var scrollHandleEnd = scrollHandleStart + scrollHandleLength;

        if(event instanceof PressStartEvent) {
            this.startScrollFactor = scrollFactor;
            this.startScrollMousePosition = mousePosition.y >= scrollHandleStart && mousePosition.y <= scrollHandleEnd 
                ? mousePosition.y
                : scrollHandleStart + scrollHandleLength / 2;
        }

        if(event instanceof DragEvent || event instanceof PressStartEvent) {
            var mouseDelta = mousePosition.y - this.startScrollMousePosition;
            var newScrollFactor = this.startScrollFactor + (float) mouseDelta / scrollRange;
            this.provider.onChange(FloatMaths.clamp(newScrollFactor, 0f, 1f));
        }
    }

    private final Vector2i writeToSpriteBatchDimensions = new Vector2i();
    private final Vector2i writeToSpriteBatchPosition = new Vector2i();
    private final Vector2i writeToSpriteBatchHandlePosition = new Vector2i();
    private final Vector2i writeToSpriteBatchHandleDimensions = new Vector2i();

    @Override
    public void writeToSpriteBatch(SpriteBatch spriteBatch) {
        super.writeToSpriteBatch(spriteBatch);

        var position = this.getPosition(this.writeToSpriteBatchPosition);
        var dimensions = this.getDimensions(this.writeToSpriteBatchDimensions);
        var scrollHandleLength = (int)(dimensions.y * this.provider.getScrollHandleHeightFactor());
        var scrollRange = dimensions.y - scrollHandleLength;
        var scrollHandleStart = (int)(position.y + this.provider.getScrollFactor() * scrollRange);

        if(this.provider.isScrollBarDisabled()) {
            BorderSpriteTemplate.buttonDisabled.writeToSpriteBatch(
                spriteBatch,
                position,
                dimensions,
                this.getSpriteHeight()
            );
        } else {
            BorderSpriteTemplate.button.writeToSpriteBatch(
                spriteBatch,
                this.writeToSpriteBatchHandlePosition.set(position.x, scrollHandleStart),
                this.writeToSpriteBatchHandleDimensions.set(ScrollBarComponent.scrollBarWidth, scrollHandleLength),
                this.getSpriteHeight()
            );
        }
    }
}
