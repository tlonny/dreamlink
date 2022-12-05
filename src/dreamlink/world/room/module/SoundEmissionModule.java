package dreamlink.world.room.module;

import dreamlink.world.room.IRoomModuleProvider;

public class SoundEmissionModule {

    private final IRoomModuleProvider provider;

    public SoundEmissionModule(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    public void emitSounds(float gainModulator) {
        var blockModule = this.provider.getBlockModule();
        var echoModule = this.provider.getEchoModule();
        var settingsModule = this.provider.getSettingsModule();
        var ambienceModule = this.provider.getAmbienceModule();

        var ambienceSound = ambienceModule.getSound();
        var baseGain = settingsModule.masterGain * gainModulator;
        if(ambienceSound != null && settingsModule.isAmbientSoundEnabled) {
            ambienceSound.soundBuffer.putGain(baseGain * ambienceModule.getGain());
        }

        for(var ix = 0; ix < blockModule.getSize(); ix += 1) {
            var block = blockModule.getBlockByIndex(ix);
            var sound = block.getSound();
            if(sound == null) {
                continue;
            }

            var gain = 1f
                * baseGain
                * echoModule.getMaxGainForBlockID(block.getBlockID())
                * block.getGain();

            sound.soundBuffer.putGain(gain);
        }
    }
    
}
