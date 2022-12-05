package dreamlink.overlay.component.checkbox;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.border.dialog.DialogBorderComponent;
import dreamlink.overlay.component.border.dialog.DialogState;
import dreamlink.overlay.component.icon.IconComponent;
import dreamlink.overlay.eventspan.EventSpanHandler;
import dreamlink.overlay.eventspan.IEvent;
import dreamlink.overlay.eventspan.IEventSpan;
import dreamlink.overlay.eventspan.IEventSpanRegistry;
import dreamlink.overlay.eventspan.event.ClickEvent;

public class CheckBoxComponent extends WrapperComponent implements IEventSpan {
    
    private static final Vector2ic checkboxDimensions = new Vector2i(12);

    private final EventSpanHandler eventHandler = new EventSpanHandler();
    private final ICheckBoxComponentProvider provider;
    private final IComponent component; 

    public CheckBoxComponent(ICheckBoxComponentProvider provider) {
        this.provider = provider;
        this.component = new DialogBorderComponent(
            this::getDialogState,
            new IconComponent(
                this::getCheckSprite,
                CheckBoxComponent.checkboxDimensions
            )
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    private DialogState getDialogState() {
        if(this.provider.isCheckBoxDisabled()) {
            return DialogState.disabled;
        } 
        if(this.eventHandler.isPressed() && this.eventHandler.isHovered()) {
            return DialogState.disabled;
        }

        return DialogState.focused;
    }

    private ISpriteTemplate getCheckSprite() {
        return this.provider.getValue() ? MenuTextureSample.checkBoxTick : null;
    }

    @Override
    public void onEvent(IEvent event) {
        this.eventHandler.onEvent(event);
        if(event instanceof ClickEvent) {
            var isChecked = this.provider.getValue();
            this.provider.onChange(!isChecked);
        }
    }

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {
        if(!this.provider.isCheckBoxDisabled()) {
            registry.register(this);
        }
    }

    @Override
    public int getDragDistanceThreshold() {
        return Integer.MAX_VALUE;
    }
    
}