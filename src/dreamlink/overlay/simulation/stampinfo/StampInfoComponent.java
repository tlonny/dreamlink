package dreamlink.overlay.simulation.stampinfo;

import dreamlink.graphics.text.FontDecoration;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.text.CharacterData;
import dreamlink.overlay.component.text.line.view.ITextLineViewComponentProvider;
import dreamlink.overlay.component.text.line.view.TextLineViewComponent;
import dreamlink.utility.maths.Vector4fMaths;

public class StampInfoComponent extends WrapperComponent {

    private static final String colonInfix = ": ";

    private class InternalTextLineComponentProvider implements ITextLineViewComponentProvider {

        @Override
        public void setCharacterState(int characterIndex, CharacterData state) {
            var stamp = StampInfoComponent.this.provider.getStamp();
            if(stamp == null) {
                state.clear();
                return;
            }
            
            var stampType = stamp.getStampType();
            var stampName = stamp.getStampName();

            if(characterIndex < stampType.length()) {
                state.set(
                    stampType.charAt(characterIndex),
                    FontDecoration.normal,
                    Vector4fMaths.black
                );
                return;
            }

            characterIndex -= stampType.length();
            if(characterIndex < StampInfoComponent.colonInfix.length()) {
                state.set(
                    StampInfoComponent.colonInfix.charAt(characterIndex),
                    FontDecoration.normal,
                    Vector4fMaths.black
                );
                return;
            }

            characterIndex -= StampInfoComponent.colonInfix.length();
            state.set(
                stampName.charAt(characterIndex),
                FontDecoration.underline,
                Vector4fMaths.blue
            );
        }

        @Override
        public int getCharacterOffset() {
            return 0;
        }

        @Override
        public int getCharacterSize() {
            var stamp = StampInfoComponent.this.provider.getStamp();
            if(stamp == null) {
                return 0;
            }

            return 0
                + stamp.getStampType().length() 
                + StampInfoComponent.colonInfix.length() 
                + stamp.getStampName().length();
        }
    }

    private final IComponent component;
    private final IStampInfoComponentProvider provider;

    public StampInfoComponent(IStampInfoComponentProvider provider) {
        this.provider = provider;
        this.component = new TextLineViewComponent(
            new InternalTextLineComponentProvider(),
            Alignment.start
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
