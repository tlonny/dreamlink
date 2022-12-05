package dreamlink.world.room.module.block;

import org.joml.Vector3i;

import dreamlink.audio.buffer.cache.SoundBufferRef;
import dreamlink.graphics.mesh.terrain.TerrainMeshBuffer;
import dreamlink.utility.maths.CubeFace;
import dreamlink.world.room.module.terrain.TerrainBlockData;

public class AirBlock implements IBlock {

    public static final int blockID = 0x00;

    @Override
    public int getBlockID() {
        return AirBlock.blockID;
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
        return true;
    }

    @Override
    public boolean canEmitLight(CubeFace cubeFace) {
        return true;
    }

    @Override
    public boolean canTransmitSound(CubeFace cubeFace) {
        return true;
    }

    @Override
    public boolean canEmitSound(CubeFace cubeFace) {
        return true;
    }

    public SoundBufferRef getSound() {
        return null;
    }

    @Override
    public boolean isCollision(
        Vector3i blockPosition,
        TerrainBlockData blockData
    ) {
        return false;
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

    @Override
    public void writeToTerrainMeshBuffer(
        TerrainMeshBuffer meshBuffer,
        Vector3i position, 
        TerrainBlockData blockData
    ) {

    }
}
