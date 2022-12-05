package dreamlink.overlay.component.scroll;

public interface IScrollBarComponentProvider {

    public float getScrollFactor();

    public float getScrollHandleHeightFactor();

    public boolean isScrollBarDisabled();

    public void onChange(float factor);
    
}
