package dreamlink.disk;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import dreamlink.utility.CommonPaths;
import dreamlink.utility.StringFns;
import dreamlink.utility.file.FileFns;

public class PackState {

    public static final PackState instance = new PackState();

    private static Pack[] defaultPacks = new Pack[] {
        new Pack("dreamlink/colors", new File("pack/colors.zip")),
        new Pack("dreamlink/mc", new File("pack/mc.zip"))
    };

    private final List<Pack> packs = new ArrayList<>();

    public int getSize() {
        return this.packs.size();
    }

    private void deployDefaultPacks() {
        var packsPath = CommonPaths.instance.localPacksPath;
        FileFns.createDirectory(packsPath.toFile());

        try {
            var classLoader = this.getClass().getClassLoader();
            for(var pack : defaultPacks) {
                    var tempFilePath = Files.createTempFile("pack", ".zip");
                    var encodedName = StringFns.urlEncode(pack.packName);
                    var destinationFile = CommonPaths.instance.localPacksPath.resolve(encodedName).toFile();
                    if(destinationFile.exists()) {
                        continue;
                    }

                    // Windows paths are backslash-separated, but we need forward slashes when using the class loader
                    var resourcePath = pack.file.toString().replace("\\", "/");
                    try(var stream = classLoader.getResourceAsStream(resourcePath)) {
                        Files.copy(stream, tempFilePath, StandardCopyOption.REPLACE_EXISTING);
                        FileFns.extractZip(tempFilePath.toFile(), destinationFile);
                    }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Pack getPack(int index) {
        return this.packs.get(index);
    }

    public void setup() {
        this.deployDefaultPacks();
        var packsPath = CommonPaths.instance.localPacksPath;
        for(var packDirectory : packsPath.toFile().listFiles()) {
            var decodedName = StringFns.urlDecode(packDirectory.getName());
            var pack = new Pack(decodedName, packDirectory);
            this.packs.add(pack);
        }
    }
    
}
