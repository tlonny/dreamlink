package dreamlink.overlay.component.text.line;

import org.joml.Vector4f;
import org.joml.Vector4fc;

import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.text.CharacterData;
import dreamlink.overlay.component.text.line.view.ITextLineViewComponentProvider;
import dreamlink.overlay.component.text.line.view.TextLineViewComponent;

public class TextLineLabelComponent extends WrapperComponent {

    private class InternalTextLineComponentProvider implements ITextLineViewComponentProvider {

        @Override
        public void setCharacterState(int characterIndex, CharacterData state) {
            state.set(
                TextLineLabelComponent.this.text.charAt(characterIndex),
                TextLineLabelComponent.this.fontDecoration,
                TextLineLabelComponent.this.color
            );
        }

        @Override
        public int getCharacterOffset() {
            return 0;
        }

        @Override
        public int getCharacterSize() {
            return TextLineLabelComponent.this.text.length();
        }

    }

    private final IComponent component;
    private final String text;
    private final FontDecoration fontDecoration;
    private final Vector4f color;

    public TextLineLabelComponent(
        String text,
        FontDecoration fontDecoration,
        Vector4fc color
    ) {
        this.text = text;
        this.fontDecoration = fontDecoration;
        this.color = new Vector4f(color);

        this.component = new BoxComponent(
            new TextLineViewComponent(
                new InternalTextLineComponentProvider(),
                Alignment.start
            ),
            BoxDimension.fixed(MenuTextureSample.glyphDimensions.x() * this.text.length()),
            BoxDimension.fixed(MenuTextureSample.glyphDimensions.y())
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    
}