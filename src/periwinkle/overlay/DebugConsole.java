package periwinkle.overlay;

import periwinkle.Game;
import periwinkle.graphics.Mesh;
import periwinkle.graphics.MeshBuffer;
import periwinkle.io.ButtonLatch;
import periwinkle.utility.Maths;

import org.joml.Vector2i;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class DebugConsole {

    private static int CONSOLE_WIDTH = 80;
    private static int NUM_HISTORY = 23;
    private static int CONSOLE_HEIGHT = NUM_HISTORY + 1;
    private static int MAX_QUADS = 5_000;

    private static Vector3f CONSOLE_COLOR = Maths.getColor(204, 191, 169);
    private static Vector3f TEXT_COLOR = Maths.getColor(102, 91, 73);

    private static Vector3f POSITION_ZERO = new Vector3f();
    private static Vector2i GLYPH_DIMENSIONS = new Vector2i(16, 32);
    private static Vector2i PADDING = new Vector2i(10, 10);

    private static Vector2i DIMENSIONS = new Vector2i(
        GLYPH_DIMENSIONS.x * CONSOLE_WIDTH, 
        GLYPH_DIMENSIONS.y * CONSOLE_HEIGHT
    );

    public boolean active;
    private boolean isDirty;

    private ButtonLatch toggleLatch = new ButtonLatch();
    private ButtonLatch submitLatch = new ButtonLatch();
    private ButtonLatch deleteLatch = new ButtonLatch();
    private ButtonLatch historyUpLatch = new ButtonLatch();
    private ButtonLatch historyDownLatch = new ButtonLatch();

    private String[] history = new String[CONSOLE_HEIGHT - 1];
    private long lineCount = CONSOLE_HEIGHT;
    private StringBuilder inputBuilder = new StringBuilder();
    private int historyCursor = 0;
    private boolean blinkCursor = true;

    private MeshBuffer meshBuffer = new MeshBuffer(MAX_QUADS);
    private Mesh mesh = new Mesh(Game.OVERLAY_ATLAS);
    private SpriteElement spriteBuffer = new SpriteElement();
    private BoxElement boxBuffer = new BoxElement();
    private TextElement textBuffer = new TextElement();

    private Vector2i basePosition = new Vector2i(
        (Game.DISPLAY.dimensions.x - DIMENSIONS.x) / 2,
        (Game.DISPLAY.dimensions.y - DIMENSIONS.y) / 2
    );

    public DebugConsole() {
        for(var ix = 0; ix < this.history.length; ix += 1)
            this.history[ix] = "";
    }

    public void setup() {
        this.mesh.setup();
        this.updateMesh();
    }

    public void addLineToHistory(String text) {
    }

    public void simulate() {
        if(Game.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_HOME, this.toggleLatch)) {
            this.active ^= true;
        }

        Game.PLAYER.playerControlSystem.active = !this.active;

        if(!this.active) {
            this.submitLatch.reset();
            this.deleteLatch.reset();
            this.historyUpLatch.reset();
            return;
        }

        var newBlink = (System.currentTimeMillis() % 1000) < 500;
        this.isDirty |= newBlink != this.blinkCursor;
        this.blinkCursor = newBlink;

        while(!Game.TYPED_CHARACTER_STREAM.characterQueue.isEmpty()) {
            if(this.inputBuilder.length() < CONSOLE_WIDTH) {
                var character = Game.TYPED_CHARACTER_STREAM.characterQueue.remove();
                this.inputBuilder.append(character);
                this.isDirty = true;
            }
        }

        if(Game.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_ENTER, this.submitLatch)) {
            var lineIx = (int)(this.lineCount % NUM_HISTORY);
            var line = this.inputBuilder.toString();
            this.history[lineIx] = line;
            this.inputBuilder.setLength(0);
            this.lineCount += 1;

            var split = line.split(" ");
            var command = Command.COMMAND_LOOKUP.get(split[0]);
            if(command != null) {
                command.behaviour.run(split);
            }

            this.historyCursor = -1;
            this.isDirty = true;
        }

        if(Game.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_UP, this.historyUpLatch)) {
            if(this.historyCursor < NUM_HISTORY - 1) {
                this.inputBuilder.setLength(0);
                this.historyCursor += 1;
                var lineIx = (int)((this.lineCount - 1 - this.historyCursor) % NUM_HISTORY);
                this.inputBuilder.append(this.history[lineIx]);
                this.isDirty = true;
            }
        }

        if(Game.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_DOWN, this.historyDownLatch)) {
            if(this.historyCursor > 0) {
                this.inputBuilder.setLength(0);
                this.historyCursor -= 1;
                var lineIx = (int)((this.lineCount - 1 - this.historyCursor) % NUM_HISTORY);
                this.inputBuilder.append(this.history[lineIx]);
                this.isDirty = true;
            }
        }

        if(Game.KEYBOARD.isKeyPressed(GLFW.GLFW_KEY_BACKSPACE, this.deleteLatch)) {
            this.inputBuilder.setLength(Math.max(this.inputBuilder.length() - 1, 0));
            this.isDirty = true;
        }

        this.updateMesh();
    }

    public void updateMesh() {
        if(!this.isDirty) {
            return;
        }

        this.meshBuffer.clear();
        this.boxBuffer.color = CONSOLE_COLOR;
        this.boxBuffer.position.set(this.basePosition).sub(PADDING);
        this.boxBuffer.dimensions.set(PADDING).mul(2).add(DIMENSIONS);
        this.boxBuffer.batch(this.meshBuffer);

        this.textBuffer.color = TEXT_COLOR;
        this.textBuffer.position.set(this.basePosition);
        this.textBuffer.text = this.inputBuilder.toString();
        this.textBuffer.batch(this.meshBuffer);

        if(this.blinkCursor) {
            this.spriteBuffer.color = TEXT_COLOR;
            this.spriteBuffer.texture = Game.OVERLAY_ATLAS.solid;
            this.spriteBuffer.dimensions = GLYPH_DIMENSIONS;
            this.spriteBuffer.position.set(this.basePosition);
            this.spriteBuffer.position.x += GLYPH_DIMENSIONS.x * this.inputBuilder.length();
            this.spriteBuffer.batch(this.meshBuffer);
        }

        for(var y = 0; y < NUM_HISTORY; y += 1) {
            this.textBuffer.position.y += GLYPH_DIMENSIONS.y;
            var lineIx = (int)((this.lineCount - y - 1) % NUM_HISTORY);
            var line = this.history[lineIx];
            this.textBuffer.text = line;
            this.textBuffer.batch(this.meshBuffer);
        }
    
        this.meshBuffer.flip();
        this.mesh.loadFromBuffer(this.meshBuffer);
        this.isDirty = false;
    }

    public void render() {
        if(this.active) {
            this.mesh.render(POSITION_ZERO);
        }
    }

}
