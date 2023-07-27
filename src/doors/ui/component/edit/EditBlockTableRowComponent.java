package doors.ui.component.edit;

import doors.graphics.text.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.MenuTexture;
import doors.io.Mouse;
import doors.level.block.Block;
import doors.ui.component.IExplicitDimensions;
import doors.ui.component.TextureComponent;
import doors.ui.component.TextComponent;
import doors.ui.component.layout.HorizontalSpanComponent;
import doors.ui.cursor.PointerCursor;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class EditBlockTableRowComponent implements IExplicitDimensions {
    
    private static int SPACING = 4;
    private static Vector2in BLOCK_SIZE = new Vector2in(16);
    private static Vector2in DRAGGED_BLOCK_SIZE = new Vector2in(32);

    public Block block;

    private HorizontalSpanComponent layoutComponent = new HorizontalSpanComponent(SPACING);
    private TextureComponent blockComponent;
    private TextComponent nameComponent;

    private boolean isDragged;
    private boolean isGrabbed;

    private Vector2in position = new Vector2in();
    private Vector2in originCursor = new Vector2in();
    private Vector2in dimensions = new Vector2in();

    public EditBlockTableRowComponent(Block block) {
        this.block = block;
        this.blockComponent = new TextureComponent(block.textureSample, BLOCK_SIZE);
        this.nameComponent = new TextComponent(block.name, FontDecoration.NORMAL, Vector3fl.BLACK);

        this.layoutComponent.components.add(this.blockComponent);
        this.layoutComponent.components.add(this.nameComponent);
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void setDimensions(int width, int height) {
        this.dimensions.set(width, height);
    }

    @Override
    public void calculateDimensions() {
        this.layoutComponent.calculateDimensions();
    }

    @Override
    public void onDragStart(UIRoot root) {
        root.draggedComponent = this;
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);
        this.originCursor.set(origin);
        this.originCursor.y += this.dimensions.y / 2 - this.layoutComponent.getDimensions().y / 2;
        this.layoutComponent.update(this.originCursor, root);
        this.isDragged = root.draggedComponent == this;
        this.isGrabbed = root.grabbedComponent == this;

        if(root.hoveredComponent == this) {
            root.selectedCursor = PointerCursor.POINTER_CURSOR;
        }

        this.nameComponent.color.set(this.isGrabbed ? Vector3fl.WHITE : Vector3fl.BLACK);

        root.captureMouse(this, this.position, this.dimensions, 0);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        if(this.isGrabbed) {
            spriteBatch.pushSprite(
                MenuTexture.MENU_TEXTURE.highlight,
                this.position,
                this.dimensions,
                SpriteBatchHeight.UI_NORMAL,
                Vector3fl.WHITE
            );
        }

        this.layoutComponent.writeComponentToSpriteBatch(spriteBatch);

        if(this.isDragged) {
            this.originCursor
                .set(DRAGGED_BLOCK_SIZE)
                .div(2)
                .mul(-1)
                .add(Mouse.MOUSE.position);

            spriteBatch.pushSprite(
                this.block.textureSample,
                this.originCursor,
                DRAGGED_BLOCK_SIZE,
                SpriteBatchHeight.UI_CURSOR_PAYLOAD,
                Vector3fl.WHITE
            );
        }
    }
    
}
