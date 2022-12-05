package dreamlink.overlay.component.window;

import org.joml.Vector2i;

import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.graphics.sprite.template.SolidSpriteTemplate;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.background.BackgroundComponent;
import dreamlink.overlay.component.background.StaticBackgroundComponentProvider;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.icon.IconComponent;
import dreamlink.overlay.component.icon.StaticIconComponentProvider;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.utility.maths.Vector4fMaths;

public class WindowTitleComponent extends WrapperComponent {

    private static int titleHorizontalPadding = 5;
    private static int titleVerticalPadding = 2;
    private static int titleSpacing = 5;
    private static Vector2i iconDimensions = new Vector2i(16);

    private final IComponent component;

    public WindowTitleComponent(ISpriteTemplate icon, String title) {
        this.component = new BackgroundComponent(
            new StaticBackgroundComponentProvider(SolidSpriteTemplate.overlayHighlight),
            new BoxComponent(
                new PaddingComponent(
                    new HorizontalSpanComponent(Alignment.start, WindowTitleComponent.titleSpacing)
                        .addComponent(
                            new IconComponent(
                                new StaticIconComponentProvider(icon),
                                WindowTitleComponent.iconDimensions
                            )
                        )
                        .addComponent(
                            new TextLineLabelComponent(
                                title, 
                                FontDecoration.underline,
                                Vector4fMaths.white
                            )
                        ),
                    WindowTitleComponent.titleVerticalPadding,
                    WindowTitleComponent.titleVerticalPadding,
                    WindowTitleComponent.titleHorizontalPadding,
                    WindowTitleComponent.titleHorizontalPadding
                ),
                BoxDimension.grow(Alignment.start),
                BoxDimension.wrap()
            )
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
