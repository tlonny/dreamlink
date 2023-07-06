package doors.ui.element;

import java.util.Arrays;

import doors.Doors;
import doors.core.graphics.sprite.FontDecoration;
import doors.core.graphics.texture.TextureSample;
import doors.core.ui.HorizontalSpanElement;
import doors.core.ui.MinDimensionsElement;
import doors.core.ui.PaddingElement;
import doors.core.ui.WrapperElement;
import doors.core.ui.alignment.BoxAlignmentWrapper;
import doors.core.ui.alignment.HorizontalAlignment;
import doors.core.ui.alignment.VerticalAlignmentWrapper;
import doors.ui.UITextureAtlas;
import doors.core.utility.vector.Vector3fl;
import doors.core.utility.vector.Vector2in;

public class MenuTitleElement extends WrapperElement {

    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(250, 0);

    public MenuTitleElement(TextureSample icon, String text) {
        this.child = new BackgroundElement(
            new PaddingElement(
                new MinDimensionsElement(
                    new BoxAlignmentWrapper(
                        new HorizontalSpanElement(
                            Arrays.asList(
                                new VerticalAlignmentWrapper(new IconElement(Doors.TEXTURE_CHANNEL_UI, icon)),
                                new VerticalAlignmentWrapper(new TextLabelElement(text, FontDecoration.UNDERLINE, Vector3fl.WHITE))
                            ), 2
                        ),
                        HorizontalAlignment.LEFT
                    ),
                    BUTTON_DIMENSIONS
                ),
                2
            ),
            Doors.TEXTURE_CHANNEL_UI,
            UITextureAtlas.TITLE_BAR
        );
    }

}
