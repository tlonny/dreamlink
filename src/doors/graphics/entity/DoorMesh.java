package doors.graphics.entity;

import doors.core.graphics.cube.CubeMesh;
import doors.core.graphics.cube.CubeSchema;
import doors.core.utility.vector.Vector3fl;

public class DoorMesh extends CubeMesh {

    private static Vector3fl DOOR_FACE_DIMENSIONS = new Vector3fl(1f, 2f, 0.25f);

    private static float DOOR_OPEN_ROTATION = (float)Math.PI * 0.6f;

    private static CubeSchema RIGHT_DOOR_SCHEMA = new CubeSchema(
        new Vector3fl(0f, 0f, 0f),
        new Vector3fl(1f, 0f, 0f),
        DOOR_FACE_DIMENSIONS,
        EntityTextureAtlas.DOOR_HALF_FACE_RIGHT,
        EntityTextureAtlas.DOOR_HALF_FACE_LEFT,
        EntityTextureAtlas.DOOR_END,
        EntityTextureAtlas.DOOR_END,
        EntityTextureAtlas.DOOR_SIDE,
        EntityTextureAtlas.DOOR_SIDE,
        1
    );

    private static CubeSchema LEFT_DOOR_SCHEMA = new CubeSchema(
        new Vector3fl(-1f, 0f, 0f),
        new Vector3fl(-1f, 0f, 0f),
        DOOR_FACE_DIMENSIONS,
        EntityTextureAtlas.DOOR_HALF_FACE_LEFT,
        EntityTextureAtlas.DOOR_HALF_FACE_RIGHT,
        EntityTextureAtlas.DOOR_END,
        EntityTextureAtlas.DOOR_END,
        EntityTextureAtlas.DOOR_SIDE,
        EntityTextureAtlas.DOOR_SIDE,
        2
    );

    private static CubeSchema LOCKED_SCHEMA = new CubeSchema(
        new Vector3fl(-1f, 0f, 0f),
        new Vector3fl(0f, 0f, 0f),
        new Vector3fl(2f, 2f, 0.25f),
        EntityTextureAtlas.DOOR_LOCKED_FACE,
        EntityTextureAtlas.DOOR_LOCKED_FACE,
        EntityTextureAtlas.DOOR_END,
        EntityTextureAtlas.DOOR_END,
        EntityTextureAtlas.DOOR_SIDE,
        EntityTextureAtlas.DOOR_SIDE,
        3
    );

    private static CubeSchema[] CUBE_SCHEMAS = new CubeSchema[] {
        LEFT_DOOR_SCHEMA,
        RIGHT_DOOR_SCHEMA,
        LOCKED_SCHEMA
    };

    public static DoorMesh DOOR_MESH = new DoorMesh();

    private Vector3fl rotationBuffer;

    public DoorMesh() {
        super(CUBE_SCHEMAS);
        this.rotationBuffer = new Vector3fl();
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
