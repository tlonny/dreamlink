package dreamlink.overlay.component.list.press;

import org.joml.Vector2i;
import org.joml.Vector2ic;

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
import dreamlink.overlay.eventspan.IEvent;
import dreamlink.overlay.eventspan.IEventSpan;
import dreamlink.overlay.eventspan.IEventSpanRegistry;
import dreamlink.overlay.eventspan.event.HoverEvent;
import dreamlink.overlay.eventspan.event.PressStartEvent;
import dreamlink.utility.maths.FloatMaths;
import dreamlink.window.Window;

public class ListPressComponent extends WrapperComponent implements IEventSpan {

    private class InternalScrollBarComponentProvider implements IScrollBarComponentProvider {

        @Override
        public float getScrollFactor() {
            return ListPressComponent.this.scrollFactor;
        }

        @Override
        public float getScrollHandleHeightFactor() {
            return FloatMaths.clamp(
                ListPressComponent.this.listViewComponent.getVisibleRowCount() / (float) ListPressComponent.this.provider.getRowCount(),
                ListPressComponent.minScrollHandleHeightFactor, 
                1f
            );
        }

        @Override
        public boolean isScrollBarDisabled() {
            return ListPressComponent.this.provider.getRowCount() <= ListPressComponent.this.listViewComponent.getVisibleRowCount();
        }

        @Override
        public void onChange(float factor) {
            var maxIndex = ListPressComponent.this.provider.getRowCount() - ListPressComponent.this.listViewComponent.getVisibleRowCount();
            var selectedIndex = (int)(factor * maxIndex);
            ListPressComponent.this.scrollFactor = (float)selectedIndex / maxIndex;
        }
    }

    private class InternalListComponentProvider implements IListViewComponentProvider {

        @Override
        public int getRowOffset() {
            return ListPressComponent.this.getRowOffset();
        }

        private final ListRowData getRowDataRowData = new ListRowData();

        @Override
        public void setRowData(int index, ListViewRowData data) {
            if(index >= ListPressComponent.this.provider.getRowCount()) {
                data.clear();
                return;
            }

            ListPressComponent.this.provider.setRowData(index, this.getRowDataRowData);
            data.set(
                this.getRowDataRowData.icon,
                this.getRowDataRowData.text,
                index == ListPressComponent.this.hoveredIndex
            );
        }
    }

    private final static int padding = 2;
    private final static float minScrollHandleHeightFactor = 0.1f;

    private final DialogBorderComponent component;
    private final ScrollBarComponent scrollBarComponent;
    private final IListPressComponentProvider provider;
    private final ListViewComponent listViewComponent;
    private float scrollFactor;
    private int hoveredIndex;

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
        return (int)(ListPressComponent.this.scrollFactor * maxIndex);
    }

    public ListPressComponent(IListPressComponentProvider provider) {
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
                ListPressComponent.padding
            )
        );
    }

    private final Vector2i onEventMousePosition = new Vector2i();
    private final Vector2i onEventViewPosition = new Vector2i();

    @Override
    public void onEvent(IEvent event) {
        var mousePosition = Window.instance.getMousePosition(this.onEventMousePosition);
        var viewPosition = this.listViewComponent.getPosition(this.onEventViewPosition);
        var deltaHeight = mousePosition.y - viewPosition.y;
        var hoveredIndex = Math.max(deltaHeight / ListViewComponent.rowHeight, 0) + this.getRowOffset();

        if(event instanceof HoverEvent) {
            this.hoveredIndex = hoveredIndex;
            this.provider.onHover(hoveredIndex);
        } else {
            this.hoveredIndex = -1;
            this.provider.onHover(-1);
        }

        if(event instanceof PressStartEvent) {
            this.provider.onPress(hoveredIndex);
        }
    }

    @Override
    public int getDragDistanceThreshold() {
        return Integer.MAX_VALUE;
    }
    
}
