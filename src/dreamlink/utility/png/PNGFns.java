package dreamlink.utility.png;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.joml.Vector2i;

import de.matthiasmann.twl.utils.PNGDecoder;

public class PNGFns {

    private static final int chunkBufferSize = 1024 * 1024;

    public static byte[] fromStream(InputStream stream, Vector2i dimensions) throws PNGDecodeException, IOException {
        try(
            var bufferedStream = new BufferedInputStream(stream, PNGFns.chunkBufferSize);
        ) {
            var decoder = new PNGDecoder(bufferedStream);
            dimensions.set(decoder.getWidth(), decoder.getHeight());
            var textureData = new byte[dimensions.x * dimensions.y * 4];
            var buffer = ByteBuffer.wrap(textureData);
            decoder.decodeFlipped(buffer, dimensions.x * 4, PNGDecoder.Format.RGBA);
            return textureData;
        } catch (UnsupportedOperationException e) {
            throw new PNGDecodeException();
        }
    }

    public static byte[] fromFile(File file, Vector2i dimensions) throws PNGDecodeException, IOException {
        try(
            var rawStream = new FileInputStream(file);
        ) {
            return PNGFns.fromStream(rawStream, dimensions);
        }
    }
    
}
