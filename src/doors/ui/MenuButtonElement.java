package doors.ui;

import java.util.Arrays;

import doors.core.graphics.sprite.FontDecoration;
import doors.core.graphics.texture.TextureSample;
import doors.core.ui.HorizontalSpanElement;
import doors.core.ui.MinDimensionsElement;
import doors.core.ui.PaddingElement;
import doors.core.ui.WrapperElement;
import doors.core.ui.alignment.BoxAlignmentWrapper;
import doors.core.ui.alignment.VerticalAlignmentWrapper;
import doors.core.utility.Functional.IAction;
import doors.core.utility.vector.Vector3fl;
import doors.core.utility.vector.Vector2in;

public class MenuButtonElement extends WrapperElement {

    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(150, 0);

    public MenuButtonElement(TextureSample icon, String text, IAction action) {
        this.child = new PaddingElement(
            new ButtonElement(
                new MinDimensionsElement(
                    new BoxAlignmentWrapper(
                        new HorizontalSpanElement(
                            Arrays.asList(
                                new VerticalAlignmentWrapper(new IconElement(icon)),
                                new VerticalAlignmentWrapper(new TextLabelElement(text, FontDecoration.NORMAL, Vector3fl.BLACK))
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
