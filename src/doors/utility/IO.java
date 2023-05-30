package doors.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

import de.matthiasmann.twl.utils.PNGDecoder;

public class IO {

    public static String loadText(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadImageToBuffer(Path path, ByteBuffer buffer) {
        try {
            var stream = new FileInputStream(path.toString());
            var decoder = new PNGDecoder(stream);
            var width = decoder.getWidth();
            decoder.decode(buffer, width * 4, PNGDecoder.Format.RGBA);
        } catch (FileNotFoundException e) {
            var msg = String.format("Unable to find PNG: %s", path);
            throw new RuntimeException(msg);
        } catch (IOException e) {
            var msg = String.format("Unable to load PNG: %s", path);
            throw new RuntimeException(msg);
        }
    }
}
