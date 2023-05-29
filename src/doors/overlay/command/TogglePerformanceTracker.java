package doors.overlay.command_behaviour;

import doors.Game;

public class TogglePerformanceTracker implements ICommandBehaviour {

    public void run(String[] args) {
        Game.PERFORMANCE_TRACKER.active = !Game.PERFORMANCE_TRACKER.active;
    }
}
