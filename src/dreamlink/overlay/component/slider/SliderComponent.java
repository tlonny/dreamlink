package dreamlink.overlay.component.slider;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.template.BorderSpriteTemplate;
import dreamlink.graphics.sprite.template.SliderSpriteTemplate;
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
import dreamlink.window.Window;

public class SliderComponent extends WrapperComponent implements IEventSpan {

    private static final Vector2ic sliderDimensions = new Vector2i(8, 16);

    private final IComponent component;
    private final ISliderComponentProvider provider;
    private final EventSpanHandler eventHandler = new EventSpanHandler();

    public SliderComponent(ISliderComponentProvider provider) {
        this.provider = provider;
        this.component = new BackgroundComponent(
            new StaticBackgroundComponentProvider(SliderSpriteTemplate.instance),
            new BoxComponent(
                new EmptyComponent(),
                BoxDimension.grow(),
                BoxDimension.fixed(SliderComponent.sliderDimensions.y())
            )
        );
    }

    private final Vector2i onEventMousePosition = new Vector2i();
    private final Vector2i onEventPosition = new Vector2i();
    private final Vector2i onEventDimensions = new Vector2i();

    @Override
    public void onEvent(IEvent event) {
        this.eventHandler.onEvent(event);
        if(event instanceof DragEvent || event instanceof PressStartEvent) {
            var position = this.getPosition(this.onEventPosition);
            var mousePosition = Window.instance.getMousePosition(this.onEventMousePosition);
            var dimensions = this.component.getDimensions(this.onEventDimensions);
            var slideFactor = (mousePosition.x - position.x) / (float) dimensions.x;
            this.provider.onChange(FloatMaths.clamp(slideFactor, 0f, 1f));
        }
    }

    @Override
    public int getDragDistanceThreshold() {
        return 0;
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    public void registerEventSpans(IEventSpanRegistry registry) {
        if(!this.provider.isSliderDisabled()) {
            registry.register(this);
        }
    }

    private final Vector2i writeToSpriteBatchPosition = new Vector2i();
    private final Vector2i writeToSpriteBatchDimensions = new Vector2i();

    @Override
    public void writeToSpriteBatch(SpriteBatch spriteBatch) {
        super.writeToSpriteBatch(spriteBatch);
        var buttonSprite = this.provider.isSliderDisabled()
            ? BorderSpriteTemplate.buttonDisabled
            : BorderSpriteTemplate.button;

        var dimensions = this.getDimensions(this.writeToSpriteBatchDimensions);
        var slideRange = dimensions.x - SliderComponent.sliderDimensions.x();
        var position = this.getPosition(this.writeToSpriteBatchPosition);
        position.x += slideRange * this.provider.getSlideFactor();
        
        buttonSprite.writeToSpriteBatch(
            spriteBatch,
            position,
            SliderComponent.sliderDimensions,
            this.getSpriteHeight()
        );
        
    }
    
}
