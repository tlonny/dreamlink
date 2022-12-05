package periwinkle.utility;

import de.matthiasmann.twl.utils.PNGDecoder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class File {

    public static File FILE = new File();

    public String readStringFromFile(String strPath) {
        var path = Paths.get(strPath);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ByteBuffer loadPNGFromFile(String strPath, ByteBuffer dataBuffer) {
        try {
            var stream = new FileInputStream(strPath);
            var decoder = new PNGDecoder(stream);
            decoder.decode(dataBuffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            return dataBuffer;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("Unable to find PNG: %s", strPath));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to load PNG: %s", strPath));
        }
    }

}
