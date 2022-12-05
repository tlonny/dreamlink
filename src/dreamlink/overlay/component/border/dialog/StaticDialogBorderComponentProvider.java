package dreamlink.overlay.component.border.dialog;

public class StaticDialogBorderComponentProvider implements IDialogBorderComponentProvider {

    private final DialogState state;

    public StaticDialogBorderComponentProvider(DialogState state) {
        this.state = state;
    }

    @Override
    public DialogState getDialogState() {
        return this.state;
    }
    
}
