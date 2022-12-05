package dreamlink.overlay.component.list.drag;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.border.dialog.DialogBorderComponent;
import dreamlink.overlay.component.border.dialog.DialogState;
import dreamlink.overlay.component.border.dialog.StaticDialogBorderComponentProvider;
import dreamlink.overlay.component.list.ListRowData;
import dreamlink.overlay.component.list.view.IListViewComponentProvider;
import dreamlink.overlay.component.list.view.ListViewComponent;
import dreamlink.overlay.component.list.view.ListViewRowData;
import dreamlink.overlay.component.scroll.IScrollBarComponentProvider;
import dreamlink.overlay.component.scroll.ScrollBarComponent;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.eventspan.EventSpanHandler;
import dreamlink.overlay.eventspan.IEvent;
import dreamlink.overlay.eventspan.IEventSpan;
import dreamlink.overlay.eventspan.IEventSpanRegistry;
import dreamlink.overlay.eventspan.event.DragEndEvent;
import dreamlink.overlay.eventspan.event.DragStartEvent;
import dreamlink.overlay.eventspan.event.PressEndEvent;
import dreamlink.overlay.eventspan.event.PressStartEvent;
import dreamlink.utility.maths.FloatMaths;
import dreamlink.utility.maths.IntMaths;
import dreamlink.window.Window;

public class ListDragComponent extends WrapperComponent implements IEventSpan {

    private class InternalScrollBarComponentProvider implements IScrollBarComponentProvider {

        @Override
        public float getScrollFactor() {
            return ListDragComponent.this.scrollFactor;
        }

        @Override
        public float getScrollHandleHeightFactor() {
            return FloatMaths.clamp(
                ListDragComponent.this.listViewComponent.getVisibleRowCount() / (float) ListDragComponent.this.provider.getRowCount(),
                ListDragComponent.minScrollHandleHeightFactor, 
                1f
            );
        }

        @Override
        public boolean isScrollBarDisabled() {
            return ListDragComponent.this.provider.getRowCount() <= ListDragComponent.this.listViewComponent.getVisibleRowCount();
        }

        @Override
        public void onChange(float factor) {
            var maxIndex = ListDragComponent.this.provider.getRowCount() - ListDragComponent.this.listViewComponent.getVisibleRowCount();
            var selectedIndex = (int)(factor * maxIndex);
            ListDragComponent.this.scrollFactor = (float)selectedIndex / maxIndex;
        }
    }

    private class InternalListComponentProvider implements IListViewComponentProvider {

        @Override
        public int getRowOffset() {
            return ListDragComponent.this.getRowOffset();
        }

        private final ListRowData getRowDataRowData = new ListRowData();

        @Override
        public void setRowData(int index, ListViewRowData data) {
            if(index >= ListDragComponent.this.provider.getRowCount()) {
                data.clear();
                return;
            }

            ListDragComponent.this.provider.setRowData(index, this.getRowDataRowData);
            data.set(
                this.getRowDataRowData.icon,
                this.getRowDataRowData.text,
                index == ListDragComponent.this.pressedIndex
            );
        }
    }

    private final static int padding = 2;
    private final static float minScrollHandleHeightFactor = 0.1f;
    private final static int dragDistanceThreshold = 25;
    private final static Vector2ic dragSpriteDimensions = new Vector2i(32);

    private final DialogBorderComponent component;
    private final ScrollBarComponent scrollBarComponent;
    private final IListDragComponentProvider provider;
    private final ListViewComponent listViewComponent;
    private final EventSpanHandler handler;
    private float scrollFactor;
    private int pressedIndex;
    private int draggedIndex;

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    @Override
    public void computeDimensions(Vector2ic availableSpace) {
        super.computeDimensions(availableSpace);
        var listDimensions = this.listViewComponent.getDimensions(new Vector2i());
        var scrollDimensions = this.scrollBarComponent.getDimensions(new Vector2i());
        var adjustedSpace = this.component.getDimensions(new Vector2i());
        adjustedSpace.y -= scrollDimensions.y - listDimensions.y;
        super.computeDimensions(adjustedSpace);
    }

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {
        registry.register(this);
        super.registerEventSpans(registry);

    }

    private int getRowOffset() {
        var maxIndex = this.provider.getRowCount() - this.listViewComponent.getVisibleRowCount();
        return (int)(ListDragComponent.this.scrollFactor * maxIndex);
    }

    public ListDragComponent(IListDragComponentProvider provider) {
        this.provider = provider;

        this.scrollBarComponent = new ScrollBarComponent(
            new InternalScrollBarComponentProvider()
        );

        this.listViewComponent = new ListViewComponent(
            new InternalListComponentProvider()
        );

        this.component = new DialogBorderComponent(
            new StaticDialogBorderComponentProvider(DialogState.focused),
            new PaddingComponent(
                new HorizontalSpanComponent(Alignment.start, 0)
                    .addComponent(this.listViewComponent)
                    .addComponent(this.scrollBarComponent),
                ListDragComponent.padding
            )
        );

        this.handler = new EventSpanHandler();
        this.draggedIndex = -1;
        this.pressedIndex = -1;
    }

    private final Vector2i onEventMousePosition = new Vector2i();
    private final Vector2i onEventViewPosition = new Vector2i();

    @Override
    public void onEvent(IEvent event) {
        this.handler.onEvent(event);
        var mousePosition = Window.instance.getMousePosition(this.onEventMousePosition);
        var viewPosition = this.listViewComponent.getPosition(this.onEventViewPosition);
        var deltaHeight = mousePosition.y - viewPosition.y;
        var hoveredIndex = Math.max(deltaHeight / ListViewComponent.rowHeight, 0) + this.getRowOffset();

        if(event instanceof PressStartEvent) {
            this.draggedIndex = hoveredIndex;
            this.pressedIndex = hoveredIndex;
        } else if(event instanceof PressEndEvent) {
            this.pressedIndex = -1;
        } else if(event instanceof DragStartEvent dragStartEvent) {
            var rowCount = this.provider.getRowCount();
            if(IntMaths.inRange(rowCount, this.draggedIndex)) {
                var payload = this.provider.getDragPayload(this.draggedIndex);
                dragStartEvent.draggedPayload = payload;
            }
        } else if(event instanceof DragEndEvent) {
            this.draggedIndex = -1;
        }
    }

    @Override
    public int getDragDistanceThreshold() {
        return ListDragComponent.dragDistanceThreshold;
    }

    private final Vector2i writeToSpriteBatchSpritePosition = new Vector2i();
    private final ListRowData writeToSpriteBatchRowData = new ListRowData();

    @Override
    public void writeToSpriteBatch(SpriteBatch spriteBatch) {
        super.writeToSpriteBatch(spriteBatch);

        var range = this.provider.getRowCount();
        if(!this.handler.isDragged() || !IntMaths.inRange(range, this.draggedIndex)) {
            return;
        }

        var spritePosition = Window.instance.getMousePosition(this.writeToSpriteBatchSpritePosition);
        spritePosition.sub(
            ListDragComponent.dragSpriteDimensions.x() / 2,
            ListDragComponent.dragSpriteDimensions.y() / 2
        );

        this.provider.setRowData(
            this.draggedIndex, 
            this.writeToSpriteBatchRowData
        );

        this.writeToSpriteBatchRowData.icon.writeToSpriteBatch(
            spriteBatch,
            spritePosition,
            ListDragComponent.dragSpriteDimensions,
            SpriteHeight.cursorPayload
        );
    }
    
}
