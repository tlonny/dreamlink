package doors.level.block;

import doors.graphics.mesh.MeshBuffer;
import doors.graphics.texture.sample.TextureSample;
import doors.level.terrain.TerrainData;
import doors.utility.Orientation;
import doors.utility.vector.Vector3in;

public abstract class AbstractBlock {

    public int blockID;

    public AbstractBlock(int blockID) {
        this.blockID = blockID;
    }

    public abstract TextureSample getIconTextureSample();

    public abstract String getName();

    public abstract boolean isSolid();

    public abstract void writeBlockToMeshBuffer(
        TerrainData terrainData,
        MeshBuffer meshBuffer,
        Vector3in position,
        Orientation orientation
    );

}
