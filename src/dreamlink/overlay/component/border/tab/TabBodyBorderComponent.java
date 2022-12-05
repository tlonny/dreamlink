package dreamlink.overlay.component.border.tab;

import dreamlink.graphics.sprite.template.BorderSpriteTemplate;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.background.BackgroundComponent;
import dreamlink.overlay.component.background.StaticBackgroundComponentProvider;

public class TabBodyBorderComponent extends WrapperComponent {

    private static final int padding = 2;

    private final IComponent component;

    public TabBodyBorderComponent(IComponent component) {
        this.component = new BackgroundComponent(
            new StaticBackgroundComponentProvider(BorderSpriteTemplate.tabBody),
            new PaddingComponent(component, TabBodyBorderComponent.padding)
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
