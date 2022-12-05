package dreamlink.overlay.component.border.button;

import dreamlink.graphics.sprite.template.BorderSpriteTemplate;
import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.background.BackgroundComponent;
import dreamlink.overlay.component.background.IBackgroundComponentProvider;

public class ButtonBorderComponent extends WrapperComponent {

    private class InternalBackgroundComponentProvider implements IBackgroundComponentProvider {

        @Override
        public ISpriteTemplate getSprite() {
            var state = ButtonBorderComponent.this.provider.getButtonState();

            if(state == ButtonState.pressed) {
                return BorderSpriteTemplate.buttonPressed;
            }

            if(state == ButtonState.disabled) {
                return BorderSpriteTemplate.buttonDisabled;
            }
            
            return BorderSpriteTemplate.button;
        }

    }

    private static final int padding = 2;

    private final IButtonBorderComponentProvider provider;
    private final IComponent component;

    public ButtonBorderComponent(
        IButtonBorderComponentProvider provider,
        IComponent component
    ) {
        this.provider = provider;
        this.component = new BackgroundComponent(
            new InternalBackgroundComponentProvider(),
            new PaddingComponent(component, ButtonBorderComponent.padding)
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

}
