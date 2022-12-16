package periwinkle.overlay;

import periwinkle.graphics.Atlas;
import periwinkle.graphics.Mesh;
import periwinkle.Input;
import periwinkle.graphics.MeshBuffer;
import periwinkle.graphics.Shader;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class DebugConsole {

    private static char DEFAULT = '\0';
    private static int CONSOLE_WIDTH = 40;
    private static int CONSOLE_HEIGHT = 10;
    private static int NUM_GLYPHS = 5000;
    private static Vector2f GLYPH_DIMENSIONS = new Vector2f(8, 18);

    public static DebugConsole DEBUG_CONSOLE = new DebugConsole();
    public static void init() {
        DEBUG_CONSOLE.setup();
    }

    public final String[] history = new String[CONSOLE_HEIGHT];
    private long lineCount = CONSOLE_HEIGHT;

    private MeshBuffer spriteBuffer = new MeshBuffer(NUM_GLYPHS);
    private Mesh consoleMesh = new Mesh(Atlas.GLYPH_ATLAS);

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

        if(Input.INPUT.isKeyPressed(GLFW.GLFW_KEY_HOME)) {
            this.active = !this.active;
            this.consumeInput();
            return;
        }

        if(!this.active)
            return;

        for(var character : Input.INPUT.inputCapture)
            this.addNewCharacterToInput(character);

        if(Input.INPUT.isKeyRepeated(GLFW.GLFW_KEY_BACKSPACE))
            this.removeLastCharacterFromInput();

        if(Input.INPUT.isKeyPressed(GLFW.GLFW_KEY_ENTER)) {
            var line = this.consumeInput();
            this.addLineToHistory(line);
            var split = line.split(" ");
            var command = Command.COMMAND_LOOKUP.get(split[0]);
            if(command != null)
                command.behaviour.run(split);
        }

        this.blinkCursor = (stepCount % 10) < 5;
    }

    private void renderLine(String text, Vector2f position) {
        for(var x = 0; x < CONSOLE_WIDTH; x += 1) {
            var character = x < text.length() ? text.charAt(x) : DEFAULT;
            var glyph = Glyph.GLYPH_CHARACTER_LOOKUP.getOrDefault(character, Glyph.SYM_SPACE);
            var glyphPosition = new Vector2f(x, 0).mul(GLYPH_DIMENSIONS).add(position);
            this.spriteBuffer.pushSprite(glyphPosition, GLYPH_DIMENSIONS, glyph.sprite);
        }
    }

    public void render() {
        if(!this.active)
            return;
        this.spriteBuffer.clear();
        for(var y = 0; y < CONSOLE_HEIGHT; y += 1) {
            var lineIx = (int)((this.lineCount - y -1) % CONSOLE_HEIGHT);
            var line = this.history[lineIx];
            this.renderLine(line, new Vector2f(0, CONSOLE_HEIGHT - y - 1).mul(GLYPH_DIMENSIONS).add(10, 10));
        }
        var blinkingInput = this.blinkCursor ? this.input + "_" : this.input;
        this.renderLine(blinkingInput, new Vector2f(0, CONSOLE_HEIGHT).mul(GLYPH_DIMENSIONS).add(10, 15));
        this.spriteBuffer.flip();
        this.consoleMesh.loadMesh(this.spriteBuffer);
        Shader.SHADER.setModelMatrix(new Matrix4f().identity());
        Shader.SHADER.setGlobalColor(new Vector3f(1f, 1f, 1f));
        this.consoleMesh.render(new Vector3f(), new Vector3f(1f, 1f, 1f), 0f);
    }

    public void setup() {
        this.consoleMesh.setup();
    }


}
