package dreamlink.overlay.component.window;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.border.WindowBorderComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.window.Window;

public class WindowComponent extends WrapperComponent {

    private static int spacing = 3;

    private IComponent component;

    public WindowComponent(
        ISpriteTemplate icon, 
        String title, 
        IComponent content
    ) {
        this.component = new WindowBorderComponent(
            new VerticalSpanComponent(Alignment.start, WindowComponent.spacing)
                .addComponent(new WindowTitleComponent(icon, title))
                .addComponent(content)
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    @Override
    public void computeDimensions(Vector2ic availableSpace) {
        var dimensions = this.getInitialDimensions(new Vector2i());
        super.computeDimensions(dimensions);
    }

    @Override
    public void computePosition(Vector2ic position, SpriteHeight spriteHeight) {
        var resolution = Window.instance.getResolution(new Vector2i());
        var dimensions = this.getDimensions(new Vector2i());
        var windowPosition = new Vector2i(
            (resolution.x - dimensions.x) / 2,
            (resolution.y - dimensions.y) / 2
        );
        super.computePosition(windowPosition, spriteHeight);
    }
}