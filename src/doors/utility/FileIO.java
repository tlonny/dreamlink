package doors.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import de.matthiasmann.twl.utils.PNGDecoder;
import doors.utility.geometry.Vector2in;

public class FileIO {

    public static String loadText(String path) {
        try(var stream = new FileInputStream(path)) {
            return new String(stream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Vector2in writeImageToBuffer(String path, ByteBuffer buffer) {
        try (var stream = new FileInputStream(path)) {
            var decoder = new PNGDecoder(stream);
            var width = decoder.getWidth();
            var height = decoder.getHeight();
            decoder.decodeFlipped(buffer, width * 4, PNGDecoder.Format.RGBA);
            return new Vector2in(width, height);
        } catch (IOException e) {
            var msg = String.format("Unable to load PNG: %s", path);
            throw new RuntimeException(msg);
        }
    }
}
