package dreamlink.world.room.module.texturesample;

import java.io.File;
import java.io.IOException;

import org.joml.Vector2i;

import dreamlink.graphics.texture.TextureUnit;
import dreamlink.graphics.texture.sample.TextureSample;
import dreamlink.utility.file.FileFns;
import dreamlink.utility.json.JSONAccessException;
import dreamlink.utility.json.JSONDecodeException;
import dreamlink.utility.json.CheckedJSONObject;
import dreamlink.utility.maths.Vector2iMaths;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class TextureSampleLoader {

    private static final String positionKey = "lookup.position";
    private static final String dimensionsKey = "lookup.dimensions";
    private static final String animationStrideKey = "animation.stride";
    private static final String animationFramesTotalKey = "animation.total";
    private static final String animationFramesRowKey = "animation.row";
    private static final String animationFramesStartKey = "animation.start";
    private static final String animationSpeedKey = "animation.speed";

    private static final int defaultAnimationStart = 0;
    private static final int defaultAnimationSize = 1;
    private static final int defaultAnimationSpeed = 0;

    private final IRoomModuleProvider provider;

    public TextureSampleLoader(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    public TextureSample loadFromFile(File file) throws RoomLoadException{
        try {
            var JSONData = FileFns.readStringFromFile(file);
            var config = new CheckedJSONObject(JSONData);

            var positionJSON = config.getJSONArray(TextureSampleLoader.positionKey);
            var position = positionJSON.getVector2i(new Vector2i());

            var dimensionsJSON = config.getJSONArray(TextureSampleLoader.dimensionsKey);
            var dimensions = dimensionsJSON.getVector2i(new Vector2i());

            var animationStrideJSON = config.optJSONArray(TextureSampleLoader.animationStrideKey);
            var animationStride = animationStrideJSON != null 
                ? animationStrideJSON.getVector2i(new Vector2i()) 
                : Vector2iMaths.zero;

            var animationFramesTotal = config.optInt(TextureSampleLoader.animationFramesTotalKey, TextureSampleLoader.defaultAnimationSize);

            var animationFramesRow = config.optInt(TextureSampleLoader.animationFramesRowKey, TextureSampleLoader.defaultAnimationSize);

            var animationFramesStart = config.optInt(TextureSampleLoader.animationFramesStartKey, TextureSampleLoader.defaultAnimationStart);

            var animationSpeed = config.optInt(TextureSampleLoader.animationSpeedKey, TextureSampleLoader.defaultAnimationSpeed);

            if(file.getName().contains("magma")) {
                System.out.println("Magma: " + file.getName());
                System.out.println(animationFramesRow);
                System.out.println(animationFramesTotal);
                System.out.println(animationFramesStart);
                System.out.println(animationSpeed);
            }

            return new TextureSample(
                TextureUnit.room,
                provider.getTextureAtlasModule().getDimensions(new Vector2i()),
                position,
                dimensions,
                animationStride,
                animationFramesTotal,
                animationFramesRow,
                animationFramesStart,
                animationSpeed
            );

        } catch (JSONDecodeException e) {
            throw RoomLoadException.invalidTextureSample(file.getName());
        } catch (JSONAccessException e) {
            throw RoomLoadException.invalidTextureSample(file.getName());
        } catch (IOException e) {
            throw RoomLoadException.invalidTextureSample(file.getName());
        }
    }
    
}
