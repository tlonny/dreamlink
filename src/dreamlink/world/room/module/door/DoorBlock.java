package dreamlink.world.room.module.door;

import org.joml.AABBf;
import org.joml.Vector3f;
import org.joml.Vector3i;

import dreamlink.audio.buffer.cache.SoundBufferRef;
import dreamlink.graphics.mesh.terrain.TerrainMeshBuffer;
import dreamlink.graphics.mesh.terrain.TerrainQuadData;
import dreamlink.graphics.texture.sample.EntityTextureSample;
import dreamlink.player.Player;
import dreamlink.state.SimulationState;
import dreamlink.utility.maths.AABBfMaths;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Orientation;
import dreamlink.utility.maths.Vector3fMaths;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.module.block.BlockLight;
import dreamlink.world.room.module.block.IBlock;
import dreamlink.world.room.module.terrain.TerrainBlockData;

public class DoorBlock implements IBlock {

    private final IRoomModuleProvider provider;
    private final int blockID;

    public DoorBlock(
        IRoomModuleProvider provider,
        int blockID
    ) {
        this.provider = provider;
        this.blockID = blockID;
    }

    @Override
    public int getBlockID() {
        return this.blockID;
    }

    public float getGain() {
        return 0f;
    }

    public SoundBufferRef getSound() {
        return null;
    }

    @Override
    public int getLight(BlockLight blockLight) {
        return 0;
    }

    @Override
    public boolean canTransmitLight(CubeFace cubeFace) {
        return cubeFace != CubeFace.back;
    }

    @Override
    public boolean canEmitLight(CubeFace cubeFace) {
        return true;
    }

    @Override
    public boolean canTransmitSound(CubeFace cubeFace) {
        return cubeFace != CubeFace.back;
    }

    @Override
    public boolean canEmitSound(CubeFace cubeFace) {
        return cubeFace != CubeFace.back;
    }

    private final AABBf isCollisionPlayerCollider = new AABBf();
    private final AABBf isCollisionDoorCollider = new AABBf();
    private final AABBf isCollisionProjection = new AABBf();

    @Override
    public boolean isCollision(
        Vector3i blockPosition,
        TerrainBlockData blockData
    ) {
        if (SimulationState.instance.getNoClip()) {
            return false;
        }
        var doorSystem = this.provider.getDoorModule();
        var door = doorSystem.getDoorByBlockID(this.blockID);
        var doorCollider = door.getCollider(this.isCollisionDoorCollider);
        var playerCollider = Player.instance.getCollider(this.isCollisionPlayerCollider);
        var openDoor = this.provider.getRoomStateModule().getOpenDoor();

        if(openDoor != door) {
            return doorCollider.intersectsAABB(playerCollider);
        }

        for (var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);
            if (door.getOrientation().cubeFace == cubeFace) {
                continue;
            }

            var projection = AABBfMaths.project(
                this.isCollisionProjection,
                doorCollider, 
                cubeFace
            );

            if (projection.intersectsAABB(playerCollider)) {
                return true;
            }
        }
        return false;
    }

    private final Vector3i onEraseBlockPosition = new Vector3i();
    private final TerrainBlockData onEroaseBlockData = new TerrainBlockData();

    @Override
    public void onErase(Vector3i blockPosition, TerrainBlockData blockData) {
        var terrainModule = this.provider.getTerrainModule();

        var door = this.provider
            .getDoorModule()
            .getDoorByBlockID(this.blockID);

        var clearBlockData = this.onEroaseBlockData.clear();
        for(var ix = 0; ix < DoorSegment.getSize(); ix += 1) {
            var segment = DoorSegment.get(ix);
            var doorBlockPosition = door.getBlockPosition(segment, this.onEraseBlockPosition);
            terrainModule.setBlockData(doorBlockPosition, clearBlockData);
        }

        door.pickup();
    }

    private final AABBf canInteractDoorCollider = new AABBf();
    private final AABBf canInteractPlayerCollider = new AABBf();

    @Override
    public boolean canInteract(
        Vector3i blockPosition, 
        TerrainBlockData blockData
    ) {
        var playerCollider = Player.instance.getCollider(this.canInteractPlayerCollider);
        var openDoor = this.provider.getRoomStateModule().getOpenDoor();

        if(openDoor != null) {
            var doorCollider = openDoor.getCollider(this.canInteractDoorCollider);
            if(doorCollider.intersectsAABB(playerCollider)) {
                return false;
            }
        }

        var door = this.provider
            .getDoorModule()
            .getDoorByBlockID(this.blockID);

        return door.isLinked();
    }

    @Override
    public void onInteract(
        Vector3i blockPosition, 
        TerrainBlockData blockData
    ) {
        var door = this.provider
            .getDoorModule()
            .getDoorByBlockID(this.blockID);

        this.provider.getRoomStateModule().setOpenDoor(door, true);
    }

    @Override
    public void onEyeDrop(
        Vector3i blockPosition, 
        TerrainBlockData blockData
    ) {
        var door = this.provider
            .getDoorModule()
            .getDoorByBlockID(this.blockID);

        var pallete = this.provider.getPaletteModule();
        pallete.setSelectedStamp(door);
    }

    private final Vector3i writeToTerrainMeshBufferAdjacentPosition = new Vector3i();
    private final TerrainBlockData writeToTerrainMeshBufferAdjacentBlockData = new TerrainBlockData();
    private final Vector3f writeToTerrainMeshBufferPosition = new Vector3f();
    private final Vector3f writeToTerrainMeshBufferDimensions = new Vector3f();
    private final TerrainQuadData writeToTerrainMeshBufferQuad = new TerrainQuadData();

    @Override
    public void writeToTerrainMeshBuffer(
        TerrainMeshBuffer meshBuffer,
        Vector3i blockPosition, 
        TerrainBlockData blockData
    ) {
        var terrainModule = this.provider.getTerrainModule();
        var orientation = Orientation.get(blockData.orientationIndex);

        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);
            var adjacentBlockData = terrainModule.getBlockData(
                this.writeToTerrainMeshBufferAdjacentPosition
                    .set(blockPosition)
                    .add(cubeFace.normal),
                this.writeToTerrainMeshBufferAdjacentBlockData
            );

            if (orientation.cubeFace == cubeFace) {
                continue;
            }

            if (adjacentBlockData.blockID == this.getBlockID()) {
                continue;
            }

            var position = Vector3fMaths.addComponent(
                this.writeToTerrainMeshBufferPosition.set(blockPosition),
                orientation.cubeFace.axisID,
                orientation.cubeFace.isPositive ? 0f : 0.5f
            );

            var dimensions = this.writeToTerrainMeshBufferDimensions
                .set(Vector3fMaths.one)
                .setComponent(orientation.cubeFace.axisID, 0.5f);

            var terrainQuad = this.writeToTerrainMeshBufferQuad
                .clearLight()
                .set(
                    position,
                    Vector3fMaths.zero,
                    dimensions,
                    cubeFace,
                    orientation,
                    EntityTextureSample.placeholder,
                    false,
                    false,
                    0
                );

            meshBuffer.addQuad(terrainQuad);
        }
    }

}
