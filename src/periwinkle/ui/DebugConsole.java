package periwinkle.ui;

import periwinkle.Input;
import periwinkle.graphics.Atlas;

import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;

public class DebugConsole {

    private static int CONSOLE_WIDTH = 80;
    private static int CONSOLE_HEIGHT = 24;

    private static Vector2i GLYPH_DIMENSIONS = new Vector2i(8, 18);
    private static Vector2i PADDING = new Vector2i(10);

    private static Vector2i DIMENSIONS = new Vector2i(
        GLYPH_DIMENSIONS.x * CONSOLE_WIDTH, 
        GLYPH_DIMENSIONS.y * CONSOLE_HEIGHT
    );

    private static Vector2i POSITION = new Vector2i(
        (UI.DIMENSIONS.x - DIMENSIONS.x) / 2,
        (UI.DIMENSIONS.y - DIMENSIONS.y) / 2
    );

    public static DebugConsole DEBUG_CONSOLE = new DebugConsole();

    public final String[] history = new String[CONSOLE_HEIGHT];
    private long lineCount = CONSOLE_HEIGHT;

    private StringBuilder inputBuilder = new StringBuilder();
    private String input = "";

    public boolean active;
    private boolean blinkCursor;

    public DebugConsole() {
        for(var ix = 0; ix < this.history.length; ix += 1)
            this.history[ix] = "";
    }

    public void addLineToHistory(String text) {
        var lineIx = (int)(this.lineCount % CONSOLE_HEIGHT);
        this.history[lineIx] = text;
        this.lineCount += 1;
    }

    private String consumeInput() {
        var str = this.inputBuilder.toString();
        this.inputBuilder.setLength(0);
        this.input = this.inputBuilder.toString();
        return str;
    }

    private void addNewCharacterToInput(char c) {
        if(this.inputBuilder.length() >= CONSOLE_WIDTH)
            return;
        this.inputBuilder.append(c);
        this.input = this.inputBuilder.toString();
    }

    private void removeLastCharacterFromInput() {
        var len = this.inputBuilder.length();
        if(len > 0)
            this.inputBuilder.setLength(len - 1);
        this.input = this.inputBuilder.toString();
    }

    public void update(long stepCount) {

        this.blinkCursor = (stepCount % 10) < 5;

        if(Input.INPUT.isKeyPressed(GLFW.GLFW_KEY_HOME)) {
            this.active = !this.active;
            this.consumeInput();
        }

        if(Input.INPUT.isKeyPressed(GLFW.GLFW_KEY_ENTER)) {
            var line = this.consumeInput();
            this.addLineToHistory(line);
            var split = line.split(" ");
            var command = Command.COMMAND_LOOKUP.get(split[0]);
            if(command != null)
                command.behaviour.run(split);
        }

        if(Input.INPUT.isKeyRepeated(GLFW.GLFW_KEY_BACKSPACE))
            this.removeLastCharacterFromInput();
    }

    public void render() {

        if(!this.active)
            return;

        for(var character : Input.INPUT.inputCapture)
            this.addNewCharacterToInput(character);


        UI.UI.drawBox(
            new Vector2i(POSITION).sub(PADDING),
            new Vector2i(PADDING).mul(2).add(DIMENSIONS)
        );

        var positionBuffer = new Vector2i(POSITION).add(0, DIMENSIONS.y);
        positionBuffer.y -= GLYPH_DIMENSIONS.y;
        UI.UI.drawText(positionBuffer, this.input);
        if(this.blinkCursor) {
            positionBuffer.x += this.input.length() * GLYPH_DIMENSIONS.x;
            UI.UI.drawSprite(positionBuffer, GLYPH_DIMENSIONS, Atlas.UI_ATLAS.getSprite("SYM_CURSOR"));
        }
        positionBuffer.x = POSITION.x;

        for(var y = 0; y < CONSOLE_HEIGHT - 1; y += 1) {
            positionBuffer.y -= GLYPH_DIMENSIONS.y;
            var lineIx = (int)((this.lineCount - y -1) % CONSOLE_HEIGHT);
            var line = this.history[lineIx];
            UI.UI.drawText(positionBuffer, line);
        }


    }


}
