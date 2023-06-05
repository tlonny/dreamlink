package doors;

import org.joml.Vector2i;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import doors.graphics.Mesh;
import doors.graphics.MeshBuffer;
import doors.graphics.RenderTarget;
import doors.overlay.Sprite;

public class Screen {

    public static Vector2i RESOLUTION = new Vector2i(1280, 720);
    private static int NUM_QUADS = 1;

    private Mesh mesh = new Mesh();
    private MeshBuffer meshBuffer = new MeshBuffer(NUM_QUADS);
    private Sprite sprite = new Sprite();
    private RenderTarget renderTarget;

    public Screen() {
        this.renderTarget = new RenderTarget(RESOLUTION);
        this.sprite.textureSample = this.renderTarget.texture.createTextureSample(
            new Vector2i(0, RESOLUTION.y), 
            new Vector2i(RESOLUTION.x, -RESOLUTION.y)
        );
        this.sprite.position.set(0, 0);
        this.sprite.dimensions.set(RESOLUTION);
    }

    public void setup() {
        this.renderTarget.setup();
        this.mesh.setup();
        this.sprite.write(this.meshBuffer);
        this.meshBuffer.flip();
        this.mesh.loadFromMeshBuffer(this.meshBuffer);
        this.renderTarget.bind();
    }


    public void flush() {
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
        GL20.glViewport(0, 0, Game.WINDOW.dimensions.x, Game.WINDOW.dimensions.y);
        GL15.glClear(GL15.GL_COLOR_BUFFER_BIT | GL15.GL_DEPTH_BUFFER_BIT );

        this.renderTarget.texture.bind();
        this.mesh.render();
        this.renderTarget.bind();
    }
}
