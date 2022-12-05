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

public class HalfSlabBlock implements IUserBlock {

    private static final String stampType = "Half Slab Block";

    private final IRoomModuleProvider provider;
    private final TextureSample iconTextureSample;
    private final TextureSample[] textureSamples = new TextureSample[CubeFace.getSize()];
    private final int light[] = new int[BlockLight.getSize()];

    private final int blockID;
    private final String fileName;
    private final boolean isAffectedByLight;
    private final float gain;
    private final boolean isSolid;
    
    public HalfSlabBlock(
        IRoomModuleProvider provider,
        int blockID, 
        String fileName, 
        boolean isSolid,
        boolean isAffectedByLight,
        TextureSample iconTextureSample,
        float gain
    ) {
        this.gain = gain;
        this.provider = provider;
        this.isSolid = isSolid;
        this.blockID = blockID;
        this.fileName = fileName;
        this.isAffectedByLight = isAffectedByLight;
        this.iconTextureSample = iconTextureSample;
    }

    public float getGain() {
        return this.gain;
    }

    public void setLight(BlockLight blockLight, int lightLevel) {
        this.light[blockLight.getIndex()] = lightLevel;
    }

    public void setTextureSample(CubeFace cubeFace, TextureSample textureSample) {
        this.textureSamples[cubeFace.getIndex()] = textureSample;
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
        return HalfSlabBlock.stampType;
    }

    @Override
    public SoundBufferRef getSound() {
        return null;
    }

    @Override
    public ISpriteTemplate getStampSprite() {
        return this.iconTextureSample;
    }

    private final TerrainBlockData onStampApplyBlockData = new TerrainBlockData();

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

    private final Vector3f isCollisionMinPosition = new Vector3f();
    private final Vector3f isCollisionMaxPosition = new Vector3f();
    private final AABBf isCollisionBlockCollider = new AABBf();
    private final AABBf isCollisionPlayerCollider = new AABBf();

    @Override
    public boolean isCollision(
        Vector3i blockPosition,
        TerrainBlockData blockData
    ) {
        if (!this.isSolid || SimulationState.instance.getNoClip()) {
            return false;
        }

        var minPosition = this.isCollisionMinPosition.set(blockPosition);
        var maxPosition = this.isCollisionMaxPosition.set(blockPosition).add(1f, 0.5f, 1f);

        var orientation = Orientation.get(blockData.orientationIndex);

        if (orientation.cubeFace.isPositive) {
            Vector3fMaths.addComponent(maxPosition, orientation.cubeFace.axisID, -0.5f);
        } else {
            Vector3fMaths.addComponent(minPosition, orientation.cubeFace.axisID, 0.5f);
        }

        var playerCollider = Player.instance.getCollider(this.isCollisionPlayerCollider);
        return this.isCollisionBlockCollider
            .setMin(minPosition)
            .setMax(maxPosition)
            .intersectsAABB(playerCollider);
    }


    private final TerrainBlockData onEraseBlockData = new TerrainBlockData();

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

    private final Vector3i writeToTerrainMeshBufferAdjacentPosition = new Vector3i();
    private final TerrainBlockData writeToTerrainMeshBufferAdjacentBlockData = new TerrainBlockData();
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
        var terrainModule = this.provider.getTerrainModule();

        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);
            var adjacentBlockData = terrainModule.getBlockData(
                this.writeToTerrainMeshBufferAdjacentPosition.set(position).add(cubeFace.normal),
                this.writeToTerrainMeshBufferAdjacentBlockData
            );

            if (
                adjacentBlockData.blockID == this.blockID &&
                adjacentBlockData.orientationIndex == blockData.orientationIndex &&
                cubeFace.axisID != CubeFace.YAxis &&
                cubeFace.axisID != orientation.cubeFace.axisID
            ) {
                continue;
            }

            var remapped = orientation.remap(cubeFace);
            var originalSample = this.textureSamples[remapped.getIndex()];
            var sample = originalSample == null ? EntityTextureSample.missing : originalSample;
            var orientationAxisID = orientation.cubeFace.axisID;

            var terrainQuad = this.writeToTerrainMeshBufferQuad.set(
                Vector3fMaths.addComponent(
                    this.writeToTerrainMeshBufferPosition.set(position),
                    orientationAxisID,
                    orientation.cubeFace.isPositive ? 0 : 0.5f
                ),
                Vector3fMaths.zero,
                this.writeToTerrainMeshBufferDimensions
                    .set(1f, 0.5f, 1f)
                    .setComponent(orientationAxisID, 0.5f),
                cubeFace,
                orientation,
                sample,
                originalSample == null,
                originalSample != null && this.isAffectedByLight,
                0
            );

            for(var jx = 0; jx < TerrainLight.getSize(); jx += 1) {
                var terrainLight = TerrainLight.get(jx);
                if (cubeFace == CubeFace.top || cubeFace == orientation.cubeFace) {
                    var selfLight = blockData.getLight(terrainLight);
                    this.writeToTerrainMeshBufferQuad.setLight(terrainLight, selfLight);
                } else {
                    var adjacentLight = this.writeToTerrainMeshBufferAdjacentBlockData.getLight(terrainLight);
                    this.writeToTerrainMeshBufferQuad.setLight(terrainLight, adjacentLight);
                }
            }

            meshBuffer.addQuad(terrainQuad);
        }
    }


}

