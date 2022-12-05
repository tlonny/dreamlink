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
import dreamlink.world.room.module.userblock.HalfSlabBlock;
import dreamlink.world.room.parser.LightIsAffectedParser;
import dreamlink.world.room.parser.MaterialIsSolidParser;
import dreamlink.world.room.parser.TextureSampleParser;

public class HalfSlabBlockLoader {

    private static final boolean defaultIsAffectedByLighting = true;
    private static final boolean defaultIsSolid = true;
    private static final float defaultGain = 1f;
    private static final String soundGainKey = "sound.gain";
    private static final String textureSampleKeyTemplate = "texture.sample.%s";
    private static final String iconTextureSampleSuffix = "icon";

    private final IRoomModuleProvider provider;
    private final MaterialIsSolidParser isSolidParser;
    private final LightIsAffectedParser isAffectedParser;
    private final TextureSampleParser iconTextureSampleParser;
    private final TextureSampleParser[] faceTextureSampleParsers = new TextureSampleParser[CubeFace.getSize()];

    public HalfSlabBlockLoader(IRoomModuleProvider provider) {
        this.provider = provider;
        this.isSolidParser = new MaterialIsSolidParser(HalfSlabBlockLoader.defaultIsSolid);
        this.isAffectedParser = new LightIsAffectedParser(HalfSlabBlockLoader.defaultIsAffectedByLighting);

        this.iconTextureSampleParser = new TextureSampleParser(
            this.provider, 
            String.format(HalfSlabBlockLoader.textureSampleKeyTemplate, HalfSlabBlockLoader.iconTextureSampleSuffix),
            EntityTextureSample.placeholder
        );

        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);
            this.faceTextureSampleParsers[ix] = new TextureSampleParser(
                this.provider, 
                String.format(HalfSlabBlockLoader.textureSampleKeyTemplate, cubeFace.name)
            );
        }
    }

    public HalfSlabBlock loadHalfSlabBlock(File file) throws RoomLoadException {
        try {
            var data = FileFns.readStringFromFile(file);
            var blockConfig = new CheckedJSONObject(data);
            var blockModule = this.provider.getUserBlockModule();
            var blockID = blockModule.acquireBlockID(file.getName());

            if(blockID == -1) {
                throw RoomLoadException.unableToAcquireUserBlockID();
            }

            var block = new HalfSlabBlock(
                this.provider,
                blockID,
                file.getName(),
                this.isSolidParser.getIsSolid(blockConfig),
                this.isAffectedParser.getIsAffected(blockConfig),
                this.iconTextureSampleParser.getTextureSample(blockConfig),
                blockConfig.optFloat(HalfSlabBlockLoader.soundGainKey, HalfSlabBlockLoader.defaultGain)
            );

            for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
                var cubeFace = CubeFace.get(ix);
                var parser = this.faceTextureSampleParsers[ix];
                var textureSample = parser.getTextureSample(blockConfig);
                block.setTextureSample(cubeFace, textureSample);
            }

            return block;
        } catch (IOException e) {
            throw RoomLoadException.invalidUserBlock(file.getName());
        } catch (JSONDecodeException e) {
            throw RoomLoadException.invalidUserBlock(file.getName());
        }
    }
    
}
