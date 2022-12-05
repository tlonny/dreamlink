package dreamlink.overlay.component.border.tab;

import dreamlink.graphics.sprite.template.BorderSpriteTemplate;
import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.background.BackgroundComponent;
import dreamlink.overlay.component.background.IBackgroundComponentProvider;

public class TabBorderComponent extends WrapperComponent {

    private class InternalBackgroundComponentProvider implements IBackgroundComponentProvider {

        @Override
        public ISpriteTemplate getSprite() {
            return TabBorderComponent.this.provider.isTabSelected()
                ? BorderSpriteTemplate.tabSelected
                : BorderSpriteTemplate.tab;
        }

    }

    private static final int padding = 2;

    private final ITabBorderComponentProvider provider;
    private final IComponent component;

    public TabBorderComponent(
        ITabBorderComponentProvider provider,
        IComponent component
    ) {
        this.provider = provider;
        this.component = new BackgroundComponent(
            new InternalBackgroundComponentProvider(),
            new PaddingComponent(component, TabBorderComponent.padding)
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
