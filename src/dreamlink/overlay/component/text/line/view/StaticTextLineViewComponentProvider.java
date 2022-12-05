package dreamlink.overlay.component.text.line.view;

import org.joml.Vector4f;
import org.joml.Vector4fc;

import dreamlink.graphics.text.FontDecoration;
import dreamlink.overlay.component.text.CharacterData;

public class StaticTextLineViewComponentProvider implements ITextLineViewComponentProvider {

    private String text;
    private FontDecoration decoration;
    private Vector4f color;

    public StaticTextLineViewComponentProvider(
        String text, 
        FontDecoration decoration, 
        Vector4fc color
    ) {
        this.text = text;
        this.decoration = decoration;
        this.color = new Vector4f(color);
    }

    @Override
    public void setCharacterState(int characterIndex, CharacterData characterState) {
        characterState.set(
            this.text.charAt(characterIndex),
            this.decoration,
            this.color
        ); 
    }

    @Override
    public int getCharacterOffset() {
        return 0;
    }

    @Override
    public int getCharacterSize() {
        return this.text.length();
    }
    
}
