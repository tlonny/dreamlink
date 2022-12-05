package dreamlink.overlay.component.box;

import dreamlink.overlay.component.Alignment;

public class BoxDimension {

    public static final BoxDimension fixed(int value, Alignment alignment) {
        return new BoxDimension(value, value, alignment);
    }

    public static final BoxDimension fixed(int value) {
        return BoxDimension.fixed(value, Alignment.center);
    }

    public static final BoxDimension max(int value, Alignment alignment) {
        return new BoxDimension(0, value, alignment);
    }

    public static final BoxDimension max(int value) {
        return BoxDimension.max(value, Alignment.center);
    }

    public static final BoxDimension min(int value, Alignment alignment) {
        return new BoxDimension(value, Integer.MAX_VALUE, alignment);
    }

    public static final BoxDimension min(int value) {
        return BoxDimension.min(value, Alignment.center);
    }

    public static final BoxDimension grow(Alignment alignment) {
        return BoxDimension.min(0, alignment);
    }

    public static final BoxDimension grow() {
        return BoxDimension.grow(Alignment.center);
    }

    public static final BoxDimension wrap() {
        return BoxDimension.max(0, Alignment.start);
    }

    public final int minValue;
    public final int maxValue;
    public final Alignment alignment;

    public BoxDimension(
        int minValue, 
        int maxValue, 
        Alignment alignment
    ) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.alignment = alignment;
    }

    
}
