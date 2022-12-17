package periwinkle.environment;

import periwinkle.Camera;
import periwinkle.component.Entity;
import periwinkle.terrain.World;
import org.joml.Vector2i;
import org.joml.Vector3f;

import java.util.Random;

public class Rain extends Entity {

    private static float RAIN_SPEED = 2f;
    public static float SPAWN_RANGE = 32;
    public static float DE_SPAWN_RANGE = SPAWN_RANGE * 1.5f;

    public Vector3f position;
    public Vector3f previousPosition = new Vector3f();
    public float width;

    public Rain() {
        super();
        var random = new Random();
        var fullRange = SPAWN_RANGE * 2;
        this.position = new Vector3f(
            (random.nextFloat() - 0.5f) * fullRange + Camera.CAMERA.position.x,
            Camera.CAMERA.position.y + SPAWN_RANGE,
            (random.nextFloat() - 0.5f) * fullRange + Camera.CAMERA.position.z
        );
        this.width = 0.3f + random.nextFloat() * 0.3f;
    }

    public void setup() {
        Sky.SKY.rainSet.add(this);
        if(!this.inBounds())
            this.destroy();
    }

    public void tearDown() {
        Sky.SKY.rainSet.remove(this);
    }

    private boolean inBounds() {
        if(this.position.distance(Camera.CAMERA.position) > DE_SPAWN_RANGE)
            return false;
        var height = World.WORLD.getHeight(new Vector2i(
                (int)this.position.x,
                (int)this.position.z
        ));
        if(this.position.y <= height + 1)
            return false;
        return true;
    }

    public void update() {
        this.previousPosition.set(position);
        this.position.y -= RAIN_SPEED;
        if(!this.inBounds())
            this.destroy();
    }

}
