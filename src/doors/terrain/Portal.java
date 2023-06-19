package doors.terrain;

import org.joml.Vector3f;

import doors.Camera;
import doors.Game;
import doors.component.IHasDimensions;
import doors.component.IHasPosition;
import doors.component.IHasRotation;
import doors.utility.CubeFace;
import doors.utility.Maths;

public class Portal implements IHasPosition, IHasDimensions {

    // Remember that the portal can rotate by 90 degrees, so its important
    // that the X and Z dimensions are the same so that the dimensions are valid
    // invariant of the rotation.
    public static Vector3f TELEPORTER_DIMENSIONS = new Vector3f(2.0f, 2.0f, 1.0f);

    public Door door;
    public Portal targetPortal;
    public CubeFace orientation;
    public Vector3f position;

    private static Vector3f previousCameraPosition = new Vector3f();

    public Portal(Door door, Vector3f position, CubeFace orientation) {
        this.door = door;
        this.position = position;
        this.orientation = orientation;
    }

    public <T extends IHasPosition & IHasRotation> void mutate(T body) {
        var position = body.getPosition();
        var rotation = body.getRotation();

        position.sub(this.position);
        position.rotateY(-this.orientation.rotation.y);
        position.rotateY(this.targetPortal.orientation.rotation.y);
        position.rotateY((float)Math.PI);
        position.add(this.targetPortal.position);

        rotation.y -= this.orientation.rotation.y;
        rotation.y += this.targetPortal.orientation.rotation.y;
        rotation.y += (float)Math.PI;
    }

    public void simulate() {
        var relativeCamera = new Vector3f(Camera.CAMERA.position).sub(this.position);
        var dot = relativeCamera.dot(this.orientation.normalF);

        var relativeCameraPrevious = new Vector3f(previousCameraPosition).sub(this.position);
        var previousDot = relativeCameraPrevious.dot(this.orientation.normalF);

        if(Math.signum(dot) != Math.signum(previousDot) && Maths.isCollision(this, Camera.CAMERA)) {
            this.mutate(Camera.CAMERA);
            Game.GAME.currentTerrain = this.targetPortal.door.terrain;
        } 

        previousCameraPosition.set(Camera.CAMERA.position);
    }

    @Override
    public Vector3f getDimensions() {
        return TELEPORTER_DIMENSIONS;
    }

    @Override
    public Vector3f getPosition() {
        return this.position;
    }
}
