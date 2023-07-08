package doors.ui;

import doors.core.config.Config;
import doors.core.ui.IUIElement;
import doors.core.ui.MinDimensionsElement;
import doors.core.ui.WrapperElement;
import doors.core.ui.alignment.BoxAlignmentWrapper;
import doors.graphics.ui.UITextureAtlas;
import doors.core.utility.vector.Vector2in;

public class ContainerElement extends WrapperElement {

    public ContainerElement(IUIElement child) {
        this.child = new BackgroundElement(
            new MinDimensionsElement(
                new BoxAlignmentWrapper(child),
                new Vector2in(Config.CONFIG.getResolution())
            ),
            UITextureAtlas.BACKGROUND
        );
    }

}
