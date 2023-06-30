package doors.ui.element;

import java.util.Arrays;

import doors.graphics.texture.TextureChannel;
import doors.graphics.texture.TextureSample;
import doors.ui.UITextureAtlas;
import doors.utility.geometry.Vector2in;
import doors.utility.geometry.Vector3fl;

public class MenuTitleElement extends WrapperElement {

    private static Vector2in BUTTON_DIMENSIONS = new Vector2in(250, 0);

    public MenuTitleElement(TextureSample icon, String text) {
        this.child = new BackgroundElement(
            new PaddingElement(
                new MinDimensionsElement(
                    new AlignmentElement(
                        new HorizontalSpanElement(
                            Arrays.asList(
                                new AlignmentElement(new IconElement(TextureChannel.UI_TEXTURE_CHANNEL, icon)),
                                new AlignmentElement(new TextLabelElement(text, true, Vector3fl.ONE))
                            ), 2
                        ),
                        AlignmentElement.HorizontalAlignment.LEFT
                    ),
                    BUTTON_DIMENSIONS
                ),
                2
            ),
            TextureChannel.UI_TEXTURE_CHANNEL,
            UITextureAtlas.TITLE_BAR
        );
    }

}
