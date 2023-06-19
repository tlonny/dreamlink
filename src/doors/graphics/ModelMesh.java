package doors.graphics;

import org.joml.Vector2i;
import org.joml.Vector3f;
import org.json.JSONObject;

import doors.utility.CubeFace;
import doors.utility.FileIO;
import doors.utility.Maths;

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

        var positionBuffer = new Vector3f[4];
        for(var ix = 0; ix < positionBuffer.length; ix += 1) {
            positionBuffer[ix] = new Vector3f();
        }

        var modelString = FileIO.loadText(path);
        var modelConfig = new JSONObject(modelString);

        this.cullFaces = modelConfig.getBoolean("cullFaces");

        var originArray = modelConfig.getJSONArray("origin");
        var origin = new Vector3f(
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
            var textureDimensions = new Vector2i(textureDimensionsArray.getInt(0), textureDimensionsArray.getInt(1));
            var sampler = new TextureSampler(textureChannel, textureDimensions);

            var dimensionsArray = fragment.getJSONArray("dimensions");
            var dimensions = new Vector3f(
                dimensionsArray.getFloat(0),
                dimensionsArray.getFloat(1),
                dimensionsArray.getFloat(2)
            );

            var positionArray = fragment.getJSONArray("position");
            var position = new Vector3f(
                positionArray.getFloat(0),
                positionArray.getFloat(1),
                positionArray.getFloat(2)
            );

            var textureSamplesObj = textureObj.getJSONObject("samples");

            for(var cubeFace : CubeFace.CUBE_FACES) {
                var textureSampleOffsets = textureSamplesObj.getJSONArray(cubeFace.name);
                for(var ix = 0; ix < cubeFace.vertices.length; ix += 1) {
                    positionBuffer[ix].set(cubeFace.vertices[ix]).mul(dimensions).add(position).sub(origin);
                }
                var textureSample = sampler.createTextureSample(
                    new Vector2i(textureSampleOffsets.getInt(0), textureSampleOffsets.getInt(1)),
                    new Vector2i(textureSampleOffsets.getInt(2), textureSampleOffsets.getInt(3))
                );
                MESH_BUFFER.pushQuad(positionBuffer, cubeFace, textureSample, Maths.VEC3F_ONE);
            }
        }

        MESH_BUFFER.flip();
        this.loadFromMeshBuffer(MESH_BUFFER);
    }
}
