package doors.ui.component.table;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.texture.MenuTexture;
import doors.io.Mouse;
import doors.ui.component.BackgroundComponent;
import doors.ui.component.IComponent;
import doors.ui.component.layout.box.BoxComponent;
import doors.ui.component.layout.box.FixedDimension;
import doors.ui.component.layout.box.GrowDimension;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;

public class TableScrollBarComponent<T extends IComponent> implements IComponent {

    private static int SCROLL_BAR_WIDTH = 16;

    private int scrollAmount;
    private Vector2in originCursor = new Vector2in();
    private Vector2in dimensionsCursor = new Vector2in();
    private TableScrollBoxComponent scrollBoxComponent = new TableScrollBoxComponent();

    private BackgroundComponent scrollBarBackgroundComponent;
    private TableState<T> tableState;

    public TableScrollBarComponent(TableState<T> tableState) {
        this.tableState = tableState;

        var scrollBarSpaceComponent = new BoxComponent(
            null,
            new FixedDimension(SCROLL_BAR_WIDTH),
            new GrowDimension()
        );

        this.scrollBarBackgroundComponent = new BackgroundComponent(
            scrollBarSpaceComponent, MenuTexture.MENU_TEXTURE.windowBackground
        );
    }

    @Override
    public Vector2in getDimensions() {
        return this.scrollBarBackgroundComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.scrollBarBackgroundComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.scrollBarBackgroundComponent.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.scrollBarBackgroundComponent.adjustDimensions(availableSpace);

        var scrollBarHeight = this.scrollBarBackgroundComponent.getDimensions().y;
        this.dimensionsCursor.set(SCROLL_BAR_WIDTH, scrollBarHeight);

        var useScroll = this.tableState.rows.size() > this.tableState.numVisibleRows;
        this.scrollBoxComponent.isDisabled = !useScroll;
        if(useScroll) {
            this.dimensionsCursor.y *= this.tableState.numVisibleRows;
            this.dimensionsCursor.y /= this.tableState.rows.size();
        }

        this.scrollBoxComponent.adjustDimensions(this.dimensionsCursor);
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.scrollBarBackgroundComponent.calculatePosition(origin);

        this.originCursor.set(origin).add(0, this.scrollAmount);
        this.scrollBoxComponent.calculatePosition(this.originCursor);
    }

    @Override
    public void update(UIRoot root) {
        this.scrollBarBackgroundComponent.update(root);
        this.scrollBoxComponent.update(root);

        if(root.draggedComponent != this.scrollBoxComponent) {
            return;
        }

        var mousePosition = Mouse.MOUSE.position;
        var scrollRange = this.getDimensions().y - this.scrollBoxComponent.getDimensions().y;
        var scrollBoxStart = this.scrollBoxComponent.getPosition().y;
        var scrollBoxStop = scrollBoxStart + this.scrollBoxComponent.getDimensions().y;

        if(mousePosition.y >= scrollBoxStart && mousePosition.y <= scrollBoxStop) {
            this.scrollAmount += mousePosition.y - this.scrollBoxComponent.lastMousePosition.y;
        }

        this.scrollAmount = Math.max(0, Math.min(this.scrollAmount, scrollRange));
        this.scrollBoxComponent.lastMousePosition.set(mousePosition);

        this.tableState.setScrollFactor((float)this.scrollAmount / scrollRange);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.scrollBarBackgroundComponent.writeComponentToSpriteBatch(spriteBatch);
        this.scrollBoxComponent.writeComponentToSpriteBatch(spriteBatch);
    }

}
    