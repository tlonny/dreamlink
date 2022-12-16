package periwinkle.utility;

import java.io.IOException;
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

}
