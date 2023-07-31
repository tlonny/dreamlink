package doors.ui.component.edit;

import doors.graphics.text.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.MenuTexture;
import doors.io.Mouse;
import doors.level.block.Block;
import doors.ui.component.IComponent;
import doors.ui.component.IconComponent;
import doors.ui.component.TextComponent;
import doors.ui.component.layout.RowComponent;
import doors.ui.component.layout.alignment.HorizontalAlignment;
import doors.ui.component.layout.alignment.VerticalAlignment;
import doors.ui.component.layout.box.BoxComponent;
import doors.ui.component.layout.box.GrowDimension;
import doors.ui.cursor.PointerCursor;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class EditBlockTableRowComponent implements IComponent {
    
    private static int SPACING = 4;
    private static Vector2in BLOCK_SIZE = new Vector2in(16);
    private static Vector2in DRAGGED_BLOCK_SIZE = new Vector2in(32);

    public Block block;

    private TextComponent nameComponent;
    private BoxComponent spaceComponent;

    private boolean isDragged;
    private boolean isGrabbed;

    private Vector2in originCursor = new Vector2in();

    public EditBlockTableRowComponent(Block block) {
        this.block = block;

        var layoutComponent = new RowComponent(SPACING);
        var iconComponent = new IconComponent(block.textureSample, BLOCK_SIZE);
        layoutComponent.children.add(iconComponent);
        this.nameComponent = new TextComponent(block.name, FontDecoration.NORMAL, Vector3fl.BLACK);
        layoutComponent.children.add(this.nameComponent);

        this.spaceComponent = new BoxComponent(
            layoutComponent,
            new GrowDimension(),
            new GrowDimension(),
            HorizontalAlignment.LEFT,
            VerticalAlignment.CENTER
        );
    }

    @Override
    public Vector2in getDimensions() {
        return this.spaceComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.spaceComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.spaceComponent.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.spaceComponent.adjustDimensions(availableSpace);
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.spaceComponent.calculatePosition(origin);
    }

    @Override
    public void onDragStart(UIRoot root) {
        root.draggedComponent = this;
    }

    @Override
    public void update(UIRoot root) {
        this.spaceComponent.update(root);
        this.isDragged = root.draggedComponent == this;
        this.isGrabbed = root.grabbedComponent == this;

        if(root.hoveredComponent == this) {
            root.selectedCursor = PointerCursor.POINTER_CURSOR;
        }

        this.nameComponent.color.set(this.isGrabbed ? Vector3fl.WHITE : Vector3fl.BLACK);

        root.captureMouse(this, this.getPosition(), this.getDimensions(), 0);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        if(this.isGrabbed) {
            spriteBatch.pushSprite(
                MenuTexture.MENU_TEXTURE.highlight,
                this.getPosition(),
                this.getDimensions(),
                SpriteBatchHeight.UI_NORMAL,
                Vector3fl.WHITE
            );
        }

        this.spaceComponent.writeComponentToSpriteBatch(spriteBatch);

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
