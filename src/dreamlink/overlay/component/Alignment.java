package dreamlink.overlay.component;

public class Alignment {

    public static final Alignment start = new Alignment(0.0f);
    public static final Alignment center = new Alignment(0.5f);
    public static final Alignment end = new Alignment(1.0f);

    private final float factor;

    public Alignment(float factor) {
        this.factor = factor;
    }

    public int getOffset(
        int parentWidth, 
        int childWidth
    ) {
        var delta = parentWidth - childWidth;
        return (int)(delta * this.factor);
    }

}
