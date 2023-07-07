package doors.ui.element;

import doors.Config;
import doors.core.ui.IUIElement;
import doors.core.ui.MinDimensionsElement;
import doors.core.ui.WrapperElement;
import doors.core.ui.alignment.BoxAlignmentWrapper;
import doors.ui.UITextureAtlas;
import doors.core.utility.vector.Vector2in;

public class ContainerElement extends WrapperElement {

    public ContainerElement(IUIElement child) {
        this.child = new BackgroundElement(
            new MinDimensionsElement(
                new BoxAlignmentWrapper(child),
                new Vector2in(Config.RESOLUTION)
            ),
            UITextureAtlas.BACKGROUND
        );
    }

}
