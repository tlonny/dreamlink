package dreamlink.world.room.module.doortemplate;

import org.joml.Vector2i;
import org.joml.Vector3f;

import dreamlink.graphics.mesh.terrain.TerrainMesh;
import dreamlink.graphics.mesh.terrain.TerrainMeshBuffer;
import dreamlink.graphics.mesh.terrain.TerrainQuadData;
import dreamlink.graphics.program.TerrainShaderProgram;
import dreamlink.graphics.texture.sample.EntityTextureSample;
import dreamlink.graphics.texture.sample.TextureSample;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Orientation;
import dreamlink.utility.maths.Vector3fMaths;

public class DoorTemplate {

    private final static int leftDoorTransformerIndex = 1;
    private final static int rightDoorTransformerIndex = 2;
    private final static Vector3f leftDoorTransformerPosition = new Vector3f(1f, 0f, 0f);
    private final static Vector3f rightDoorTransformerPosition = new Vector3f(-1f, 0f, 0f);
    private final static Vector3f leftDoorPosition = new Vector3f(0f, -1f, 0f);
    private final static Vector3f rightDoorPosition = new Vector3f(-1f, -1f, 0f);
    private final static Vector3f doorDimensions = new Vector3f(1f, 2f, 0f);
    private final static float rightAngleRad = (float) Math.toRadians(90);

    public final static DoorTemplate defaultDoorTemplate = new DoorTemplate(
        "default",
        EntityTextureSample.door,
        EntityTextureSample.door
    );

    private static float getAngleFromDoorOpenFactor(float doorOpenFactor) {
        return doorOpenFactor * DoorTemplate.rightAngleRad;
    }

    public static void setTransformers(float doorOpenFactor) {
        TerrainShaderProgram.instance.setTransformer(
            DoorTemplate.leftDoorTransformerIndex,
            DoorTemplate.leftDoorTransformerPosition,
            DoorTemplate.getAngleFromDoorOpenFactor(doorOpenFactor)
        );

        TerrainShaderProgram.instance.setTransformer(
            DoorTemplate.rightDoorTransformerIndex,
            DoorTemplate.rightDoorTransformerPosition,
            -DoorTemplate.getAngleFromDoorOpenFactor(doorOpenFactor)
        );
    }

    private final TextureSample leftDoorTextureSample;
    private final TextureSample rightDoorTextureSample;
    public final TextureSample iconTextureSample;
    public final String name;

    private final TerrainMesh doorMesh = new TerrainMesh();

    public DoorTemplate(
        String name,
        TextureSample textureSample,
        TextureSample iconTextureSample
    ) {
        this.name = name;

        var singleDoorDimensions = new Vector2i(textureSample.dimensions);
        singleDoorDimensions.x /= 2;
        
        var leftDoorPosition = new Vector2i(textureSample.position);
        leftDoorPosition.x += singleDoorDimensions.x;

        this.leftDoorTextureSample = new TextureSample(
            textureSample.textureUnit,
            textureSample.atlasDimensions,
            leftDoorPosition,
            singleDoorDimensions,
            textureSample.animationStride,
            textureSample.animationTotalFrames,
            textureSample.animationRowFrames,
            textureSample.animationStartFrame,
            textureSample.animationSpeed
        );

        this.rightDoorTextureSample = new TextureSample(
            textureSample.textureUnit,
            textureSample.atlasDimensions,
            textureSample.position,
            singleDoorDimensions,
            textureSample.animationStride,
            textureSample.animationTotalFrames,
            textureSample.animationRowFrames,
            textureSample.animationStartFrame,
            textureSample.animationSpeed
        ); 
        
        this.iconTextureSample = iconTextureSample;
    }

    public void setup() {
        this.doorMesh.setup();
        var meshBuffer = TerrainMeshBuffer.instance.clear();
        var quadData = new TerrainQuadData();

        meshBuffer.addQuad(quadData.set(
            DoorTemplate.leftDoorPosition,
            Vector3fMaths.zero,
            DoorTemplate.doorDimensions,
            CubeFace.front,
            Orientation.front,
            this.leftDoorTextureSample,
            false,
            true,
            DoorTemplate.leftDoorTransformerIndex
        ));

        meshBuffer.addQuad(quadData.set(
            DoorTemplate.rightDoorPosition,
            Vector3fMaths.zero,
            DoorTemplate.doorDimensions,
            CubeFace.front,
            Orientation.front,
            this.rightDoorTextureSample,
            false,
            true,
            DoorTemplate.rightDoorTransformerIndex
        ));

        this.doorMesh.buffer(meshBuffer);
    }

    public void render() {
        this.doorMesh.render();
    }

    public void destroy() {
        this.doorMesh.destroy();
    }
    
}
