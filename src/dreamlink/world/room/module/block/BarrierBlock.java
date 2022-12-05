package dreamlink.world.room.module.block;

import org.joml.AABBf;
import org.joml.Vector3f;
import org.joml.Vector3i;

import dreamlink.audio.buffer.cache.SoundBufferRef;
import dreamlink.graphics.mesh.terrain.TerrainMeshBuffer;
import dreamlink.graphics.mesh.terrain.TerrainQuadData;
import dreamlink.graphics.texture.sample.EntityTextureSample;
import dreamlink.player.Player;
import dreamlink.utility.maths.AABBfMaths;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Orientation;
import dreamlink.utility.maths.Vector3fMaths;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.module.terrain.TerrainBlockData;
import dreamlink.world.room.module.terrain.TerrainLight;

public class BarrierBlock implements IBlock {

    public static final int blockID = 0x01;

    private final IRoomModuleProvider provider;

    public BarrierBlock(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    @Override
    public int getBlockID() {
        return BarrierBlock.blockID;
    }

    @Override
    public int getLight(BlockLight blockLight) {
        return 0;
    }

    public float getGain() {
        return 0.0f;
    }

    @Override
    public boolean canTransmitLight(CubeFace cubeFace) {
        return false;
    }

    @Override
    public boolean canEmitLight(CubeFace cubeFace) {
        return true;
    }

    @Override
    public boolean canTransmitSound(CubeFace cubeFace) {
        return false;
    }

    @Override
    public boolean canEmitSound(CubeFace cubeFace) {
        return true;
    }

    public SoundBufferRef getSound() {
        return null;
    }

    private AABBf isCollisionBlockCollider = new AABBf();
    private AABBf isCollisionPlayerCollider = new AABBf();

    @Override
    public boolean isCollision(
        Vector3i blockPosition, 
        TerrainBlockData blockData
    ) {
        var playerCollider = Player.instance.getCollider(this.isCollisionPlayerCollider);
        return AABBfMaths
            .setUnitCube(this.isCollisionBlockCollider, blockPosition)
            .intersectsAABB(playerCollider);
    }

    @Override
    public void onErase(
        Vector3i blockPosition,
        TerrainBlockData blockData
    ) {

    }

    @Override
    public boolean canInteract(
        Vector3i blockPosition, 
        TerrainBlockData blockData
    ) {
        return false;
    }

    @Override
    public void onInteract(
        Vector3i blockPosition, 
        TerrainBlockData blockData
    ) {

    }

    @Override
    public void onEyeDrop(
        Vector3i blockPosition, 
        TerrainBlockData blockData
    ) {

    }

    private final Vector3i writeToTerrainMeshBufferAdjacentPosition = new Vector3i();
    private final TerrainBlockData writeToTerrainMeshBufferAdjacentBlockData = new TerrainBlockData();
    private final TerrainQuadData writeToTerrainMeshBufferQuad = new TerrainQuadData();
    private final Vector3f writeToTerrainMeshBufferPosition = new Vector3f();

    @Override
    public void writeToTerrainMeshBuffer(
        TerrainMeshBuffer meshBuffer,
        Vector3i position, 
        TerrainBlockData blockData
    ) {
        var terrainModule = this.provider.getTerrainModule();
        var orientation = Orientation.get(blockData.orientationIndex);
        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);
            var adjacentBlockData = terrainModule.getBlockData(
                this.writeToTerrainMeshBufferAdjacentPosition.set(position).add(cubeFace.normal),
                this.writeToTerrainMeshBufferAdjacentBlockData
            );

            if(adjacentBlockData.blockID == this.getBlockID()) {
                continue;
            }

            var quad = this.writeToTerrainMeshBufferQuad
                .clearLight()
                .set(
                    writeToTerrainMeshBufferPosition.set(position),
                    Vector3fMaths.zero,
                    Vector3fMaths.one,
                    cubeFace,
                    orientation,
                    EntityTextureSample.missing,
                    true,
                    false,
                    0
                );

            for(var jx = 0; jx < TerrainLight.getSize(); jx += 1) {
                var terrainLight = TerrainLight.get(jx);
                var adjacentLight = adjacentBlockData.getLight(terrainLight);
                quad.setLight(terrainLight, adjacentLight);
            }

            meshBuffer.addQuad(quad);
        }
    }

}
