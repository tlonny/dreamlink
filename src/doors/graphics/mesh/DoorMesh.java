package doors.graphics.mesh;

import doors.graphics.template.CubeTemplate;
import doors.graphics.texture.EntityTexture;
import doors.utility.vector.Vector3fl;

public class DoorMesh extends Mesh {

    // TODO: scaling for the top/bottom faces is a bit wrong. Need to tweak texture atlas...
    private static Vector3fl DOOR_FACE_DIMENSIONS = new Vector3fl(1f, 2f, 0.25f);
    private static float DOOR_OPEN_ROTATION = (float)Math.PI * 0.45f;

    private static CubeTemplate RIGHT_DOOR_SCHEMA = new CubeTemplate(
        new Vector3fl(0f, 0f, 0f),
        new Vector3fl(1f, 0f, 0f),
        DOOR_FACE_DIMENSIONS,
        EntityTexture.ENTITY_TEXTURE.doorRightFace,
        EntityTexture.ENTITY_TEXTURE.doorLeftFace,
        EntityTexture.ENTITY_TEXTURE.doorEnd,
        EntityTexture.ENTITY_TEXTURE.doorEnd,
        EntityTexture.ENTITY_TEXTURE.doorSide,
        EntityTexture.ENTITY_TEXTURE.doorSide,
        1
    );

    private static CubeTemplate LEFT_DOOR_SCHEMA = new CubeTemplate(
        new Vector3fl(-1f, 0f, 0f),
        new Vector3fl(-1f, 0f, 0f),
        DOOR_FACE_DIMENSIONS,
        EntityTexture.ENTITY_TEXTURE.doorLeftFace,
        EntityTexture.ENTITY_TEXTURE.doorRightFace,
        EntityTexture.ENTITY_TEXTURE.doorEnd,
        EntityTexture.ENTITY_TEXTURE.doorEnd,
        EntityTexture.ENTITY_TEXTURE.doorSide,
        EntityTexture.ENTITY_TEXTURE.doorSide,
        2
    );

    private static CubeTemplate LOCKED_SCHEMA = new CubeTemplate(
        new Vector3fl(-1f, 0f, 0f),
        new Vector3fl(0f, 0f, 0f),
        new Vector3fl(2f, 2f, 0.25f),
        EntityTexture.ENTITY_TEXTURE.doorLockedDoubleFace,
        EntityTexture.ENTITY_TEXTURE.doorLockedDoubleFace,
        EntityTexture.ENTITY_TEXTURE.doorEnd,
        EntityTexture.ENTITY_TEXTURE.doorEnd,
        EntityTexture.ENTITY_TEXTURE.doorSide,
        EntityTexture.ENTITY_TEXTURE.doorSide,
        3
    );

    private static CubeTemplate[] CUBE_SCHEMAS = new CubeTemplate[] {
        LEFT_DOOR_SCHEMA,
        RIGHT_DOOR_SCHEMA,
        LOCKED_SCHEMA
    };

    public static DoorMesh DOOR_MESH = new DoorMesh();

    private Vector3fl rotationBuffer = new Vector3fl();

    @Override
    public void setup() {
        super.setup();
        MeshBuffer.DEFAULT_MESH_BUFFER.clear();
        for(var cubeSchema : CUBE_SCHEMAS) {
            cubeSchema.writeCube(MeshBuffer.DEFAULT_MESH_BUFFER);
        }
        this.loadDataFromMeshBuffer(MeshBuffer.DEFAULT_MESH_BUFFER);
    }

    public void renderLocked(Vector3fl position, Vector3fl rotation) {
        LOCKED_SCHEMA.transform(Vector3fl.ZERO, Vector3fl.ZERO, Vector3fl.ONE);
        LEFT_DOOR_SCHEMA.transform(Vector3fl.ZERO, Vector3fl.ZERO, Vector3fl.ZERO);
        RIGHT_DOOR_SCHEMA.transform(Vector3fl.ZERO, Vector3fl.ZERO, Vector3fl.ZERO);
        super.render(position, rotation);
    }

    public void render(Vector3fl position, Vector3fl rotation, float openFactor) {
        var modulated = 1f / (1f + (float)Math.exp((0.5f - openFactor) * 10f));
        if(modulated < 0.01f) {
            modulated = 0f;
        }

        var doorRotation = modulated * DOOR_OPEN_ROTATION;

        LOCKED_SCHEMA.transform(Vector3fl.ZERO, Vector3fl.ZERO, Vector3fl.ZERO);
        this.rotationBuffer.set(0f, doorRotation, 0f);
        RIGHT_DOOR_SCHEMA.transform(Vector3fl.ZERO, this.rotationBuffer, Vector3fl.ONE);
        this.rotationBuffer.mul(-1f);
        LEFT_DOOR_SCHEMA.transform(Vector3fl.ZERO, this.rotationBuffer, Vector3fl.ONE);
        super.render(position, rotation);
    }

}
