package dreamlink.overlay.component.text.line.view;

import dreamlink.overlay.component.text.CharacterData;

public interface ITextLineViewComponentProvider {

    public int getCharacterSize();

    public int getCharacterOffset();

    public void setCharacterState(int characterIndex, CharacterData characterState);

    
}
