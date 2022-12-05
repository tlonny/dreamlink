package dreamlink.player.kinematics;

import org.joml.Vector3f;
import org.joml.Vector3fc;

import dreamlink.utility.maths.CubeFace;

public class KinematicsStateSnapShot {

    public Vector3f position;
    public Vector3f rotation;
    public Vector3f velocity;
    public boolean isCrouching;
    public float laggedHeight;
    private boolean[] isContactFaces;

    public KinematicsStateSnapShot() {
        this.position = new Vector3f();
        this.rotation = new Vector3f();
        this.velocity = new Vector3f();
        this.isContactFaces = new boolean[6];
    }

    public KinematicsStateSnapShot set(
        Vector3fc position,
        Vector3fc rotation,
        Vector3fc velocity,
        float laggedHeight,
        boolean isCrouching
    ) {
        this.laggedHeight = laggedHeight;
        this.position.set(position);
        this.rotation.set(rotation);
        this.velocity.set(velocity);
        this.isCrouching = isCrouching;
        return this;
    }

    public void setIsContact(CubeFace cubeFace, boolean value) {
        this.isContactFaces[cubeFace.getIndex()] = value;
    }

    public boolean isContact(CubeFace cubeFace) {
        return this.isContactFaces[cubeFace.getIndex()];
    }

}
