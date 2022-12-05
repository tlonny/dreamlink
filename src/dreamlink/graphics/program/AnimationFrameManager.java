package dreamlink.graphics.program;

public class AnimationFrameManager {

    private static final int animationFramePeriodMs = 10;

    public static final AnimationFrameManager instance = new AnimationFrameManager();

    public int getAnimationFrame() {
        return (int)((System.currentTimeMillis() / AnimationFrameManager.animationFramePeriodMs) % Integer.MAX_VALUE);
    }
    
}
