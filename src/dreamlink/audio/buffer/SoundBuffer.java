package dreamlink.audio.buffer;

import java.nio.ShortBuffer;

import org.lwjgl.openal.AL10;
import org.lwjgl.system.MemoryUtil;

import dreamlink.audio.SoundMixer;
import dreamlink.utility.ogg.OggMetaData;

public class SoundBuffer {

    private static int maxBufferSize = 1024 * 1024 * 64;
    private static ShortBuffer inputBuffer = MemoryUtil.memAllocShort(maxBufferSize);

    private int soundBufferID;
    private float gain;

    public int getSoundBufferID() {
        return this.soundBufferID;
    }

    public void putGain(float gain) {
        this.gain = Math.max(this.gain, gain);
    }

    public float getGain() {
        return this.gain;
    }

    public void zeroGain() {
        this.gain = 0f;
    }

    public void setup() {
        this.soundBufferID = AL10.alGenBuffers();
        SoundMixer.instance.addSoundBuffer(this);
    }

    public void buffer(short[] pcmData, OggMetaData metaData) {
        SoundBuffer.inputBuffer.clear();
        SoundBuffer.inputBuffer.put(pcmData);
        SoundBuffer.inputBuffer.flip();

        AL10.alBufferData(
            this.soundBufferID, 
            metaData.channels == 1 
                ? AL10.AL_FORMAT_MONO16 
                : AL10.AL_FORMAT_STEREO16, 
            SoundBuffer.inputBuffer,
            metaData.sampleRate
        );
    }

    public void destroy() {
        SoundMixer.instance.removeSoundBuffer(this);
        AL10.alDeleteBuffers(this.soundBufferID);
    }
    
}
