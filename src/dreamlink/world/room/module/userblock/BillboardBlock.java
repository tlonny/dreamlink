package dreamlink.world.room.module.userblock;

import org.joml.Vector3f;
import org.joml.Vector3i;

import dreamlink.audio.buffer.cache.SoundBufferRef;
import dreamlink.graphics.mesh.terrain.TerrainMeshBuffer;
import dreamlink.graphics.mesh.terrain.TerrainQuadData;
import dreamlink.graphics.program.TerrainShaderProgram;
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

public class BillboardBlock implements IUserBlock {

    private final static int lookAtTransformerIndex = 1;

    public static void setTransformers(float yaw) {
        TerrainShaderProgram.instance.setTransformer(
            BillboardBlock.lookAtTransformerIndex,
            Vector3fMaths.zero,
            yaw
        );
    }

    private static final String stampType = "Billboard Block";

    private final int blockID;
    private final int light[] = new int[BlockLight.getSize()];
    private final IRoomModuleProvider provider;
    private final float gain;

    private final String fileName;
    private final boolean isHidden;
    private final boolean isAffectedByLight;
    private final TextureSample iconTextureSample;
    private final TextureSample textureSample;
    private final SoundBufferRef sound;

    public BillboardBlock(
        IRoomModuleProvider provider,
        int blockID, 
        String fileName, 
        boolean isAffectedByLight,
        boolean isHidden,
        TextureSample iconTextureSample,
        TextureSample textureSample,
        SoundBufferRef sound,
        float gain
    ) {
        this.gain = gain;
        this.provider = provider;
        this.blockID = blockID;
        this.fileName = fileName;
        this.isAffectedByLight = isAffectedByLight;
        this.isHidden = isHidden;
        this.iconTextureSample = iconTextureSample;
        this.textureSample = textureSample;
        this.sound = sound;
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
        return BillboardBlock.stampType;
    }

    @Override
    public ISpriteTemplate getStampSprite() {
        return this.iconTextureSample;
    }

    public SoundBufferRef getSound() {
        return this.sound;
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
    private final Vector3f writeToTerrainMeshBufferFulcrum = new Vector3f();
    private final Vector3f writeToTerrainMeshBufferDimensions = new Vector3f();
    private final TerrainQuadData writeToTerrainMeshBufferQuad = new TerrainQuadData();

    @Override
    public void writeToTerrainMeshBuffer(
        TerrainMeshBuffer meshBuffer,
        Vector3i position, 
        TerrainBlockData blockData
    ) {
        var sample = this.textureSample == null 
            ? EntityTextureSample.missing 
            : this.textureSample;

        var quadFulcrum = this.writeToTerrainMeshBufferFulcrum.set(position);
        var quadPosition = this.writeToTerrainMeshBufferPosition.set(position);
        quadPosition.z += 0.5f;
        quadFulcrum.add(0.5f, 0.5f, 0.5f);

        var terrainQuad = this.writeToTerrainMeshBufferQuad.set(
            quadPosition,
            quadFulcrum,
            this.writeToTerrainMeshBufferDimensions.set(1f, 1f, 0f),
            CubeFace.front,
            Orientation.front,
            sample,
            this.isHidden || this.textureSample == null,
            this.textureSample != null && this.isAffectedByLight,
            BillboardBlock.lookAtTransformerIndex
        );

        for(var ix = 0; ix < TerrainLight.getSize(); ix += 1) {
            var terrainLight = TerrainLight.get(ix);
            var selfLight = blockData.getLight(terrainLight);
            terrainQuad.setLight(terrainLight, selfLight);
        }

        meshBuffer.addQuad(terrainQuad);
    }
}