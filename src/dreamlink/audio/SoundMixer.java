package dreamlink.audio;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import dreamlink.audio.buffer.SoundBuffer;

public class SoundMixer {

    private static final int maxAudioSources = 16;

    private static final Comparator<SoundBuffer> gainComparator = Comparator.comparing(SoundBuffer::getGain);

    public static final SoundMixer instance = new SoundMixer();

    private final List<SoundSource> allSoundSources = new ArrayList<>();
    private final Queue<SoundSource> freeSoundSources = new ArrayDeque<>();
    private final List<SoundBuffer> allSoundBuffers = new ArrayList<>();
    private final Map<SoundBuffer, SoundSource> activeSoundBuffers = new HashMap<>();

    public SoundMixer() {
        for(var ix = 0; ix < SoundMixer.maxAudioSources; ix += 1) {
            var soundSource = new SoundSource();
            this.allSoundSources.add(soundSource);
            this.freeSoundSources.add(soundSource);
        }
    }

    public void removeSoundBuffer(SoundBuffer soundBuffer) {
        this.allSoundBuffers.remove(soundBuffer);
        if(this.activeSoundBuffers.containsKey(soundBuffer)) {
            var soundSource = this.activeSoundBuffers.get(soundBuffer);
            soundSource.stop();
            this.activeSoundBuffers.remove(soundBuffer);
            this.freeSoundSources.add(soundSource);
        }
    }

    public void setup() {
        for(var soundSource : this.allSoundSources) {
            soundSource.setup();
        }
    }

    public void addSoundBuffer(SoundBuffer soundBuffer) { 
        this.allSoundBuffers.add(soundBuffer);
    }

    public void clear() {
        for(var ix = 0; ix < this.allSoundBuffers.size(); ix += 1) {
            this.allSoundBuffers.get(ix).zeroGain();
        }
    }

    public void update() {
        this.allSoundBuffers.sort(SoundMixer.gainComparator);

        var count = 0;
        for(var ix = 0; ix < this.allSoundBuffers.size(); ix += 1) {
            var soundBuffer = this.allSoundBuffers.get(ix);
            var gain = soundBuffer.getGain();
            soundBuffer.zeroGain();

            var isActiveSoundBuffer = count < SoundMixer.maxAudioSources;
            count += 1;

            // If the sound is not active, yet is still in possession of a sound source, 
            // we should release that sound source so an active sound can use it.
            // N.B. As the sounds are sorted by gain, we will do all the freeing before
            // we need to start claiming sound sources, thus we should always have sound
            // sources available for the active sounds.
            if(!isActiveSoundBuffer) {
                if(this.activeSoundBuffers.containsKey(soundBuffer)) {
                    var soundSource = this.activeSoundBuffers.get(soundBuffer);
                    soundSource.stop();
                    this.activeSoundBuffers.remove(soundBuffer);
                    this.freeSoundSources.add(soundSource);
                }
                continue;
            }

            // If we have an active sound that hasn't yet possessed a sound source,
            // then we need to grab a free source and set it to start playing.
            if (!this.activeSoundBuffers.containsKey(soundBuffer)) {
                var source = this.freeSoundSources.remove();
                source.play(soundBuffer);
                this.activeSoundBuffers.put(soundBuffer, source);
            }

            var source = this.activeSoundBuffers.get(soundBuffer);
            source.setGain(gain);
        }
    }

    public void destroy() {
        for(var soundSource : this.allSoundSources) {
            soundSource.destroy();
        }
    }

    
}
