package doors.utility.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import de.matthiasmann.twl.utils.PNGDecoder;
import doors.utility.vector.Vector2in;
import doors.work.WorkUnit;

public class Image {

    public String path;
    private boolean decodeFlipped;

    private byte[] pixelData;
    private Vector2in dimensions;

    public WorkUnit setupWorkUnit;

    public Image(String path, boolean decodeFlipped) {
        this.path = path;
        this.decodeFlipped = decodeFlipped;

        this.setupWorkUnit = new WorkUnit(this::loadImageData, true);
        this.setupWorkUnit.submit();
    }

    public Vector2in getDimensions() {
        if(!this.setupWorkUnit.isDone()) {
            return null;
        }

        return this.dimensions;
    }

    public byte[] getPixelData() {
        if(!this.setupWorkUnit.isDone()) {
            return null;
        }

        return this.pixelData;
    }

    private void loadImageData() { 
        try (var stream = new FileInputStream(this.path)) {
            var decoder = new PNGDecoder(stream);
            var width = decoder.getWidth();
            var height = decoder.getHeight();
            this.dimensions = new Vector2in(width, height);
            this.pixelData = new byte[this.dimensions.getIntArea() * 4];
            var buffer = ByteBuffer.wrap(pixelData);
            if(decodeFlipped) {
                decoder.decodeFlipped(buffer, width * 4, PNGDecoder.Format.RGBA);
            } else {
                decoder.decode(buffer, width * 4, PNGDecoder.Format.RGBA);
            }
        } catch (IOException e) {
            var msg = String.format("Unable to load PNG: %s", path);
            throw new RuntimeException(msg);
        }
    }

    
    
}
