package periwinkle.overlay;

import periwinkle.Game;

public class TogglePerformanceTracker implements ICommandBehaviour {

    public void run(String[] args) {
        Game.PERFORMANCE_TRACKER.active = !Game.PERFORMANCE_TRACKER.active;
    }
}
