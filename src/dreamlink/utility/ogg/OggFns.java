package dreamlink.utility.ogg;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.stb.STBVorbis;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import dreamlink.utility.HashFns;
import dreamlink.utility.file.FileFns;

public class OggFns {

    private static int maxBufferSize = 1024 * 1024 * 64;
    private static ByteBuffer inputBuffer = MemoryUtil.memAlloc(maxBufferSize);

    public static short[] fromFile(File file, OggMetaData metaData) throws OggDecodeException, IOException {
        var rawBytes = FileFns.readBytesFromFile(file);
        try(var stack = MemoryStack.stackPush()) {
            var channelsBuffer = stack.mallocInt(1);
            var sampleRateBuffer = stack.mallocInt(1);
            ShortBuffer pcmBuffer = null;
            try {
                synchronized(OggFns.inputBuffer) {
                    OggFns.inputBuffer.clear();
                    OggFns.inputBuffer.put(rawBytes);
                    OggFns.inputBuffer.flip();

                    pcmBuffer = STBVorbis.stb_vorbis_decode_memory(
                        OggFns.inputBuffer,
                        channelsBuffer,
                        sampleRateBuffer
                    );
                }

                if(pcmBuffer == null) {
                    throw new OggDecodeException();
                }

                var pcmData = new short[pcmBuffer.remaining()];
                pcmBuffer.get(pcmData);
                pcmBuffer.flip();
                var channels = channelsBuffer.get();
                var sampleRate = sampleRateBuffer.get();
                var hash = HashFns.generateHash(rawBytes);
                metaData.set(channels, sampleRate, hash);
                return pcmData;
            } finally {
                if(pcmBuffer != null) {
                    MemoryUtil.memFree(pcmBuffer);
                }
            }
        }

    }
    
}
