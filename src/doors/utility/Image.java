package doors.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import de.matthiasmann.twl.utils.PNGDecoder;

public class Image {

    public static void loadToBuffer(ByteBuffer buffer, String filename) {
        try {
            var stream = new FileInputStream(filename);
            var decoder = new PNGDecoder(stream);
            var width = decoder.getWidth();
            decoder.decode(buffer, width * 4, PNGDecoder.Format.RGBA);
        } catch (FileNotFoundException e) {
            var msg = String.format("Unable to find PNG: %s", filename);
            throw new RuntimeException(msg);
        } catch (IOException e) {
            var msg = String.format("Unable to load PNG: %s", filename);
            throw new RuntimeException(msg);
        }
    }

}
