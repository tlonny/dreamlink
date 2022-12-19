package periwinkle.overlay;

import org.joml.Vector2i;
import org.joml.Vector3f;

import periwinkle.Game;
import periwinkle.graphics.MeshBuffer;

public class BoxElement {

    private static int BORDER_SIZE = 4; 
    private static Vector3f COLOR_WHITE = new Vector3f(1f, 1f, 1f);

    public Vector2i dimensions = new Vector2i();
    public Vector2i position = new Vector2i();
    public Vector3f color = new Vector3f(1f, 1f, 1f);

    private SpriteElement spriteBuffer = new SpriteElement();

    public void batch(MeshBuffer meshBuffer) {
        spriteBuffer.dimensions.set(BORDER_SIZE, BORDER_SIZE);
        spriteBuffer.color.set(COLOR_WHITE);

        spriteBuffer.texture = Game.OVERLAY_ATLAS.borderBottomLeft;
        spriteBuffer.position.set(this.position);
        spriteBuffer.batch(meshBuffer);

        spriteBuffer.texture = Game.OVERLAY_ATLAS.borderBottomRight;
        spriteBuffer.position.set(
            this.position.x + dimensions.x - BORDER_SIZE, 
            this.position.y
        );
        spriteBuffer.batch(meshBuffer);

        spriteBuffer.texture = Game.OVERLAY_ATLAS.borderTopLeft;
        spriteBuffer.position.set(
            this.position.x, 
            this.position.y + dimensions.y - BORDER_SIZE
        );
        spriteBuffer.batch(meshBuffer);

        spriteBuffer.texture = Game.OVERLAY_ATLAS.borderTopRight;
        spriteBuffer.position.set(
            this.position.x + dimensions.x - BORDER_SIZE, 
            this.position.y + dimensions.y - BORDER_SIZE
        );
        spriteBuffer.batch(meshBuffer);

        spriteBuffer.texture = Game.OVERLAY_ATLAS.borderBottom;
        spriteBuffer.position.set(
            this.position.x + BORDER_SIZE, 
            this.position.y
        );
        spriteBuffer.dimensions.set(
            this.dimensions.x - 2 * BORDER_SIZE,
            BORDER_SIZE
        );
        spriteBuffer.batch(meshBuffer);

        spriteBuffer.texture = Game.OVERLAY_ATLAS.borderTop;
        spriteBuffer.position.set(
            this.position.x + BORDER_SIZE, 
            this.position.y + dimensions.y - BORDER_SIZE
        );
        spriteBuffer.dimensions.set(
            this.dimensions.x - 2 * BORDER_SIZE,
            BORDER_SIZE
        );
        spriteBuffer.batch(meshBuffer);

        spriteBuffer.texture = Game.OVERLAY_ATLAS.borderRight;
        spriteBuffer.position.set(
            this.position.x + dimensions.x - BORDER_SIZE,
            this.position.y + BORDER_SIZE
        );
        spriteBuffer.dimensions.set(
            BORDER_SIZE,
            this.dimensions.y - 2 * BORDER_SIZE
        );
        spriteBuffer.batch(meshBuffer);

        spriteBuffer.texture = Game.OVERLAY_ATLAS.borderLeft;
        spriteBuffer.position.set(
            this.position.x,
            this.position.y + BORDER_SIZE
        );
        spriteBuffer.dimensions.set(
            BORDER_SIZE,
            this.dimensions.y - 2 * BORDER_SIZE
        );
        spriteBuffer.batch(meshBuffer);

        spriteBuffer.texture = Game.OVERLAY_ATLAS.solid;
        spriteBuffer.color.set(color);
        spriteBuffer.position.set(
            this.position.x + BORDER_SIZE,
            this.position.y + BORDER_SIZE
        );
        spriteBuffer.dimensions.set(
            this.dimensions.x - 2 * BORDER_SIZE,
            this.dimensions.y - 2 * BORDER_SIZE
        );
        spriteBuffer.batch(meshBuffer);

    }

}
