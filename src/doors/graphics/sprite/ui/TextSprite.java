package doors.graphics.sprite.ui;

import doors.core.graphics.font.AbstractFont;
import doors.core.graphics.font.FontDecoration;
import doors.core.graphics.sprite.AbstractTextSprite;
import doors.graphics.font.Font;
import doors.utility.vector.Vector3fl;

public class TextSprite extends AbstractTextSprite {

    public TextSprite(String text, FontDecoration fontDecoration, Vector3fl color) {
        super(text, fontDecoration, color);
    }

    @Override
    protected AbstractFont getFont() {
        return Font.FONT;
    }
    
}
