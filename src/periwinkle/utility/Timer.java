package periwinkle.utility;

public class Timer {

    private long startTime;

    public Timer() {
        this.startTime = System.currentTimeMillis();
    }

    public void resetStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void incrementStartTime(int ms) {
        this.startTime += ms;
    }

    public long millisElapsed() {
        return System.currentTimeMillis() - this.startTime;
    }

}
