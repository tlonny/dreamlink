package dreamlink.overlay.component.checkbox;

public interface ICheckBoxComponentProvider {

    public boolean isCheckBoxDisabled();

    public boolean getValue();

    public void onChange(boolean isChecked);
    
}
