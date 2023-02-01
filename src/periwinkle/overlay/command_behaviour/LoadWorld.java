package periwinkle.overlay.command_behaviour;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import periwinkle.Game;

public class LoadWorld implements ICommandBehaviour {

    public void run(String[] args) {
        try {
            var fileStream = new FileInputStream(args[1]);
            var dataStream = new DataInputStream(fileStream);
            Game.WORLD.disableUpdates();
            // TODO: this is the slow bit! It shouldn't be slow...
            Game.WORLD.readFromStream(dataStream);
            Game.WORLD.enableUpdates();
            dataStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
