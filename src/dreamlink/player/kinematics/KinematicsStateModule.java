package dreamlink.player.kinematics;

import org.joml.AABBf;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import dreamlink.utility.maths.CubeFace;

public class KinematicsStateModule {

    private static final Vector3fc standingDimensions = new Vector3f(0.5f, 1.8f, 0.5f);
    private static final Vector3fc crouchingDimensions = new Vector3f(0.5f, 0.9f, 0.5f);
    private static final Vector3fc originFactor = new Vector3f(0.5f, 0.9f, 0.5f);
    private static final float maxHeightClimb = 0.1f;

    public final Vector3f position = new Vector3f();
    public final Vector3f rotation = new Vector3f();
    public final Vector3f velocity = new Vector3f();

    private float laggedHeight;
    public boolean isCrouching;

    private final boolean[] isInContact = new boolean[CubeFace.getSize()];

    public KinematicsStateSnapShot getSnapShot(KinematicsStateSnapShot snapshot) {
        snapshot.set(
            this.position,
            this.rotation,
            this.velocity,
            this.laggedHeight,
            this.isCrouching
        );

        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);
            snapshot.setIsContact(cubeFace, this.isInContact[cubeFace.getIndex()]);
        }
        return snapshot;
    }

    public void restoreFromSnapShot(KinematicsStateSnapShot snapshot) {
        this.position.set(snapshot.position);
        this.rotation.set(snapshot.rotation);
        this.velocity.set(snapshot.velocity);
        this.isCrouching = snapshot.isCrouching;
        this.laggedHeight = snapshot.laggedHeight;
        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);
            this.isInContact[cubeFace.getIndex()] = snapshot.isContact(cubeFace);
        }
    }

    public void resetLaggedHeight() {
        this.laggedHeight = this.position.y;
    }

    public Vector3fc getDimensions() {
        return this.isCrouching
            ? KinematicsStateModule.crouchingDimensions
            : KinematicsStateModule.standingDimensions;
    }

    public boolean isInContact(CubeFace face) {
        return this.isInContact[face.getIndex()];
    }

    public void setInContact(CubeFace face, boolean value) {
        this.isInContact[face.getIndex()] = value;
    }

    public void clearContacts() {
        for(var ix = 0; ix < this.isInContact.length; ix += 1) {
            this.isInContact[ix] = false;
        }
    }

    public Vector3f getHeadPosition(Vector3f headPosition) {
        return headPosition
            .set(this.getDimensions())
            .mul(KinematicsStateModule.originFactor)
            .add(
                this.position.x,
                this.laggedHeight,
                this.position.z
            );
    }

    public void update() {
        this.laggedHeight = this.laggedHeight + Math.min(
            this.position.y - this.laggedHeight,
            KinematicsStateModule.maxHeightClimb
        );
    }

    public AABBf getCollider(AABBf collider) {
        var dimensions = this.getDimensions();
        return collider.setMin(
            this.position.x, 
            this.position.y, 
            this.position.z
        ).setMax(
            this.position.x + dimensions.x(),
            this.position.y + dimensions.y(),
            this.position.z + dimensions.z()
        );
    }
}
