package dreamlink.world.room.module.lightpropagation;

import org.joml.Vector3i;
import org.joml.Vector3ic;

import dreamlink.world.room.module.terrain.TerrainLight;

public class LightPropagation {

    public final Vector3i position = new Vector3i();
    private final boolean[] isPropagating;

    public LightPropagation() {
        this.isPropagating = new boolean[TerrainLight.getSize()];
    }

    public void setIsPropagating(TerrainLight light, boolean isPropagating) {
        this.isPropagating[light.getIndex()] = isPropagating;
    }

    public boolean getIsPropagating(TerrainLight light) {
        return this.isPropagating[light.getIndex()];
    }

    public LightPropagation set(Vector3ic position) {
        for (var ix = 0; ix < this.isPropagating.length; ix += 1) {
            this.isPropagating[ix] = true;
        }
        this.position.set(position);
        return this;
    }

    public LightPropagation set(LightPropagation other) {
        for (var ix = 0; ix < this.isPropagating.length; ix += 1) {
            this.isPropagating[ix] = other.isPropagating[ix];
        }
        this.position.set(other.position);
        return this;
    }
    
}
