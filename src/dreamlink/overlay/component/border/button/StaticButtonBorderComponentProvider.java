package dreamlink.overlay.component.border.button;

public class StaticButtonBorderComponentProvider implements IButtonBorderComponentProvider {

    private final ButtonState state;

    public StaticButtonBorderComponentProvider(ButtonState state) {
        this.state = state;
    }

    @Override
    public ButtonState getButtonState() {
        return this.state;
    }
    
}
