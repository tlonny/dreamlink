package dreamlink.overlay.component.slider;

public interface ISliderComponentProvider {

    public float getSlideFactor();

    public void onChange(float slideFactor);

    public boolean isSliderDisabled();
    
}
