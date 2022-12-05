package dreamlink.world.room.module.userblock;

import org.joml.AABBf;
import org.joml.Vector3f;
import org.joml.Vector3i;

import dreamlink.audio.buffer.cache.SoundBufferRef;
import dreamlink.graphics.mesh.terrain.TerrainMeshBuffer;
import dreamlink.graphics.mesh.terrain.TerrainQuadData;
import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.graphics.texture.sample.EntityTextureSample;
import dreamlink.graphics.texture.sample.TextureSample;
import dreamlink.player.Player;
import dreamlink.state.SimulationState;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Orientation;
import dreamlink.utility.maths.Vector3fMaths;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.module.block.BlockLight;
import dreamlink.world.room.module.terrain.TerrainBlockData;
import dreamlink.world.room.module.terrain.TerrainLight;

public class TileBlock implements IUserBlock {

    private static final String stampType = "Tile Block";
    private static final float epsilon = 0.01f;

    private final int blockID;
    private final int light[] = new int[BlockLight.getSize()];
    private final IRoomModuleProvider provider;

    private final String fileName;
    private final boolean isAffectedByLight;
    private final boolean isSolid;
    private final float gain;
    private final TextureSample iconTextureSample;
    private final TextureSample topTextureSample;
    private final TextureSample bottomTextureSample;
    private final SoundBufferRef soundBufferRef;

    public TileBlock(
        IRoomModuleProvider provider,
        int blockID, 
        String fileName, 
        boolean isSolid,
        boolean isAffectedByLight,
        TextureSample iconTextureSample,
        TextureSample topTextureSample,
        TextureSample bottomTextureSample,
        SoundBufferRef soundBufferRef,
        float gain
    ) {
        this.gain = gain;
        this.provider = provider;
        this.blockID = blockID;
        this.isSolid = isSolid;
        this.fileName = fileName;
        this.isAffectedByLight = isAffectedByLight;
        this.iconTextureSample = iconTextureSample;
        this.topTextureSample = topTextureSample;
        this.bottomTextureSample = bottomTextureSample;
        this.soundBufferRef = soundBufferRef;
    }

    public float getGain() {
        return this.gain;
    }

    public TileBlock setLight(BlockLight blockLight, int lightLevel) {
        this.light[blockLight.getIndex()] = lightLevel;
        return this;
    }

    @Override
    public SoundBufferRef getSound() {
        return this.soundBufferRef;
    }

    @Override
    public String getFileName() {
        return this.fileName;
    }

    @Override
    public String getStampName() {
        return this.fileName;
    }

    @Override
    public String getStampType() {
        return TileBlock.stampType;
    }

    @Override
    public ISpriteTemplate getStampSprite() {
        return this.iconTextureSample;
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

    private Vector3f isCollisionMinPosition = new Vector3f();
    private Vector3f isCollisionMaxPosition = new Vector3f();
    private AABBf isCollisionBlockCollider = new AABBf();
    private AABBf isCollisionPlayerCollider = new AABBf();

    @Override
    public boolean isCollision(
        Vector3i blockPosition, 
        TerrainBlockData blockData
    ) {
        if(!this.isSolid || SimulationState.instance.getNoClip()) {
            return false;
        }

        var minPosition = this.isCollisionMinPosition.set(blockPosition);
        minPosition.y += 0.5f - TileBlock.epsilon;
        var maxPosition = this.isCollisionMaxPosition.set(blockPosition).add(1f, 0f, 1f);
        maxPosition.y += 0.5f + TileBlock.epsilon;

        var playerCollider = Player.instance.getCollider(this.isCollisionPlayerCollider);
        return this.isCollisionBlockCollider
            .setMin(minPosition)
            .setMax(maxPosition)
            .intersectsAABB(playerCollider);
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
            TextureSample sample;
            if(cubeFace == CubeFace.top) {
                sample = this.topTextureSample;
            } else if(cubeFace == CubeFace.bottom) {
                sample = this.bottomTextureSample;
            } else {
                continue;
            }

            sample = sample == null 
                ? EntityTextureSample.missing 
                : sample;

            var positionComponent = position.get(cubeFace.axisID);
            var terrainQuad = this.writeToTerrainMeshBufferQuad.set(
                this.writeToTerrainMeshBufferPosition
                    .set(position)
                    .setComponent(CubeFace.YAxis, positionComponent + 0.5f),
                Vector3fMaths.zero,
                this.writeToTerrainMeshBufferDimensions
                    .set(Vector3fMaths.one)
                    .setComponent(CubeFace.YAxis, 0),
                cubeFace,
                orientation,
                sample,
                sample == null,
                sample != null && this.isAffectedByLight,
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
