package doors.state;

import org.lwjgl.opengl.GL42;

import doors.core.graphics.mesh.Mesh;
import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.rendertarget.PhysicalRenderTarget;
import doors.core.graphics.shader.Shader;
import doors.core.io.Mouse;
import doors.core.ui.AbstractUIRoot;
import doors.core.ui.Cursor;
import doors.ui.cursor.ArrowCursor;
import doors.ui.root.MainMenuRoot;

public class MainMenuGameState extends AbstractGameState {

    private static int SPRITE_BATCH_QUADS = 1_000;
    public static MainMenuGameState MAIN_MENU_GAME_STATE = new MainMenuGameState();

    private AbstractUIRoot mainMenuRoot = new MainMenuRoot();
    private MeshBuffer spriteBatch = new MeshBuffer(SPRITE_BATCH_QUADS);
    private Mesh mesh = new Mesh();

    public void setup() {
        this.mesh.setup();
    }

    @Override
    public void use() {
        super.use();
        Mouse.MOUSE.unlockMouse();
    }

    @Override
    public void update() {
        this.spriteBatch.clear();
        ArrowCursor.ARROW_CURSOR.use();
        this.mainMenuRoot.update();
        this.mainMenuRoot.writeUIRoot(this.spriteBatch);
        Cursor.USED_CURSOR.writeCursor(this.spriteBatch);
        this.mesh.loadDataFromMeshBuffer(this.spriteBatch);

        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.useRenderTarget();
        GL42.glDisable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
        Shader.SHADER.setFlatViewMatrices();

        this.mesh.render();
    }
}
