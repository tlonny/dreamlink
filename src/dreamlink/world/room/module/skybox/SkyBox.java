package dreamlink.world.room.module.skybox;

import org.joml.Vector3f;
import org.joml.Vector3fc;

import dreamlink.graphics.mesh.terrain.TerrainMesh;
import dreamlink.graphics.mesh.terrain.TerrainMeshBuffer;
import dreamlink.graphics.mesh.terrain.TerrainQuadData;
import dreamlink.graphics.texture.sample.TextureSample;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Orientation;
import dreamlink.utility.maths.Vector3fMaths;

public class SkyBox {

    private final static Vector3fc bounds = new Vector3f(
        10_000f, 10_000f, 10_000f
    );

    private final TerrainMesh skyBoxMesh = new TerrainMesh();
    private final TextureSample[] textureSamples;

    public SkyBox() {
        this.textureSamples = new TextureSample[CubeFace.getSize()];
    }

    public void setSkyBoxTextureSample(CubeFace face, TextureSample textureSample) {
        this.textureSamples[face.getIndex()] = textureSample;
    }

    public void setup() {
        this.skyBoxMesh.setup();
        var meshBuffer = TerrainMeshBuffer.instance.clear();
        var quadData = new TerrainQuadData();
        var position = new Vector3f(bounds).mul(-0.5f);
        for(var ix = 0; ix < CubeFace.getSize(); ix += 1) {
            var cubeFace = CubeFace.get(ix);
            var textureSample = this.textureSamples[ix];
            if(textureSample == null) {
                continue;
            }
            meshBuffer.addQuad(quadData.set(
                position,
                Vector3fMaths.zero,
                SkyBox.bounds,
                cubeFace,
                Orientation.front,
                textureSample,
                false,
                true,
                0
            ));
        }

        this.skyBoxMesh.buffer(meshBuffer);
    }

    public void render() {
        this.skyBoxMesh.render();
    }

    public void destroy() {
        this.skyBoxMesh.destroy();
    }
    
}
