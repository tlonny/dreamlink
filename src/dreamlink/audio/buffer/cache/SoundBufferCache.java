package dreamlink.audio.buffer.cache;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class SoundBufferCache {
    
    public static final SoundBufferCache instance = new SoundBufferCache();

    private final Map<BigInteger, SoundBufferRef> soundBufferLookup = new HashMap<>();

    public void remove(BigInteger hash) {
        this.soundBufferLookup.remove(hash);
    }

    public SoundBufferRef acquireSoundBufferRef(BigInteger hash) {
        if(!this.soundBufferLookup.containsKey(hash)) {
            this.soundBufferLookup.put(hash, new SoundBufferRef(this, hash));
        }
        return this.soundBufferLookup.get(hash).acquire();
    }

    public void destroy() {
        for(var soundBufferRef : this.soundBufferLookup.values()) {
            soundBufferRef.soundBuffer.destroy();
        }
        soundBufferLookup.clear();
    }
    
}
