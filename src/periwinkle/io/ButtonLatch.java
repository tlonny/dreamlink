package periwinkle.io;

public class ButtonLatch {

    public long lastPressedTime;
    public long lastReleasedTime;

    public void reset() {
        var currentTime = System.currentTimeMillis();
        this.lastPressedTime = currentTime;
        this.lastReleasedTime = currentTime;
    }

}
