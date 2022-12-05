package dreamlink.overlay.component.border;

import dreamlink.graphics.sprite.template.BorderSpriteTemplate;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.background.BackgroundComponent;
import dreamlink.overlay.component.background.StaticBackgroundComponentProvider;

public class SeparatorBorderComponent extends WrapperComponent {

    private static final int padding = 3;

    private final IComponent component;

    public SeparatorBorderComponent(IComponent component) {
        this.component = new BackgroundComponent(
            new StaticBackgroundComponentProvider(BorderSpriteTemplate.separator),
            new PaddingComponent(component, SeparatorBorderComponent.padding)
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

}
