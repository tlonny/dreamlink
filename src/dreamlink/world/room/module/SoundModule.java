package dreamlink.world.room.module;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import dreamlink.audio.buffer.cache.SoundBufferCache;
import dreamlink.audio.buffer.cache.SoundBufferRef;
import dreamlink.utility.ogg.OggDecodeException;
import dreamlink.utility.ogg.OggFns;
import dreamlink.utility.ogg.OggMetaData;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class SoundModule {

    private record RoomSoundSample(String name, short[] pcmData, OggMetaData metaData) { }

    private static final Path soundsDirectory = Paths.get("pack/sound");
    private static final String soundSuffix = ".ogg";

    private final Queue<RoomSoundSample> audioSampleQueue = new ArrayDeque<>();
    private final Map<String, SoundBufferRef> soundBufferRefLookup = new HashMap<>();
    private final IRoomModuleProvider provider;

    public SoundModule(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    public SoundBufferRef getSoundBufferRefByName(String name) {
        return this.soundBufferRefLookup.get(name);
    }

    public void loadData() throws RoomLoadException{
        var roomPath = this.provider.getIOModule().getPath();
        var soundDirectoryFile = roomPath
            .resolve(SoundModule.soundsDirectory)
            .toFile();

        if(!soundDirectoryFile.exists()) {
            return;
        }

        for(var soundFile : soundDirectoryFile.listFiles(File::isFile)) {
            var fileName = soundFile.getName();
            if(!fileName.endsWith(SoundModule.soundSuffix)) {
                continue;
            }

            var metaData = new OggMetaData();
            try {
                var pcmData = OggFns.fromFile(soundFile, metaData);
                var ref = SoundBufferCache.instance.acquireSoundBufferRef(metaData.hash);
                this.audioSampleQueue.add(new RoomSoundSample(fileName, pcmData, metaData));
                this.soundBufferRefLookup.put(fileName, ref);
            } catch (OggDecodeException e) {
                throw RoomLoadException.invalidSound(fileName);
            } catch (IOException e) {
                throw RoomLoadException.invalidSound(fileName);
            }
        }
    }

    public boolean doWork() {
        if(this.audioSampleQueue.isEmpty()) {
            return false;
        }

        var sample = this.audioSampleQueue.remove();
        var ref = this.soundBufferRefLookup.get(sample.name);
        ref.setup(sample.pcmData, sample.metaData);
        return true;
    }

    public void destroy() {
        for(var soundBufferRef : this.soundBufferRefLookup.values()) {
            soundBufferRef.release();
        }
    }
    
}
