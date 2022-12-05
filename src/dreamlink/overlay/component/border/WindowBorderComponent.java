package dreamlink.overlay.component.border;

import dreamlink.graphics.sprite.template.BorderSpriteTemplate;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.background.BackgroundComponent;
import dreamlink.overlay.component.background.StaticBackgroundComponentProvider;

public class WindowBorderComponent extends WrapperComponent {

    private static final int padding = 3;

    private final IComponent component;

    public WindowBorderComponent(IComponent component) {
        this.component = new BackgroundComponent(
            new StaticBackgroundComponentProvider(BorderSpriteTemplate.window),
            new PaddingComponent(component, WindowBorderComponent.padding)
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

}
