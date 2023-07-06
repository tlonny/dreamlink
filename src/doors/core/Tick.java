package doors.core;

public class Tick {

    public static Tick TICK = new Tick();

    private long currentTick;
    private long currentTime;
    private long previousTime;

    public long getCurrentTick() {
        return this.currentTick;
    }

    public long getCurrentTime() {
        return this.currentTime;
    }

    public long getPreviousTime() {
        return this.previousTime;
    }

    public void update() {
        this.currentTick += 1;
        this.previousTime = this.currentTime;
        this.currentTime = System.currentTimeMillis();
    }

}
