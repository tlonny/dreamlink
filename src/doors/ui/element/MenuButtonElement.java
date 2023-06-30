package doors.ui.element;

import java.util.Arrays;

import doors.graphics.texture.TextureChannel;
import doors.graphics.texture.TextureSample;
import doors.utility.Functional.IAction;
import doors.utility.geometry.Vector2in;
import doors.utility.geometry.Vector3fl;

public class MenuButtonElement extends WrapperElement {

    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(150, 0);

    public MenuButtonElement(TextureSample icon, String text, IAction action) {
        this.child = new PaddingElement(
            new ButtonElement(
                new MinDimensionsElement(
                    new AlignmentElement(
                        new HorizontalSpanElement(
                            Arrays.asList(
                                new AlignmentElement(new IconElement(TextureChannel.UI_TEXTURE_CHANNEL, icon)),
                                new AlignmentElement(new TextLabelElement(text, false, Vector3fl.ZERO))
                            ), 4
                        )
                    ),
                    BUTTON_DIMENSIONS
                ),
                action
            ),
            5
        );
    }

}
