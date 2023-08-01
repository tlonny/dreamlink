package doors.level.camera;

import doors.Config;
import doors.io.Mouse;
import doors.utility.CubeFace;
import doors.utility.vector.Vector2fl;
import doors.utility.vector.Vector3fl;

public class Camera {

    private static float PITCH_LIMIT = (float)Math.toRadians(85f);
    private static float MOUSE_SENSITIVITY = 0.9f;

    public Vector3fl position = new Vector3fl();
    public Vector3fl rotation = new Vector3fl();
    public Vector3fl velocity = new Vector3fl();
    public Vector3fl direction = new Vector3fl();
    public Vector2fl mouseDelta = new Vector2fl();

    public ICameraMovementSystem movementSystem;

    public Camera(ICameraMovementSystem movementSystem) {
        this.movementSystem = movementSystem;
    }

    public void update() {
        this.movementSystem.update(this);

        this.mouseDelta
            .set(Mouse.MOUSE.position)
            .div(Config.CONFIG.getResolution())
            .sub(0.5f)
            .mul(MOUSE_SENSITIVITY)
            // in 2D screen space, left and up are negative, right and down are positive.
            // in 3D world space, left and up are positive, right and down are negative. 
            // Thus both axes need to be scaled by -1f.
            .mul(-1f, -1f);

        // Using the right hand rule, stick your thumb up (+ve Y) and notice your fingers curl left.
        // In our coordinate system, left is positive, so we ADD the mouse delta.
        this.rotation.y += this.mouseDelta.x;

        // Using the right hand rule, stick your thumb left (+ve X) and notice your fingers curl down.
        // In our coordinate system, down is negative, so we SUBTRACT the mouse delta.
        this.rotation.x -= this.mouseDelta.y;

        this.rotation.x = Math.min(Math.max(this.rotation.x, - PITCH_LIMIT), PITCH_LIMIT);

        this.rotation.y %= 2 * Math.PI;
        if(this.rotation.y < 0) {
            this.rotation.y += 2 * Math.PI;
        }

        this.direction.set(CubeFace.FRONT.normal);
        this.direction.rotateX(this.rotation.x);
        this.direction.rotateY(this.rotation.y);
    }


}
