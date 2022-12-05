package dreamlink.overlay.component.border.dialog;

import dreamlink.graphics.sprite.template.BorderSpriteTemplate;
import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.background.BackgroundComponent;
import dreamlink.overlay.component.background.IBackgroundComponentProvider;

public class DialogBorderComponent extends WrapperComponent {

    private class InternalBackgroundComponentProvider implements IBackgroundComponentProvider {

        @Override
        public ISpriteTemplate getSprite() {
            var state = DialogBorderComponent.this.provider.getDialogState();
            if(state == DialogState.disabled) {
                return BorderSpriteTemplate.dialogDisabled;
            }
            if(state == DialogState.blurred) {
                return BorderSpriteTemplate.dialogBlurred;
            }
            return BorderSpriteTemplate.dialog;
        }
    }

    private static final int padding = 2;

    private final IDialogBorderComponentProvider provider;
    private final IComponent component;

    public DialogBorderComponent(
        IDialogBorderComponentProvider provider,
        IComponent component
    ) {
        this.provider = provider;
        this.component = new BackgroundComponent(
            new InternalBackgroundComponentProvider(),
            new PaddingComponent(component, DialogBorderComponent.padding)
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
