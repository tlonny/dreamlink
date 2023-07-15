package doors.core.graphics.mesh;

public enum TextureSampleMode {

    NORMAL(0),
    SCREEN(1);

    private TextureSampleMode(int value) {
        this.value = value;
    }

    public int value;
}
