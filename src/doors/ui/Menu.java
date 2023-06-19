package doors.ui;

import java.util.Arrays;
import org.joml.Vector2i;
import doors.Config;
import doors.gamestate.UIGameState;
import doors.io.Mouse;
import doors.io.Window;
import doors.overlay.SystemTextureAtlas;
import doors.utility.Color;

public class Menu {

    public static Menu MENU = new Menu();

    private IElement root;
    public boolean active;

    public Menu() {
        var quitText = new TextLineElement("Quit", false, Color.TEXT);
        var quitButton = new ButtonElement(quitText, () -> Window.WINDOW.setShouldClose());
        var grid = new VerticalSpanElement(Arrays.asList(
            new PaddingElement(new BlockPickerElement(), 4),
            new PaddingElement(new SpriteElement(SystemTextureAtlas.AT, new Vector2i(20,20), Color.WHITE), 4)
        ), false);
        this.root = new BorderElement(
                new PaddingElement(
                    new VerticalSpanElement(Arrays.asList(quitButton, grid), true),
                    10
                ), Color.BUTTON_ACTIVE
        );
                
    }

    public void render() {
        if(!UIGameState.MENU_GAME_STATE.isUsed()) {
            return;
        }

        var dimensions = this.root.getDimensions();
        var centeredPosition = new Vector2i(
            Config.RESOLUTION.x / 2 - dimensions.x / 2,
            Config.RESOLUTION.y / 2 - dimensions.y / 2
        );

        this.root.render(centeredPosition);
        Mouse.MOUSE.setCursorVisibility(true);
    }

}
