package periwinkle.ui;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

import periwinkle.graphics.Atlas;
import periwinkle.graphics.Mesh;
import periwinkle.graphics.MeshBuffer;
import periwinkle.graphics.Sprite;

public class UI {

    private static int NUM_QUADS = 5_000;

    private static int SCALE_FACTOR = 2;
    public static Vector2i DIMENSIONS = new Vector2i(1280, 720);

    private static Vector3f ZERO = new Vector3f();
    private static Vector3f WHITE = new Vector3f(1f, 1f, 1f);
    private static Vector2i GLYPH_SIZE = new Vector2i(8,16);

    public static UI UI = new UI();
    public static void init() {
        UI.setup();
    }

    private MeshBuffer meshBuffer = new MeshBuffer(NUM_QUADS);
    private Mesh mesh = new Mesh(Atlas.UI_ATLAS);
    private Vector2i cellSize = new Vector2i(16,16);

    private Sprite outerUISolid;
    private Sprite outerUITop;
    private Sprite outerUIBottom;
    private Sprite outerUILeft;
    private Sprite outerUIRight;
    private Sprite outerUITopLeft;
    private Sprite outerUITopRight;
    private Sprite outerUIBottomLeft;
    private Sprite outerUIBottomRight;

    public void setup() {
        this.mesh.setup();

        this.outerUISolid = Atlas.UI_ATLAS.getSprite("OUTER_UI_SOLID");
        this.outerUITop = Atlas.UI_ATLAS.getSprite("OUTER_UI_TOP");
        this.outerUIBottom = Atlas.UI_ATLAS.getSprite("OUTER_UI_BOTTOM");
        this.outerUILeft = Atlas.UI_ATLAS.getSprite("OUTER_UI_LEFT");
        this.outerUIRight = Atlas.UI_ATLAS.getSprite("OUTER_UI_RIGHT");
        this.outerUITopLeft = Atlas.UI_ATLAS.getSprite("OUTER_UI_TOP_LEFT");
        this.outerUITopRight = Atlas.UI_ATLAS.getSprite("OUTER_UI_TOP_RIGHT");
        this.outerUIBottomLeft = Atlas.UI_ATLAS.getSprite("OUTER_UI_BOTTOM_LEFT");
        this.outerUIBottomRight = Atlas.UI_ATLAS.getSprite("OUTER_UI_BOTTOM_RIGHT");
    }

    public void drawSprite(Vector2i position, Vector2i dimensions, Sprite sprite) {
        this.meshBuffer.pushSprite(
            new Vector2f(position).mul(SCALE_FACTOR),
            new Vector2f(dimensions).mul(SCALE_FACTOR),
            sprite
        );
    }

    public void drawText(Vector2i position, String text) {
        var positionBuffer = new Vector2i(position); 
        for(var ix = 0; ix < text.length(); ix += 1) {
            var character = text.charAt(ix);
            this.drawSprite(
                positionBuffer,
                GLYPH_SIZE,
                Glyph.GLYPH_CHARACTER_LOOKUP.getOrDefault(character, Glyph.SYM_SPACE).sprite
            );
            positionBuffer.x += GLYPH_SIZE.x;
        }
    }

    public void drawBox(Vector2i position, Vector2i dimensions) {
        var positionCursor = new Vector2i();
        var dimensionsCursor = new Vector2i();

        this.drawSprite(
            positionCursor.set(position).add(-this.cellSize.x, -this.cellSize.y), 
            dimensionsCursor.set(this.cellSize),
            this.outerUITopLeft
        );

        this.drawSprite(
            positionCursor.set(position).add(dimensions.x, -this.cellSize.y), 
            dimensionsCursor.set(this.cellSize),
            this.outerUITopRight
        );

        this.drawSprite(
            positionCursor.set(position).add(-this.cellSize.x, dimensions.y), 
            dimensionsCursor.set(this.cellSize),
            this.outerUIBottomLeft
        );

        this.drawSprite(
            positionCursor.set(position).add(dimensions.x, dimensions.y), 
            dimensionsCursor.set(this.cellSize),
            this.outerUIBottomRight
        );

        this.drawSprite(
            positionCursor.set(position).add(-this.cellSize.x, 0),
            dimensionsCursor.set(this.cellSize.x, dimensions.y),
            this.outerUILeft
        );

        this.drawSprite(
            positionCursor.set(position).add(dimensions.x, 0),
            dimensionsCursor.set(this.cellSize.x, dimensions.y),
            this.outerUIRight
        );

        this.drawSprite(
            positionCursor.set(position).add(0, -this.cellSize.y),
            dimensionsCursor.set(dimensions.x, this.cellSize.y),
            this.outerUITop
        );

        this.drawSprite(
            positionCursor.set(position).add(0, dimensions.y),
            dimensionsCursor.set(dimensions.x, this.cellSize.y),
            this.outerUIBottom
        );

        this.drawSprite(
            positionCursor.set(position), 
            dimensionsCursor.set(dimensions), 
            this.outerUISolid
        );


    }

    public void render() {
        this.meshBuffer.flip();
        this.mesh.loadMesh(this.meshBuffer);
        this.meshBuffer.clear();
        this.mesh.render(ZERO, WHITE, 0f);
    }

}
