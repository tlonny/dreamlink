package periwinkle.overlay.command_behaviour;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import periwinkle.Game;

public class SaveWorld implements ICommandBehaviour {

    public void run(String[] args) {
        try {
            var fileStream = new FileOutputStream(args[1]);
            var dataStream = new DataOutputStream(fileStream);
            Game.WORLD.writeToStream(dataStream);
            dataStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
