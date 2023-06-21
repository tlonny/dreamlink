package doors.graphics.mesh;

import org.json.JSONObject;

import doors.graphics.texture.TextureChannel;
import doors.graphics.texture.TextureSampler;
import doors.utility.CubeFace;
import doors.utility.FileIO;
import doors.utility.geometry.Vector2in;
import doors.utility.geometry.Vector3fl;

public class ModelMesh extends Mesh {

    private static int MAX_QUADS = 1_000;
    private static MeshBuffer MESH_BUFFER = new MeshBuffer(MAX_QUADS);

    public static ModelMesh UNIT = new ModelMesh("data/model/unit.json");
    public static ModelMesh DOOR = new ModelMesh("data/model/door.json");
    public static ModelMesh PORTAL = new ModelMesh("data/model/portal.json");
    public static ModelMesh ARROW = new ModelMesh("data/model/arrow.json");

    private String path;

    public ModelMesh(String path) {
        super();
        this.path = path;
    }

    public void setup() {
        super.setup();

        var modelString = FileIO.loadText(path);
        var modelConfig = new JSONObject(modelString);

        this.cullFaces = modelConfig.getBoolean("cullFaces");

        var originArray = modelConfig.getJSONArray("origin");
        var origin = new Vector3fl(
            originArray.getFloat(0),
            originArray.getFloat(1),
            originArray.getFloat(2)
        );

        MESH_BUFFER.clear();

        var fragments = modelConfig.getJSONArray("fragments");
        for(var fragIX = 0; fragIX < fragments.length(); fragIX += 1) {
            var fragment = fragments.getJSONObject(fragIX);

            var textureObj = fragment.getJSONObject("texture");

            var textureChannelName = textureObj.getString("channel");
            var textureChannel = TextureChannel.TEXTURE_CHANNEL_LOOKUP.get(textureChannelName);

            var textureDimensionsArray = textureObj.getJSONArray("dimensions");
            var textureDimensions = new Vector2in(
                textureDimensionsArray.getInt(0), 
                textureDimensionsArray.getInt(1)
            );

            var sampler = new TextureSampler(textureDimensions);

            var positionArray = fragment.getJSONArray("position");
            var position = new Vector3fl(
                positionArray.getFloat(0),
                positionArray.getFloat(1),
                positionArray.getFloat(2)
            ).sub(origin);

            var dimensionsArray = fragment.getJSONArray("dimensions");
            var dimensions = new Vector3fl(
                dimensionsArray.getFloat(0),
                dimensionsArray.getFloat(1),
                dimensionsArray.getFloat(2)
            );

            var textureSamplesObj = textureObj.getJSONObject("samples");
            for(var cubeFace : CubeFace.CUBE_FACES) {
                var textureSampleOffsets = textureSamplesObj.getJSONArray(cubeFace.name);
                var textureSample = sampler.createTextureSample(
                    new Vector2in(textureSampleOffsets.getInt(0), textureSampleOffsets.getInt(1)),
                    new Vector2in(textureSampleOffsets.getInt(2), textureSampleOffsets.getInt(3))
                );
                MESH_BUFFER.writeQuad(textureSample, textureChannel, position, dimensions, cubeFace, Vector3fl.ONE);
            }
        }

        MESH_BUFFER.flip();
        this.loadFromMeshBuffer(MESH_BUFFER);
    }
}
