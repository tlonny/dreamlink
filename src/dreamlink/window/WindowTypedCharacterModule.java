package dreamlink.window;

import java.util.ArrayList;
import java.util.List;

public class WindowTypedCharacterModule {

    private final List<Character> typedCharacters = new ArrayList<>();
    private final IWindowModuleProvider provider;

    public WindowTypedCharacterModule(IWindowModuleProvider provider) {
        this.provider = provider;
    }

    private void onCharEvent(long window, int codePoint) {
        if(codePoint <= 127) {
            this.typedCharacters.add((char)codePoint);
        }
    }

    public void setup() {
        var GLFWModule = this.provider.getGLFWModule();
        GLFWModule.setCharCallback(this::onCharEvent);
    }

    public Iterable<Character> getTypedCharacters() {
        return this.typedCharacters;
    }

    public void advance() {
        this.typedCharacters.clear();
    }

}
