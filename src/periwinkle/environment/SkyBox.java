package periwinkle.environment;

import periwinkle.Game;
import periwinkle.graphics.Atlas;
import periwinkle.graphics.Mesh;
import periwinkle.graphics.MeshBuffer;
import periwinkle.graphics.Texture;
import periwinkle.utility.CubeFace;

import org.joml.Vector2i;
import org.joml.Vector3f;

public class SkyBox {

    private static int SKY_BOX_LENGTH = 8192;
    private static Vector3f SKY_BOX_OFFSET = new Vector3f(SKY_BOX_LENGTH).mul(-0.5f);
    private static float STAR_FIELD_FACTOR = 0.95f;

    private Atlas starFieldAtlas = new Atlas("src/texture/skybox.png", new Vector2i(512, 512));
    private Texture starFieldTexture = this.starFieldAtlas.createTexture();
    private Mesh starFieldMesh = new Mesh(this.starFieldAtlas);

    private Mesh solidSkyMesh = new Mesh(Game.OVERLAY_ATLAS);
    private MeshBuffer meshBuffer = new MeshBuffer(6);

    private void buildStarFieldMesh() {
        this.starFieldAtlas.setup();
        this.starFieldMesh.setup();
        this.meshBuffer.clear();
        for(var cubeFace : CubeFace.CUBE_FACES) {
            for(var ix = 0; ix < cubeFace.vertices.length; ix += 1) {
                var vertex = cubeFace.vertices[ix];
                var inverseNormal = new Vector3f(cubeFace.normal).mul(-1f);
                this.meshBuffer.position.set(vertex).add(inverseNormal).mul(SKY_BOX_LENGTH * STAR_FIELD_FACTOR);
                this.meshBuffer.normal.set(cubeFace.normal);
                this.meshBuffer.textureOffset.set(this.starFieldTexture.vertices[ix]);
                this.meshBuffer.color.set(1f, 1f, 1f);
                this.meshBuffer.localLight = 0f;
                this.meshBuffer.globalLight = 1f;
                this.meshBuffer.push();
            }
        }
        this.meshBuffer.flip();
        this.starFieldMesh.loadFromBuffer(this.meshBuffer);
    }

    private void buildSolidSkyMesh() {
        this.solidSkyMesh.setup();
        this.meshBuffer.clear();
        for(var cubeFace : CubeFace.CUBE_FACES) {
            for(var ix = 0; ix < cubeFace.vertices.length; ix += 1) {
                var vertex = cubeFace.vertices[ix];
                var inverseNormal = new Vector3f(cubeFace.normal).mul(-1f);
                this.meshBuffer.position.set(vertex).add(inverseNormal).mul(SKY_BOX_LENGTH);
                this.meshBuffer.normal.set(cubeFace.normal);
                this.meshBuffer.textureOffset.set(Game.OVERLAY_ATLAS.solid.vertices[ix]);
                this.meshBuffer.color.set(1f, 1f, 1f);
                this.meshBuffer.localLight = 0f;
                this.meshBuffer.globalLight = 1f;
                this.meshBuffer.push();
            }
        }
        this.meshBuffer.flip();
        this.solidSkyMesh.loadFromBuffer(this.meshBuffer);
    }


    public void setup() {
        this.buildSolidSkyMesh();
        this.buildStarFieldMesh();
    }

    public void render() {
        var position = new Vector3f();
        position.set(SKY_BOX_OFFSET).mul(STAR_FIELD_FACTOR).add(Game.PLAYER.position);
        this.starFieldMesh.render(position, Game.SKY_TYPE.starColor, 0f);
        position.set(SKY_BOX_OFFSET).add(Game.PLAYER.position);
        this.solidSkyMesh.render(position, Game.SKY_TYPE.skyColor, 0f);
    }

}
