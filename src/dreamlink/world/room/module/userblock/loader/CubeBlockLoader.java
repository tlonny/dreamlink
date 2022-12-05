package dreamlink.world.room.module.userblock.loader;

import java.io.File;
import java.io.IOException;

import dreamlink.graphics.texture.sample.EntityTextureSample;
import dreamlink.utility.file.FileFns;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.json.JSONDecodeException;
import dreamlink.utility.maths.CubeFace;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;
import dreamlink.world.room.module.block.BlockLight;
import dreamlink.world.room.module.userblock.CubeBlock;
import dreamlink.world.room.parser.LightAmountParser;
import dreamlink.world.room.parser.LightIsAffectedParser;
import dreamlink.world.room.parser.LightIsTranslucentParser;
import dreamlink.world.room.parser.MaterialIsSolidParser;
import dreamlink.world.room.parser.SoundParser;
import dreamlink.world.room.parser.TextureSampleParser;

public class CubeBlockLoader {

    private static final boolean defaultIsAffectedByLighting = true;
    private static final boolean defaultIsLightTransmitting = false;
    private static final boolean defaultIsTransmittingSound = false;
    private static final boolean defaultIsHidden = false;
    private static final boolean defaultIsSolid = true;
    private static final float defaultGain = 1f;
    private static final String soundGainKey = "sound.gain";
    private static final String textureSampleKeyTemplate = "texture.sample.%s";
    private static final String iconTextureSampleSuffix = "icon";
    private static final String soundKey = "sound";
    private static final String isTransmittingSoundKey = "sound.is_transmitting";
    private static final String isHiddenKey = "material.is_hidden";

    private final IRoomModuleProvider provider;
    private final MaterialIsSolidParser isSolidParser;
    private final LightIsAffectedParser isAffectedParser;
    private final LightIsTranslucentParser isTranslucentParser;
    private final SoundParser soundParser;
    private final TextureSampleParser iconTextureSampleParser;
    private final TextureSampleParser[] faceTextureSampleParsers = new TextureSampleParser[CubeFace.getSize()];
    private final LightAmountParser[] lightAmountParsers = new LightAmountParser[BlockLight.getSize()];

    public CubeBlockLoader(IRoomModuleProvider provider) {
        this.provider = provider;
        this.soundParser = new SoundParser(this.provider, CubeBlockLoader.soundKey);
        this.isSolidParser = new MaterialIsSolidParser(CubeBlockLoader.defaultIsSolid);
        this.isTranslucentParser = new LightIsTranslucentParser(CubeBlockLoader.defaultIsLightTransmitting);
        this.isAffectedParser = new LightIsAffectedParser(CubeBlockLoader.defaultIsAffectedByLighting);

        this.iconTextureSampleParser = new TextureSampleParser(
            this.provider, 
            String.format(CubeBlockLoader.textureSampleKeyTemplate, CubeBlockLoader.iconTextureSampleSuffix),
            EntityTextureSample.placeholder
        );

        for(var ix = 0; ix < BlockLight.getSize(); ix += 1) {
            var blockLight = BlockLight.get(ix);
            this.lightAmountParsers[ix] = new LightAmountParser(blockLight.name);
        }

        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);
            this.faceTextureSampleParsers[ix] = new TextureSampleParser(
                this.provider, 
                String.format(CubeBlockLoader.textureSampleKeyTemplate, cubeFace.name)
            );
        }
    }

    public CubeBlock loadCubeBlock(File file) throws RoomLoadException {
        try {
            var configData = FileFns.readStringFromFile(file);
            var blockConfig = new CheckedJSONObject(configData);
            var blockModule = this.provider.getUserBlockModule();
            var blockID = blockModule.acquireBlockID(file.getName());

            if(blockID == -1) {
                throw RoomLoadException.unableToAcquireUserBlockID();
            }

            var block = new CubeBlock(
                this.provider,
                blockID,
                file.getName(),
                this.isSolidParser.getIsSolid(blockConfig),
                this.isAffectedParser.getIsAffected(blockConfig),
                this.isTranslucentParser.getIsTranslucent(blockConfig),
                blockConfig.optBoolean(CubeBlockLoader.isTransmittingSoundKey, CubeBlockLoader.defaultIsTransmittingSound),
                blockConfig.optBoolean(CubeBlockLoader.isHiddenKey, CubeBlockLoader.defaultIsHidden),
                this.iconTextureSampleParser.getTextureSample(blockConfig),
                this.soundParser.getSound(blockConfig),
                blockConfig.optFloat(CubeBlockLoader.soundGainKey, CubeBlockLoader.defaultGain)
            );

            for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
                var cubeFace = CubeFace.get(ix);
                var parser = this.faceTextureSampleParsers[ix];
                var textureSample = parser.getTextureSample(blockConfig);
                block.setTextureSample(cubeFace, textureSample);
            }

            for(var ix = 0; ix < BlockLight.getSize(); ix += 1) {
                var blockLight = BlockLight.get(ix);
                var parser = this.lightAmountParsers[ix];
                var lightLevel = parser.getLightAmount(blockConfig);
                block.setLight(blockLight, lightLevel);
            }

            return block;
        } catch (IOException e) {
            throw RoomLoadException.invalidUserBlock(file.getName());
        } catch (JSONDecodeException e) {
            throw RoomLoadException.invalidUserBlock(file.getName());
        }
    }
    
}
