package dreamlink.audio.buffer.cache;

import java.math.BigInteger;

import dreamlink.audio.buffer.SoundBuffer;
import dreamlink.utility.ogg.OggMetaData;

public class SoundBufferRef {

    private final BigInteger hash;
    private final SoundBufferCache cache;

    public final SoundBuffer soundBuffer = new SoundBuffer();

    private int refCount;
    private boolean isSetup;

    public SoundBufferRef(
        SoundBufferCache cache,
        BigInteger hash
    ) {
        this.cache = cache;
        this.hash = hash;
    }

    public void setup(short[] pcmData, OggMetaData metaData) {
        if(this.isSetup) {
            return;
        }

        this.soundBuffer.setup();
        this.soundBuffer.buffer(pcmData, metaData);
        this.isSetup = true;
    }

    public void release() {
        this.refCount -= 1;

        if(this.refCount <= 0) {
            if(this.isSetup) {
                this.soundBuffer.destroy();
            }
            this.cache.remove(SoundBufferRef.this.hash);
        }
    }

    public SoundBufferRef acquire() {
        this.refCount += 1;
        return this;
    }
    
}
