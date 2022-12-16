package periwinkle.ui;

import java.util.HashMap;
import java.util.Map;

public class Command {

    public static Command SET_SKY_TYPE = new Command("!set_sky_type", new SetSkyType());
    public static Command SET_BLOCK_TYPE = new Command("!set_block_type", new SetBlockType());
    public static Map<String, Command> COMMAND_LOOKUP = new HashMap<>();

    public static Command[] COMMANDS = new Command[] {
        SET_SKY_TYPE,
        SET_BLOCK_TYPE,
    };

    public static void init() {
        for(var command : COMMANDS) {
            COMMAND_LOOKUP.put(command.program, command);
        }
    }

    public final String program;
    public ICommandBehaviour behaviour;

    public Command(String program, ICommandBehaviour behaviour) {
        this.program = program;
        this.behaviour = behaviour;
    }

}
