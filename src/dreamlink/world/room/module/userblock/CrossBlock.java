package dreamlink.world.room.module.userblock;

import org.joml.Vector3f;
import org.joml.Vector3i;

import dreamlink.audio.buffer.cache.SoundBufferRef;
import dreamlink.graphics.mesh.terrain.TerrainMeshBuffer;
import dreamlink.graphics.mesh.terrain.TerrainQuadData;
import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.graphics.texture.sample.EntityTextureSample;
import dreamlink.graphics.texture.sample.TextureSample;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Orientation;
import dreamlink.utility.maths.Vector3fMaths;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.module.block.BlockLight;
import dreamlink.world.room.module.terrain.TerrainBlockData;
import dreamlink.world.room.module.terrain.TerrainLight;

public class CrossBlock implements IUserBlock {

    private static final String stampType = "Cross Block";

    private final int blockID;
    private final int light[] = new int[BlockLight.getSize()];
    private final IRoomModuleProvider provider;

    private final String fileName;
    private final float gain;
    private final boolean isAffectedByLight;
    private final TextureSample iconTextureSample;
    private final TextureSample textureSample;

    public CrossBlock(
        IRoomModuleProvider provider,
        int blockID, 
        String fileName, 
        boolean isAffectedByLight,
        TextureSample iconTextureSample,
        TextureSample textureSample,
        float gain
    ) {
        this.gain = gain;
        this.provider = provider;
        this.blockID = blockID;
        this.fileName = fileName;
        this.isAffectedByLight = isAffectedByLight;
        this.iconTextureSample = iconTextureSample;
        this.textureSample = textureSample;
    }

    public void setLight(BlockLight blockLight, int lightLevel) {
        this.light[blockLight.getIndex()] = lightLevel;
    }

    public float getGain() {
        return this.gain;
    }

    @Override
    public String getStampName() {
        return this.fileName;
    }

    @Override
    public String getFileName() {
        return this.fileName;
    }

    @Override
    public String getStampType() {
        return CrossBlock.stampType;
    }

    @Override
    public ISpriteTemplate getStampSprite() {
        return this.iconTextureSample;
    }

    public SoundBufferRef getSound() {
        return null;
    }

    private TerrainBlockData onStampApplyBlockData = new TerrainBlockData();

    @Override
    public void onStampApply(
        Vector3i position, 
        Vector3f rayTerminationPosition,
        Orientation orientation
    ) {
        var blockData = this.onStampApplyBlockData.set(this, orientation);
        this.provider.getTerrainModule().setBlockData(position, blockData);
    }

    @Override
    public int getBlockID() {
        return this.blockID;
    }

    @Override
    public int getLight(BlockLight blockLight) {
        return this.light[blockLight.getIndex()];
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

    @Override
    public boolean isCollision(
        Vector3i blockPosition, 
        TerrainBlockData blockData
    ) {
        return false;
    }

    private TerrainBlockData onEraseBlockData = new TerrainBlockData();

    @Override
    public void onErase(
        Vector3i blockPosition,
        TerrainBlockData blockData
    ) {
        var eraseBlockData = this.onEraseBlockData.clear();
        this.provider.getTerrainModule().setBlockData(
            blockPosition, 
            eraseBlockData
        );
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
        var pallete = this.provider.getPaletteModule();
        pallete.setSelectedStamp(this);
    }

    private final Vector3f writeToTerrainMeshBufferPosition = new Vector3f();
    private final Vector3f writeToTerrainMeshBufferDimensions = new Vector3f();
    private final TerrainQuadData writeToTerrainMeshBufferQuad = new TerrainQuadData();

    @Override
    public void writeToTerrainMeshBuffer(
        TerrainMeshBuffer meshBuffer,
        Vector3i position, 
        TerrainBlockData blockData
    ) {
        var orientation = Orientation.get(blockData.orientationIndex);

        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);
            if (cubeFace.axisID == CubeFace.YAxis) {
                continue;
            }

            var sample = this.textureSample == null 
                ? EntityTextureSample.missing 
                : this.textureSample;

            var positionComponent = position.get(cubeFace.axisID);
            var terrainQuad = this.writeToTerrainMeshBufferQuad.set(
                this.writeToTerrainMeshBufferPosition
                    .set(position)
                    .setComponent(cubeFace.axisID, positionComponent + 0.5f),
                Vector3fMaths.zero,
                this.writeToTerrainMeshBufferDimensions
                    .set(Vector3fMaths.one)
                    .setComponent(cubeFace.axisID, 0),
                cubeFace,
                orientation,
                sample,
                this.textureSample == null,
                this.textureSample != null && this.isAffectedByLight,
                0
            );

            for(var jx = 0; jx < TerrainLight.getSize(); jx += 1) {
                var terrainLight = TerrainLight.get(jx);
                var selfLight = blockData.getLight(terrainLight);
                terrainQuad.setLight(terrainLight, selfLight);
            }

            meshBuffer.addQuad(terrainQuad);
        }
}

    
}
