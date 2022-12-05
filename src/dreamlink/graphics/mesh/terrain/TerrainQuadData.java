package dreamlink.graphics.mesh.terrain;

import org.joml.Vector3f;
import org.joml.Vector3fc;

import dreamlink.graphics.mesh.strategy.texture.ITextureQuad;
import dreamlink.graphics.texture.sample.TextureSample;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Orientation;
import dreamlink.world.room.module.terrain.TerrainLight;

public class TerrainQuadData implements ITextureQuad {

    public final Vector3f position = new Vector3f();
    public final Vector3f fulcrum = new Vector3f();
    public final Vector3f dimensions = new Vector3f();
    private final int[] lightLevels = new int[TerrainLight.getSize()];

    public CubeFace cubeFace;
    public Orientation orientation;
    public TextureSample textureSample;
    public boolean isHidden;
    public boolean isAffectedByLight;
    public int transformerIndex;

    public TerrainQuadData set(
        Vector3fc position,
        Vector3fc fulcrum,
        Vector3fc dimensions,
        CubeFace cubeFace,
        Orientation orientation,
        TextureSample textureSample,
        boolean isHidden,
        boolean isAffectedByLight,
        int transformerIndex
    ) {
        this.position.set(position);
        this.fulcrum.set(fulcrum);
        this.dimensions.set(dimensions);
        this.cubeFace = cubeFace;
        this.orientation = orientation;
        this.textureSample = textureSample;
        this.isHidden = isHidden;
        this.isAffectedByLight = isAffectedByLight;
        this.transformerIndex = transformerIndex;
        return this;
    }

    public int getLight(TerrainLight lightType) {
        return this.lightLevels[lightType.getIndex()];
    }

    public TerrainQuadData setLight(TerrainLight lightType, int value) {
        this.lightLevels[lightType.getIndex()] = value;
        return this;
    }

    public TerrainQuadData clearLight() {
        for(var ix = 0; ix < this.lightLevels.length; ix += 1) {
            this.lightLevels[ix] = 0;
        }
        return this;
    }

    @Override
    public TextureSample getTextureSample() {
        return this.textureSample;
    }

    @Override
    public int getWindingOffset() {
        return this.cubeFace.axisID == CubeFace.YAxis
            ? this.orientation.getIndex()
            : 0;
    }
    
}
