package dreamlink.overlay.component.button;

import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.border.button.ButtonBorderComponent;
import dreamlink.overlay.component.border.button.ButtonState;
import dreamlink.overlay.eventspan.EventSpanHandler;
import dreamlink.overlay.eventspan.IEvent;
import dreamlink.overlay.eventspan.IEventSpan;
import dreamlink.overlay.eventspan.IEventSpanRegistry;
import dreamlink.overlay.eventspan.event.ClickEvent;

public class ButtonComponent extends WrapperComponent implements IEventSpan {

    private final IButtonComponentProvider provider;
    private final IComponent component;
    private final EventSpanHandler eventHandler = new EventSpanHandler();

    public ButtonComponent(
        IButtonComponentProvider provider,
        IComponent component
    ) {
        this.provider = provider;
        this.component = new ButtonBorderComponent(
            this::getButtonSchemaState,
            component
        );
    }

    private ButtonState getButtonSchemaState() {
        if(this.provider.isButtonDisabled()) {
            return ButtonState.disabled;
        } else if(this.eventHandler.isPressed() && this.eventHandler.isHovered()) {
            return ButtonState.pressed;
        } else {
            return ButtonState.normal;
        }
    } 

    @Override
    public void onEvent(IEvent event) {
        this.eventHandler.onEvent(event);
        if(event instanceof ClickEvent)  {
            this.provider.onButtonClick();
        }
    }

    @Override
    public int getDragDistanceThreshold() {
        return Integer.MAX_VALUE;
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {
        if(!this.provider.isButtonDisabled()) {
            registry.register(this);
        }
    }

}
