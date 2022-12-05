package dreamlink.world.room.module.block;

import org.joml.Vector3i;

import dreamlink.audio.buffer.cache.SoundBufferRef;
import dreamlink.graphics.mesh.terrain.TerrainMeshBuffer;
import dreamlink.utility.maths.CubeFace;
import dreamlink.world.room.module.terrain.TerrainBlockData;

public interface IBlock {

    public int getBlockID();

    public int getLight(BlockLight blockLight);

    public float getGain();

    public boolean canTransmitLight(CubeFace cubeFace);

    public boolean canEmitLight(CubeFace cubeFace);

    public boolean canTransmitSound(CubeFace cubeFace);

    public boolean canEmitSound(CubeFace cubeFace);

    public SoundBufferRef getSound(); 

    public boolean isCollision(
        Vector3i blockPosition,
        TerrainBlockData blockData
    );

    public void onErase(
        Vector3i blockPosition,
        TerrainBlockData blockData
    );

    public boolean canInteract(
        Vector3i blockPosition, 
        TerrainBlockData blockData
    );

    public void onInteract(
        Vector3i blockPosition, 
        TerrainBlockData blockData
    );

    public void onEyeDrop(
        Vector3i blockPosition,
        TerrainBlockData blockData
    );

    public void writeToTerrainMeshBuffer(
        TerrainMeshBuffer meshBuffer,
        Vector3i position,
        TerrainBlockData blockData
    );


}
