package periwinkle.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import de.matthiasmann.twl.utils.PNGDecoder;

public class ImageData {

    private final String imageSrc;
    public ByteBuffer buffer;
    public int width;
    public int height;

    public ImageData(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public void load() {
        try {
            var stream = new FileInputStream(this.imageSrc);
            var decoder = new PNGDecoder(stream);
            this.width = decoder.getWidth();
            this.height = decoder.getHeight();
            this.buffer = ByteBuffer.allocateDirect(this.width * this.height * 4);
            decoder.decode(buffer, this.width * 4, PNGDecoder.Format.RGBA);
            this.buffer.flip();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("Unable to find PNG: %s", this.imageSrc));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to load PNG: %s", this.imageSrc));
        }
    }

}
