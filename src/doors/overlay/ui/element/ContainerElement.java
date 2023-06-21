package doors.overlay.ui.element;

import doors.Config;
import doors.graphics.texture.TextureChannel;
import doors.overlay.ui.UITextureAtlas;
import doors.utility.geometry.Vector2in;

public class ContainerElement extends WrapperElement {

    public ContainerElement(IUIElement child) {
        this.child = new BackgroundElement(
            new MinDimensionsElement(
                new AlignmentElement(child),
                new Vector2in(Config.RESOLUTION)
            ),
            TextureChannel.UI_TEXTURE_CHANNEL,
            UITextureAtlas.BACKGROUND
        );
    }

}
