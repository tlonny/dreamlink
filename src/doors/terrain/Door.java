package doors.terrain;

import org.joml.Vector3f;

import doors.Camera;
import doors.Game;
import doors.spatial.IHasDimensions;
import doors.spatial.IHasPosition;
import doors.spatial.IHasRotation;
import doors.utility.CubeFace;
import doors.utility.Maths;

public class Door implements IHasPosition, IHasDimensions {

    // Remember that the portal can rotate by 90 degrees, so its important
    // that the X and Z dimensions are the same so that the dimensions are valid
    // invariant of the rotation.
    public static Vector3f TELEPORTER_DIMENSIONS = new Vector3f(1.0f, 2.0f, 1.0f);

    public Terrain terrain;
    public String targetTerrain;
    public String targetDoor;

    public CubeFace orientation;
    public Vector3f position;

    private static Vector3f previousCameraPosition = new Vector3f();

    public Door(Terrain terrain, String targetTerrain, String targetDoor, Vector3f position, CubeFace orientation) {
        this.terrain = terrain;
        this.targetTerrain = targetTerrain;
        this.targetDoor = targetDoor;

        this.position = position;
        this.orientation = orientation;
    }

    public <T extends IHasPosition & IHasRotation> void mutate(T body) {
        var position = body.getPosition();
        var rotation = body.getRotation();
        var otherTerrain = Terrain.TERRAIN_LOOKUP.get(this.targetTerrain);
        var otherDoor = otherTerrain.doors.get(this.targetDoor);

        position.sub(this.position);
        position.rotateY(-this.orientation.rotation.y);
        position.rotateY(otherDoor.orientation.rotation.y);
        position.rotateY((float)Math.PI);
        position.add(otherDoor.position);

        rotation.y -= this.orientation.rotation.y;
        rotation.y += otherDoor.orientation.rotation.y;
        rotation.y += (float)Math.PI;
    }

    public void simulate() {
        var relativeCamera = new Vector3f(Camera.CAMERA.position).sub(this.position);
        var dot = relativeCamera.dot(this.orientation.normalF);

        var relativeCameraPrevious = new Vector3f(previousCameraPosition).sub(this.position);
        var previousDot = relativeCameraPrevious.dot(this.orientation.normalF);

        if(Math.signum(dot) != Math.signum(previousDot) && Maths.isCollision(this, Camera.CAMERA)) {
            var otherTerrain = Terrain.TERRAIN_LOOKUP.get(this.targetTerrain);
            this.mutate(Camera.CAMERA);
            Game.GAME.currentTerrain = otherTerrain;
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
