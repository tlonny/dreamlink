package doors.state;

import java.util.Arrays;

import org.lwjgl.opengl.GL42;

import doors.Config;
import doors.graphics.rendertarget.PhysicalRenderTarget;
import doors.io.Window;
import doors.overlay.SpriteBatch;
import doors.overlay.ui.UITextureAtlas;
import doors.overlay.ui.element.AlignmentElement;
import doors.overlay.ui.element.ContainerElement;
import doors.overlay.ui.element.IUIElement;
import doors.overlay.ui.element.MenuButtonElement;
import doors.overlay.ui.element.MenuTitleElement;
import doors.overlay.ui.element.VerticalSpanElement;
import doors.overlay.ui.element.WindowElement;
import doors.perspective.FlatPerspective;
import doors.utility.geometry.Vector2in;

public class MainMenuRootGameState extends GameState {


    public static MainMenuRootGameState MAIN_MENU_ROOT_GAME_STATE = new MainMenuRootGameState();

    private IUIElement root;

    public void setup() {
        // We need to do this in setup to stop a circular references to other game states
        this.root = new ContainerElement(this.buildWindow());
    }

    private IUIElement buildWindow() {
        return new WindowElement(
            new VerticalSpanElement(Arrays.asList(
                new AlignmentElement(new MenuTitleElement(UITextureAtlas.ICON_LOGO, "Doors")),
                new AlignmentElement(new MenuButtonElement(UITextureAtlas.ICON_EXPLORE, "Explore", MainMenuExploreGameState.MAIN_MENU_EXPLORE_GAME_STATE::use)),
                new AlignmentElement(new MenuButtonElement(UITextureAtlas.ICON_EDITOR, "Editor", () -> {})),
                new AlignmentElement(new MenuButtonElement(UITextureAtlas.ICON_SETTINGS, "Settings", () -> {})),
                new AlignmentElement(new MenuButtonElement(UITextureAtlas.ICON_HELP, "Help", () -> {})),
                new AlignmentElement(new MenuButtonElement(UITextureAtlas.ICON_QUIT, "Quit", Window.WINDOW::setShouldClose))
            ), 0)
        );
    }

    @Override
    public void update() {
        this.root.setDimensions();
        var origin = new Vector2in(Config.RESOLUTION).sub(this.root.getDimensions()).div(2);
        this.root.setPosition(origin);
        this.root.update();
        this.root.writeElement();

        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.useRenderTarget();
        GL42.glDisable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
        FlatPerspective.FLAT_PERSPECTIVE.apply();

        SpriteBatch.SPRITE_BATCH.updateMesh();
        SpriteBatch.SPRITE_BATCH.render();
    }
}
