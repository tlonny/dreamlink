package dreamlink.player.ray;

import org.joml.Vector3f;
import org.joml.Vector3i;

import dreamlink.utility.maths.CubeFace;
import dreamlink.world.room.module.terrain.TerrainBlockData;

public class RayCastData {

    public final Vector3i blockPosition = new Vector3i();
    public final Vector3f rayPosition = new Vector3f();
    public final TerrainBlockData blockData = new TerrainBlockData();
    public CubeFace rayCubeFace;
    public int numIterations;
    public boolean isHit;

    public RayCastData set(
        CubeFace rayCubeFace, 
        Vector3i blockPosition, 
        Vector3f rayPosition, 
        TerrainBlockData blockData,
        int numIterations,
        boolean isHit
    ) {
        this.rayCubeFace = rayCubeFace;
        this.blockPosition.set(blockPosition);
        this.rayPosition.set(rayPosition);
        this.blockData.set(blockData);
        this.numIterations = numIterations;
        this.isHit = isHit;
        return this;
    }

    public RayCastData set(RayCastData other) {
        return this.set(
            other.rayCubeFace, 
            other.blockPosition, 
            other.rayPosition, 
            other.blockData,
            other.numIterations,
            other.isHit
        );
    }
}
