package doors.overlay;

import java.util.HashMap;
import java.util.Map;

import doors.overlay.command.ICommandBehaviour;
import doors.overlay.command.SetBlockType;
import doors.overlay.command.ToggleNoClip;
import doors.overlay.command.TogglePerformanceTracker;

public class Command {

    public static Map<String, Command> COMMAND_LOOKUP = new HashMap<>();

    public static Command SET_BLOCK_TYPE = new Command("set_block_type", new SetBlockType());
    public static Command TOGGLE_PERFORMANCE_TRACKER = new Command("toggle_perf", new TogglePerformanceTracker());
    public static Command TOGGLE_NO_CLIP = new Command("toggle_noclip", new ToggleNoClip());

    public final String program;
    public ICommandBehaviour behaviour;

    public Command(String program, ICommandBehaviour behaviour) {
        this.program = program;
        this.behaviour = behaviour;
        COMMAND_LOOKUP.put(this.program, this);
    }

}
