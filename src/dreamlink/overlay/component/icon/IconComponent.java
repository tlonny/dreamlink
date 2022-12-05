package dreamlink.overlay.component.icon;

import org.joml.Vector2ic;

import dreamlink.overlay.component.EmptyComponent;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.background.BackgroundComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;

public class IconComponent extends WrapperComponent {

    private final IComponent component;

    public IconComponent(
        IIconComponentProvider provider,
        Vector2ic dimensions
    ) {
        this.component = new BackgroundComponent(
            provider::getSprite,
            new BoxComponent(
                new EmptyComponent(),
                BoxDimension.fixed(dimensions.x()),
                BoxDimension.fixed(dimensions.y())
            )
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
