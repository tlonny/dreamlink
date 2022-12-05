package dreamlink.world.room.module.door;

import org.joml.AABBf;
import org.joml.Vector2i;
import org.joml.Vector2ic;
import org.joml.Vector3f;
import org.joml.Vector3i;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.utility.maths.Orientation;
import dreamlink.utility.maths.Vector3fMaths;
import dreamlink.utility.maths.Vector3iMaths;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.module.block.AirBlock;
import dreamlink.world.room.module.doortemplate.DoorTemplate;
import dreamlink.world.room.module.stamp.IStamp;
import dreamlink.world.room.module.terrain.TerrainBlockData;

public class Door implements IStamp {

    private class InternalDoorSprite implements ISpriteTemplate {

        @Override
        public void writeToSpriteBatch(
            SpriteBatch spriteBatch,
            Vector2ic position, 
            Vector2ic dimensions, 
            SpriteHeight height
        ) {
            spriteBatch.writeTextureSample(
                position, 
                dimensions,
                height,
                Door.this.template.iconTextureSample,
                Door.this.isPlaced ? Vector4fMaths.halfAlpha : Vector4fMaths.white
            );
        }

    }

    private static final Vector2ic[] centerOffsetCandidates = new Vector2ic[] {
        new Vector2i(-1, -1),
        new Vector2i(-1, 0),
        new Vector2i(-1, 1),
        new Vector2i(0, -1),
        new Vector2i(0, 0),
        new Vector2i(0, 1),
        new Vector2i(1, -1),
        new Vector2i(1, 0),
        new Vector2i(1, 1)
    };

    private static final int maxLightLevel = 0xF;
    private static final String stampType = "Door";

    private final IRoomModuleProvider provider;
    private final ISpriteTemplate doorSprite;

    public final DoorBlock block;
    public final DoorTemplate template;
    public final String name;

    public String targetRoomName;
    public String targetDoorName;

    private boolean isPlaced;
    private Orientation orientation;
    private final Vector3i centerPosition = new Vector3i();
    private final Vector3i[] blockPositions = new Vector3i[DoorSegment.getSize()];

    public Door(
        IRoomModuleProvider provider,
        DoorBlock block,
        DoorTemplate doorTemplate,
        String name
    ) {
        this.provider = provider;
        this.template = doorTemplate;
        this.block = block;
        this.name = name;
        this.doorSprite = new InternalDoorSprite();

        for(var ix = 0; ix < DoorSegment.getSize(); ix += 1) {
            this.blockPositions[ix] = new Vector3i();
        }
    }

    public Vector3i getCenterPosition(Vector3i target) {
        return target.set(this.centerPosition);
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public Vector3i getBlockPosition(DoorSegment segment, Vector3i target) {
        return target.set(this.blockPositions[segment.getIndex()]);
    }

    public void pickup() {
        this.isPlaced = false;
    }

    private final Vector2i setPlacementOffset = new Vector2i();
    private final Vector3i setPlacementBlockPosition = new Vector3i();

    public void setPlacement(
        Vector3i position,
        Orientation orientation
    ) {
        this.centerPosition.set(position);
        this.isPlaced = true;
        this.orientation = orientation;

        var orthogonalAxisID = orientation.getTurn().cubeFace.axisID;
        for (var ix = 0; ix < DoorSegment.getSize(); ix += 1) {
            var segment = DoorSegment.get(ix);
            var offset = segment.getBlockOffset(this.setPlacementOffset, orientation);
            var blockPosition = this.setPlacementBlockPosition.set(this.centerPosition);
            blockPosition.y += offset.y;
            Vector3iMaths.addComponent(
                blockPosition,
                orthogonalAxisID, 
                offset.x
            );
            this.blockPositions[segment.getIndex()].set(blockPosition);
        }
    }

    public Vector3f getPlanePosition(Vector3f plane) {
        plane.set(this.centerPosition);
        return Vector3fMaths.addComponent(
            plane,
            this.orientation.cubeFace.axisID,
            0.5f
        );
    }

    private final Vector3f getColliderBlockPosition = new Vector3f();
    private final Vector3f getColliderMinPosition = new Vector3f();
    private final Vector3f getColliderMaxPosition = new Vector3f();

    public AABBf getCollider(AABBf target) {
        this.getColliderMinPosition.set(Float.MAX_VALUE);
        this.getColliderMaxPosition.set(0f);

        for (var blockPosition : this.blockPositions) {
            this.getColliderMinPosition.min(
                this.getColliderBlockPosition.set(blockPosition)
            );
            this.getColliderMaxPosition.max(
                this.getColliderBlockPosition.add(1f, 1f, 1f)
            );
        }

        var minPosition = Vector3fMaths.addComponent(
            this.getColliderMinPosition,
            this.orientation.cubeFace.axisID,
            this.orientation.cubeFace.isPositive ? 0 : 0.5f
        );

        var maxPosition = Vector3fMaths.addComponent(
            this.getColliderMaxPosition,
            this.orientation.cubeFace.axisID,
            this.orientation.cubeFace.isPositive ? -0.5f : 0
        );

        return target
            .setMin(minPosition)
            .setMax(maxPosition);
    }

    @Override
    public String getStampName() {
        return this.name;
    }

    @Override
    public String getStampType() {
        return Door.stampType;
    }

    @Override
    public ISpriteTemplate getStampSprite() {
        return this.doorSprite;
    }

    public boolean isPlaced() {
        return this.isPlaced;
    }

    private final Vector3i onStampApplyCenterCandidate = new Vector3i();
    private final Vector3i onStampApplyCenterPosition = new Vector3i();
    private final Vector3i onStampApplyBlockPosition = new Vector3i();
    private final TerrainBlockData onStampApplyBlockData = new TerrainBlockData();
    private final Vector2i onStampApplyOffset = new Vector2i();

    @Override
    public void onStampApply(
        Vector3i position, 
        Vector3f rayTerminationPosition,
        Orientation orientation
    ) {
        var terrainModule = this.provider.getTerrainModule();

        if (this.isPlaced) {
            return;
        }

        var orthogonalAxisID = orientation.getTurn().cubeFace.axisID;
        var bestDistance = Float.MAX_VALUE;

        for (var ix = 0; ix < Door.centerOffsetCandidates.length; ix += 1) {
            var centerOffset = Door.centerOffsetCandidates[ix];
            var centerCandidate = this.onStampApplyCenterCandidate.set(position);
            centerCandidate.y += centerOffset.y();
            Vector3iMaths.addComponent(
                centerCandidate,
                orthogonalAxisID, 
                centerOffset.x()
            );

            var isCollision = false;
            for (var jx = 0; jx < DoorSegment.getSize(); jx += 1) {
                var segment = DoorSegment.get(jx);
                var blockPosition = this.onStampApplyBlockPosition.set(centerCandidate);
                var offset = segment.getBlockOffset(this.onStampApplyOffset, orientation);
                blockPosition.y += offset.y;
                Vector3iMaths.addComponent(
                    blockPosition,
                    orthogonalAxisID, 
                    offset.x
                );

                var blockID = terrainModule.getBlockData(
                    blockPosition,
                    this.onStampApplyBlockData
                ).blockID;

                if (blockID != AirBlock.blockID) {
                    isCollision = true;
                    break;
                }
            }

            if (isCollision) {
                continue;
            }

            var distance = Vector3fMaths.distance(
                rayTerminationPosition, 
                centerCandidate
            );

            if (distance < bestDistance) {
                bestDistance = distance;
                this.onStampApplyCenterPosition.set(centerCandidate);
            }
        }

        if (bestDistance == Float.MAX_VALUE) {
            return;
        }

        this.setPlacement(
            this.onStampApplyCenterPosition,
            orientation
        );

        var blockData = this.onStampApplyBlockData.set(this.block, orientation);
        for (var ix = 0; ix < this.blockPositions.length; ix += 1) {
            var blockPosition = this.blockPositions[ix];
            terrainModule.setBlockData(blockPosition, blockData);
        }
    }

    private final Vector3f getPortalLightColor = new Vector3f();
    private final TerrainBlockData getPortalLightBlockData = new TerrainBlockData();

    public Vector3f getIncidentLight(Vector3f target) {
        var colorModule = this.provider.getColorModule();
        var settingsModule = this.provider.getSettingsModule();
        var terrainModule = this.provider.getTerrainModule();

        if(!settingsModule.isLightingEnabled) {
            return target.set(Vector3fMaths.one);
        }

        target.set(settingsModule.baseLight);
        for (var ix = 0; ix < this.blockPositions.length; ix += 1) {
            var blockPosition = this.blockPositions[ix];
            var blockData = terrainModule.getBlockData(
                blockPosition, 
                this.getPortalLightBlockData
            );

            for (var jx = 0; jx < DoorLightSample.getSize(); jx += 1) {
                var lightSample = DoorLightSample.get(jx);
                var lightLevel = (float) blockData.getLight(lightSample.lightChannel) / Door.maxLightLevel;
                var lightColor = colorModule.getColor(this.getPortalLightColor, lightSample.colorConfig).mul(lightLevel);
                target.max(lightColor);
            }
        }

        return target;
    }

    public void setPortalLight() {
        var portalLightModule = this.provider.getPortalLightModule();
        portalLightModule.clear();
        for (var ix = 0; ix < this.blockPositions.length; ix += 1) {
            var blockPosition = this.blockPositions[ix];
            portalLightModule.setLight(blockPosition, Door.maxLightLevel);
        }
    }

    public boolean isLinked() {
        if(this.targetDoorName == null || this.targetDoorName.isEmpty()) {
            return false;
        }

        if(this.targetRoomName == null || this.targetRoomName.isEmpty()) {
            return false;
        }

        var targetRoom = this.provider.getRoomCacheModule().getRoom(
            this.targetRoomName, 
            !this.provider.isShadowCopy()
        );

        if(!targetRoom.isFinalized()) {
            return false;
        }
        
        var targetDoor = targetRoom.getDoorByName(this.targetDoorName);
        if(targetDoor == null) {
            return false;
        }

        var roomName = this.provider.getName();
        if(!targetDoor.targetRoomName.equals(roomName)) {
            return false;
        }

        if(!targetDoor.targetDoorName.equals(this.name)) {
            return false;
        }

        return true;
    }

    public DoorLinkData resolveLink(DoorLinkData portalData) {
        var targetRoom = this.provider.getRoomCacheModule().getRoom(
            this.targetRoomName, 
            !this.provider.isShadowCopy()
        );

        var targetDoor = targetRoom.getDoorByName(this.targetDoorName);
        return portalData.set(targetRoom, targetDoor);
    }

}
