package dreamlink.utility.ogg;

import java.math.BigInteger;

public class OggMetaData {

    public int channels;
    public int sampleRate;
    public BigInteger hash;

    public OggMetaData set(
        int channels,
        int sampleRate,
        BigInteger hash
    ) {
        this.channels = channels;
        this.sampleRate = sampleRate;
        this.hash = hash;
        return this;
    }
    
}
