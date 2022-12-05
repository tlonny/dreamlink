package dreamlink.player;

import org.joml.AABBf;
import org.joml.AABBi;
import org.joml.Vector2f;
import org.joml.Vector2fc;
import org.joml.Vector3f;
import org.joml.Vector3i;

import dreamlink.player.kinematics.KinematicsStateSnapShot;
import dreamlink.utility.maths.AABBfMaths;
import dreamlink.utility.maths.AABBiMaths;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.PortalTransformer;
import dreamlink.utility.maths.Vector2fMaths;
import dreamlink.utility.maths.Vector3fMaths;
import dreamlink.window.Window;
import dreamlink.window.button.Button;
import dreamlink.world.World;
import dreamlink.world.room.module.door.DoorLinkData;
import dreamlink.world.room.module.terrain.TerrainBlockData;

public class PositionIntegrationModule {

    private static final float stepEpsilon = 0.001f;
    private static final float nudgeEpsilon = 0.001f;
    private static final float climbEpsilon = 0.01f;
    private static final float climbHeight = 0.55f;
    private static final float portalColliderShrinkAmount = 0.005f;

    private static final Vector2fc[] nudgeVectors = new Vector2fc[] {
        new Vector2f(
            0,
            0
        ),
        new Vector2f(
            PositionIntegrationModule.nudgeEpsilon, 
            0
        ),
        new Vector2f(
            PositionIntegrationModule.nudgeEpsilon, 
            PositionIntegrationModule.nudgeEpsilon
        ),
        new Vector2f(
            0, 
            PositionIntegrationModule.nudgeEpsilon
        ),
        new Vector2f(
            -PositionIntegrationModule.nudgeEpsilon, 
            PositionIntegrationModule.nudgeEpsilon
        ),
        new Vector2f(
            -PositionIntegrationModule.nudgeEpsilon, 
            0
        ),
        new Vector2f(
            -PositionIntegrationModule.nudgeEpsilon, 
            -PositionIntegrationModule.nudgeEpsilon
        ),
        new Vector2f(
            0, 
            -PositionIntegrationModule.nudgeEpsilon
        ),
        new Vector2f(
            PositionIntegrationModule.nudgeEpsilon, 
            -PositionIntegrationModule.nudgeEpsilon
        ),
    };

    private final IPlayerModuleProvider provider;

    public PositionIntegrationModule(IPlayerModuleProvider provider) {
        this.provider = provider;
    }

    private final Vector3i isCollidingBlockPosition = new Vector3i();
    private final TerrainBlockData isCollidingBlockData = new TerrainBlockData();
    private final AABBf isCollidingCollider = new AABBf();
    private final AABBi isCollidingColliderRange = new AABBi();

    private boolean isColliding() {
        var room = World.instance.getRoom();
        var kinematicsModule = this.provider.getKinematicsStateModule();
        var collider = kinematicsModule.getCollider(this.isCollidingCollider);
        var collisionRange = AABBiMaths.expandFrom(
            this.isCollidingColliderRange,
            collider
        );

        for(var x = collisionRange.minX; x < collider.maxX; x += 1) {
            for(var y = collisionRange.minY; y < collider.maxY; y += 1) {
                for(var z = collisionRange.minZ; z < collider.maxZ; z += 1) {
                    var blockPosition = this.isCollidingBlockPosition.set(x, y, z);
                    var blockData = room.getBlockData(
                        blockPosition,
                        this.isCollidingBlockData
                    );
                    var block = room.getBlockByID(blockData.blockID);
                    var isCollision = block.isCollision(
                        blockPosition, 
                        blockData
                    );

                    if(isCollision) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final AABBf isThresholdCrossedPlayerCollider = new AABBf();
    private final AABBf isThresholdCrossedDoorCollider = new AABBf();
    private final Vector3f isThresholdCrossedPlanePosition = new Vector3f();
    private final Vector3f isThresholdCrossedHeadPosition = new Vector3f();

    // Has the actual camera (single point), crossed the plane of the teleportation portal
    private boolean isThresholdCrossed() {
        var kinematicsStateModule = this.provider.getKinematicsStateModule();
        var openDoor = World.instance.getOpenDoor();

        if(openDoor == null) {
            return false;
        }

        var collider = kinematicsStateModule.getCollider(this.isThresholdCrossedPlayerCollider);
        var doorCollider = openDoor.getCollider(this.isThresholdCrossedDoorCollider);
        AABBfMaths.shrink(doorCollider, PositionIntegrationModule.portalColliderShrinkAmount);

        if(!doorCollider.intersectsAABB(collider)) {
            return false;
        }

        var planePosition = openDoor.getPlanePosition(this.isThresholdCrossedPlanePosition);
        var headPosition = kinematicsStateModule.getHeadPosition(this.isThresholdCrossedHeadPosition);

        var relativeDistance = headPosition.sub(planePosition);
        var doorNormal = openDoor.getOrientation().cubeFace.normal;
        return Vector3fMaths.dot(relativeDistance, doorNormal) < 0;
    }

    // Perform a teleportation of the player from one portal to another.
    // N.B. its crucial to ALWAYS keep the door/level pairs in sync. If you want to
    // be cheeky and just modify the current level and door, working out the linked
    // level and door later - you'll run into problems because until that work is done
    // the linked door and current door will be the same - screwing up teleportation
    // transformations. 
    private Vector3f teleportHeadPosition = new Vector3f();
    private Vector3f teleportPlanePosition = new Vector3f();
    private Vector3f teleportTargetPlanePosition = new Vector3f();
    private PortalTransformer teleportPortalTransformer = new PortalTransformer();
    private DoorLinkData teleportDoorLinkData = new DoorLinkData();

    private void teleport() {
        var kinematicsStateModule = this.provider.getKinematicsStateModule();

        var headPosition = kinematicsStateModule
            .getHeadPosition(this.teleportHeadPosition)
            .sub(kinematicsStateModule.position);
        
        var openDoor = World.instance.getOpenDoor();
        var linkData = openDoor.resolveLink(this.teleportDoorLinkData);

        // Okay, now lets transform our position, velocity and rotation.
        // N.B. we need to  make sure the rotation happens about the head position,
        // which is the position + the origin - thus add the origin pre-teleport and
        // subtract it post-teleport.
        var transformer = this.teleportPortalTransformer.set(
            openDoor.getPlanePosition(this.teleportPlanePosition),
            openDoor.getOrientation().yaw,
            linkData.targetDoor.getPlanePosition(this.teleportTargetPlanePosition),
            linkData.targetDoor.getOrientation().yaw
        );

        kinematicsStateModule.position.add(headPosition);
        transformer.getTransformedPosition(kinematicsStateModule.position);
        transformer.getTransformedRotation(kinematicsStateModule.rotation);
        transformer.getTransformedVelocity(kinematicsStateModule.velocity);
        kinematicsStateModule.position.sub(headPosition);
        kinematicsStateModule.resetLaggedHeight();
        World.instance.teleport();
    }

    private KinematicsStateSnapShot updateCrouchingSnapShot = new KinematicsStateSnapShot();

    private void updateCrouching() {
        var playerState = this.provider.getKinematicsStateModule();

        // Take a snapshot of the player's core state before any mutation - so we can revert if necessary.
        var snapShot = playerState.getSnapShot(this.updateCrouchingSnapShot);

        // Apply the player's desired crouching state - N.B. this will affect the player's dimensions.
        playerState.isCrouching = Window.instance.isButtonDown(Button.keyLeftCtrl);

        // Bummer! By changing our crouch state we've caused a collision with the terrain.
        // Revert the state and abort.
        if(this.isColliding()) {
            playerState.restoreFromSnapShot(snapShot);
            return;
        }
    }

    private Vector3f updatePositionDeltaStep = new Vector3f();
    private Vector3f updatePositionAdjustmentVector = new Vector3f();
    private KinematicsStateSnapShot updatePositionSnapShot = new KinematicsStateSnapShot();

    private void updatePosition() {
        var playerState = this.provider.getKinematicsStateModule();

        // If we take tiny steps of length epsilon, how many steps do we need to trace the
        // entire length of the velocity vector?
        var velocityMagnitude = playerState.velocity.distance(Vector3fMaths.zero);
        var numSteps = (int) Math.ceil(velocityMagnitude / stepEpsilon);

        // Initially assume we're not colliding with anything.
        playerState.clearContacts();

        // Start walking along the velocity vector in tiny steps.
        for(var ix = 0; ix < numSteps; ix += 1) {
            // We don't want walls to be sticky - i.e. if we glance them we don't
            // want to come to a complete stop. To accomplish this, we further break down
            // each tiny delta step into its component parts along each axis.

            // If we detect a collision along a specific axis, we can zero the velocity
            // *ONLY* in that axis. This will allow us to continue moving along the other
            // axes.
            for(var jx = 0; jx < CubeFace.getSize(); jx += 1) {
                var cubeFace = CubeFace.get(jx);
                // Create a vector to represent a "step". It will be the velocity vector scaled down
                // to have magnitude epsilon.
                var deltaStep = this.updatePositionDeltaStep
                    .set(playerState.velocity)
                    .div(numSteps);

                // We split the delta step into axis by iterating through cube faces. There
                // are 6 cube faces, but 3 axes. Thus, in order to not double count, we
                // exclude the cube faces pointing in the opposite direction to travel.
                var dot = Vector3fMaths.dot(deltaStep, cubeFace.normal);
                if(dot <= 0) {
                    continue;
                }

                // Get the magnitude of the step along the current axis.
                var deltaAxis = deltaStep.get(cubeFace.axisID);

                // Take a snapshot of our state before any mutation - so we can revert if necessary.
                // All the captured variables below have the possibility of being mutated
                // as a result of our movement!
                var snapShot = playerState.getSnapShot(this.updatePositionSnapShot);

                // Assume we have collided unless told otherwise.
                var isCollision = true;

                // Due to floating point inaccuracies, (exacerbated by the fact that we can
                // teleport large distances around the map, we might find that we suddenly
                // ever-so-slightly collide with the terrain. To overcome this, before we
                // resign ourselves to having colldied, we first try adjusting the position
                // by nudging the player in various directions orthogonal to the direction
                // of travel. The first nudge vector is (0, 0) - i.e. no nudge, if this fails
                // we then revert to non-zero nudges.
                for(var kx = 0; kx < PositionIntegrationModule.nudgeVectors.length; kx += 1) {
                    var nudgeVector = PositionIntegrationModule.nudgeVectors[kx];
                    // Construct the "adjustmentVector", which is the combination of the
                    // *component* of the deltaStep and whatever nudge we're applying.
                    for(var nudgeIndex = 0; nudgeIndex < 2; nudgeIndex += 1) {
                        // We want to nudge in directions orthogonal to the direction of travel.
                        // If the current axis is Q, where Q is (0, 1, 2), then orthogonal axes
                        // would be Q + 1 and Q + 2 (modulo 3).
                        var adjustmentAxis = (cubeFace.axisID + nudgeIndex + 1) % 3;
                        var nudgeAxisValue = Vector2fMaths.getAxisValue(nudgeVector, nudgeIndex);

                        this.updatePositionAdjustmentVector
                            .setComponent(adjustmentAxis, nudgeAxisValue);
                    }

                    // We've now set every axis of the adjustmentVector - except for the
                    // axis of travel - we do this now. This axis is necessarily *not* either of
                    // the adjustment axes that were just set.
                    var adjustmentVector = this.updatePositionAdjustmentVector
                        .setComponent(cubeFace.axisID, deltaAxis);

                    // Apply the adjustment vector to the player's position - A.K.A. actually
                    // make the player move!
                    playerState.position.add(adjustmentVector);

                    // If we've collided, then revert our mutation and try again with
                    // another nudge vector.
                    if(this.isColliding()) {
                        playerState.restoreFromSnapShot(snapShot);
                        continue;
                    }

                    // If we've made it this far, we know for certain that this component of the delta step 
                    // (plus any nudging) is safe. Note this and abort - we have no need to examine other potential
                    // nudges.
                    isCollision = false;
                    break;
                }

                // If we encounter a half-block, we should climb it as if it were a step. We do this by performing a similar operations to above,
                // progressively nudging the player upwards until we find a non-colliding position. However, unlike above, the amount we nudge
                // can be up to the height of a half block!
                if(isCollision && cubeFace.axisID != CubeFace.YAxis) {
                    for(var climbHeight = 0f; climbHeight < PositionIntegrationModule.climbHeight; climbHeight += PositionIntegrationModule.climbEpsilon) {
                        var adjustmentVector = this.updatePositionAdjustmentVector
                            .zero()
                            .setComponent(cubeFace.axisID, deltaAxis)
                            .add(0, climbHeight, 0);
                        playerState.position.add(adjustmentVector);

                        if(this.isColliding()) {
                            playerState.restoreFromSnapShot(snapShot);
                            continue;
                        }

                        isCollision = false;
                        break;
                    }
                }


                if(this.isThresholdCrossed()) {
                    this.teleport();
                }

                // We reach this point by either finding a safe path forward with/without a nudge and exiting the loop early,
                // or we exhaust all possible nudges and still can't find a way to move without colliding.
                // If we ultimately do collide, then we need to zero the velocity in the axis of travel.
                // Also don't forget to zero the delta step - I don't think this is strictly necessary but it will save
                // a bunch of compute time as the dot product will be zero and the axis will be skipped entirely for future steps.
                // Finally, tag that we've touched the ground if we've collided when moving vertically downwards.
                if(isCollision) {
                    playerState.velocity.setComponent(cubeFace.axisID, 0);
                    playerState.setInContact(cubeFace, true);
                }
            }
        }
    }

    public void update() {
        this.updateCrouching();
        this.updatePosition();
    }
    
}
