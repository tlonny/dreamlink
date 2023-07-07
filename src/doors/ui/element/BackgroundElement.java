package doors.ui.element;

import doors.core.graphics.sprite.SpriteMeshBufferWriter;
import doors.core.graphics.texture.TextureSample;
import doors.core.ui.IUIElement;
import doors.core.ui.WrapperElement;
import doors.Screen;
import doors.core.utility.vector.Vector3fl;

public class BackgroundElement extends WrapperElement {

    public TextureSample textureSample;
    private SpriteMeshBufferWriter spriteWriter;

    public BackgroundElement(IUIElement child, TextureSample textureSample) {
        this.child = child;
        this.textureSample = textureSample;
        this.spriteWriter = new SpriteMeshBufferWriter(Screen.SCREEN.meshBuffer);
    }

    @Override
    public void writeElement() {
        this.spriteWriter.writeSprite(
            this.textureSample,
            this.getPosition(),
            this.getDimensions(),
            Vector3fl.WHITE
        );
        super.writeElement();
    }


}
