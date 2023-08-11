package doors.utility;

import java.io.FileInputStream;
import java.io.IOException;

public class FileIO {

    public static String loadText(String path) {
        try(var stream = new FileInputStream(path)) {
            return new String(stream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
