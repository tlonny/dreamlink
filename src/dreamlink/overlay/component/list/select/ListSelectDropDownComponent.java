package dreamlink.overlay.component.list.select;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.background.BackgroundComponent;
import dreamlink.overlay.component.background.StaticBackgroundComponentProvider;
import dreamlink.overlay.component.border.dialog.DialogBorderComponent;
import dreamlink.overlay.component.border.dialog.DialogState;
import dreamlink.overlay.component.border.dialog.StaticDialogBorderComponentProvider;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.StaticButtonComponentProvider;
import dreamlink.overlay.component.icon.IIconComponentProvider;
import dreamlink.overlay.component.icon.IconComponent;
import dreamlink.overlay.component.icon.StaticIconComponentProvider;
import dreamlink.overlay.component.list.ListRowData;
import dreamlink.overlay.component.list.view.IListViewComponentProvider;
import dreamlink.overlay.component.list.view.ListViewComponent;
import dreamlink.overlay.component.list.view.ListViewRowData;
import dreamlink.overlay.component.scroll.IScrollBarComponentProvider;
import dreamlink.overlay.component.scroll.ScrollBarComponent;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.text.CharacterData;
import dreamlink.overlay.component.text.line.view.ITextLineViewComponentProvider;
import dreamlink.overlay.component.text.line.view.TextLineViewComponent;
import dreamlink.overlay.eventspan.IEvent;
import dreamlink.overlay.eventspan.IEventSpan;
import dreamlink.overlay.eventspan.IEventSpanRegistry;
import dreamlink.overlay.eventspan.event.HoverEvent;
import dreamlink.overlay.eventspan.event.PressStartEvent;
import dreamlink.utility.maths.FloatMaths;
import dreamlink.utility.maths.IntMaths;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.window.Window;

public class ListSelectDropDownComponent implements IComponent {

    private class InternalScrollBarComponentProvider implements IScrollBarComponentProvider {

        @Override
        public float getScrollFactor() {
            return ListSelectDropDownComponent.this.scrollFactor;
        }

        @Override
        public float getScrollHandleHeightFactor() {
            return FloatMaths.clamp(
                ListSelectDropDownComponent.this.floatingComponent.listViewComponent.getVisibleRowCount() / (float) ListSelectDropDownComponent.this.provider.getRowCount(),
                ListSelectDropDownComponent.minScrollHandleHeightFactor, 
                1f
            );
        }

        @Override
        public boolean isScrollBarDisabled() {
            return ListSelectDropDownComponent.this.provider.getRowCount() <= ListSelectDropDownComponent.this.floatingComponent.listViewComponent.getVisibleRowCount();
        }

        @Override
        public void onChange(float factor) {
            var maxIndex = ListSelectDropDownComponent.this.provider.getRowCount() - ListSelectDropDownComponent.this.floatingComponent.listViewComponent.getVisibleRowCount();
            var selectedIndex = (int)(factor * maxIndex);
            ListSelectDropDownComponent.this.scrollFactor = (float)selectedIndex / maxIndex;
        }
    }
    
    private class InternalSelectIconComponentProvider implements IIconComponentProvider {

        private final ListRowData getSpriteRowData = new ListRowData();

        @Override
        public ISpriteTemplate getSprite() {
            var selectedIndex = ListSelectDropDownComponent.this.provider.getSelectedRowIndex();
            if(!IntMaths.inRange(ListSelectDropDownComponent.this.provider.getRowCount(), selectedIndex)) {
                return null;
            }

            var rowData = this.getSpriteRowData;
            ListSelectDropDownComponent.this.provider.setRowData(selectedIndex, rowData);
            return rowData.icon;
        }

    }

    private class InternalSelectTextComponentProvider implements ITextLineViewComponentProvider {

        private final ListRowData setCharacterStateRowData = new ListRowData();

        @Override
        public void setCharacterState(int characterIndex, CharacterData characterState) {
            var selectedIndex = ListSelectDropDownComponent.this.provider.getSelectedRowIndex();
            if(!IntMaths.inRange(ListSelectDropDownComponent.this.provider.getRowCount(), selectedIndex)) {
                characterState.clear();
                return;
            }

            var rowData = this.setCharacterStateRowData;
            ListSelectDropDownComponent.this.provider.setRowData(selectedIndex, rowData);

            characterState.set(
                rowData.text.charAt(characterIndex),
                FontDecoration.normal,
                ListSelectDropDownComponent.this.isOpen 
                    ? Vector4fMaths.white 
                    : Vector4fMaths.black
            );
        }

        @Override
        public int getCharacterOffset() {
            return 0;
        }

        @Override
        public int getCharacterSize() {
            var selectedIndex = ListSelectDropDownComponent.this.provider.getSelectedRowIndex();
            if(!IntMaths.inRange(ListSelectDropDownComponent.this.provider.getRowCount(), selectedIndex)) {
                return 0;
            }
            var rowData = this.setCharacterStateRowData;
            ListSelectDropDownComponent.this.provider.setRowData(selectedIndex, rowData);
            return rowData.text.length();
        }

    }

    private class InternalListComponentProvider implements IListViewComponentProvider {

        @Override
        public int getRowOffset() {
            var maxIndex = ListSelectDropDownComponent.this.provider.getRowCount() - ListSelectDropDownComponent.this.floatingComponent.listViewComponent.getVisibleRowCount();
            return (int)(ListSelectDropDownComponent.this.scrollFactor * maxIndex);
        }

        private final ListRowData setRowDataSelectData = new ListRowData();

        @Override
        public void setRowData(int index, ListViewRowData data) {
            if(index >= ListSelectDropDownComponent.this.provider.getRowCount()) {
                data.clear();
                return;
            }

            ListSelectDropDownComponent.this.provider.setRowData(index, this.setRowDataSelectData);
            data.set(
                this.setRowDataSelectData.icon,
                this.setRowDataSelectData.text,
                index == ListSelectDropDownComponent.this.highlightedIndex
            );
        }
    }

    private class InternalCatchEventSpan implements IEventSpan {

        @Override
        public Vector2i getDimensions(Vector2i target) {
            return Window.instance.getResolution(target);
        }

        @Override
        public void onEvent(IEvent event) {
            if(event instanceof PressStartEvent) {
                ListSelectDropDownComponent.this.isOpen = false;
            }
        }

        @Override
        public int getDragDistanceThreshold() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Vector2i getPosition(Vector2i target) {
            return target.zero();
        }

        @Override
        public SpriteHeight getSpriteHeight() {
            return SpriteHeight.menuFloating;
        }
    }

    private class InternalFloatingComponent extends WrapperComponent implements IEventSpan {

        private final ListViewComponent listViewComponent;
        private final ScrollBarComponent scrollBarComponent;
        private final IComponent component;

        public InternalFloatingComponent() {
            this.listViewComponent = new ListViewComponent(new InternalListComponentProvider());
            this.scrollBarComponent = new ScrollBarComponent(new InternalScrollBarComponentProvider());
            this.component = new BackgroundComponent(
                new StaticBackgroundComponentProvider(MenuTextureSample.dialogCenter),
                new PaddingComponent(
                    new HorizontalSpanComponent(Alignment.start, 0)
                        .addComponent(this.listViewComponent)
                        .addComponent(this.scrollBarComponent),
                    ListSelectDropDownComponent.floatingPadding
                )
            );
        }

        private int getRowOffset() {
            var maxIndex = ListSelectDropDownComponent.this.provider.getRowCount() - this.listViewComponent.getVisibleRowCount();
            return (int)(ListSelectDropDownComponent.this.scrollFactor * maxIndex);
        }

        private final Vector2i onEventMousePosition = new Vector2i();
        private final Vector2i onEventViewPosition = new Vector2i();

        @Override
        public void onEvent(IEvent event) {
            var mousePosition = Window.instance.getMousePosition(this.onEventMousePosition);
            var viewPosition = this.listViewComponent.getPosition(this.onEventViewPosition);
            var deltaHeight = mousePosition.y - viewPosition.y;
            var index = Math.max(deltaHeight / ListViewComponent.rowHeight, 0) + this.getRowOffset();

            if(event instanceof PressStartEvent) {
                var selectedIndex = ListSelectDropDownComponent.this.provider.getSelectedRowIndex();
                ListSelectDropDownComponent.this.provider.onChange(index == selectedIndex ? -1 : index);
                ListSelectDropDownComponent.this.isOpen = false;
            } else if(event instanceof HoverEvent) {
                ListSelectDropDownComponent.this.highlightedIndex = index;
            }
        }

        @Override
        public void registerEventSpans(IEventSpanRegistry registry) {
            registry.register(this);
            super.registerEventSpans(registry);
        }

        @Override
        public void computeDimensions(Vector2ic availableSpace) {
            super.computeDimensions(availableSpace);
            var listDimensions = this.listViewComponent.getDimensions(new Vector2i());
            var scrollDimensions = this.scrollBarComponent.getDimensions(new Vector2i());
            var adjustedSpace = new Vector2i(availableSpace);
            adjustedSpace.y -= scrollDimensions.y - listDimensions.y;
            super.computeDimensions(adjustedSpace);
        }

        @Override
        public IComponent getComponent() {
            return this.component;
        }

        @Override
        public int getDragDistanceThreshold() {
            return Integer.MAX_VALUE;
        }

    }

    private final static int padding = 2;
    private final static int floatingPadding = 4;
    private final static int spacing = 5;
    private final static float minScrollHandleHeightFactor = 0.1f;
    private final static Vector2ic iconDimensions = new Vector2i(16, 16);

    private final IListSelectComponentProvider provider;
    private final IComponent component;
    private final InternalFloatingComponent floatingComponent;
    private final InternalCatchEventSpan catchComponent;
    private final int dropDownHeight;

    private final Vector2i position = new Vector2i();
    private SpriteHeight spriteHeight;
    private boolean isOpen;
    private float scrollFactor;
    private int highlightedIndex;

    public ListSelectDropDownComponent(IListSelectComponentProvider provider, int dropDownHeight) {
        this.dropDownHeight = dropDownHeight;
        this.catchComponent = new InternalCatchEventSpan();
        this.provider = provider;
        this.floatingComponent = new InternalFloatingComponent();

        this.component = new DialogBorderComponent(
            new StaticDialogBorderComponentProvider(DialogState.focused),
            new BackgroundComponent(
                this::getBackground,
                new PaddingComponent(
                    new HorizontalSpanComponent(
                        Alignment.center,
                        ListSelectDropDownComponent.spacing
                    ).addComponent(
                        new IconComponent(
                            new InternalSelectIconComponentProvider(),
                            ListSelectDropDownComponent.iconDimensions
                        )
                    ).addComponent(
                        new TextLineViewComponent(
                            new InternalSelectTextComponentProvider(),
                            Alignment.start
                        )
                    ).addComponent(
                        new BoxComponent(
                            new ButtonComponent(
                                new StaticButtonComponentProvider(this::setIsOpen), 
                                new BoxComponent(
                                    new IconComponent(
                                        new StaticIconComponentProvider(MenuTextureSample.dropDownArrow),
                                        MenuTextureSample.dropDownArrow.dimensions
                                    ),
                                    BoxDimension.grow(),
                                    BoxDimension.grow()
                                )
                            ),
                            BoxDimension.fixed(ListSelectDropDownComponent.iconDimensions.x()),
                            BoxDimension.fixed(ListSelectDropDownComponent.iconDimensions.y())
                        )
                    ),
                    ListSelectDropDownComponent.padding
                )
            )
        );
    }

    @Override
    public void computePosition(Vector2ic position, SpriteHeight spriteHeight) {
        this.position.set(position);
        this.spriteHeight = spriteHeight;
        this.component.computePosition(position, spriteHeight);
        var dimensions = this.component.getDimensions(new Vector2i());
        var floatingPosition = new Vector2i(position);
        floatingPosition.y += dimensions.y;
        this.floatingComponent.computePosition(floatingPosition, SpriteHeight.menuFloating);
    }

    private void setIsOpen() {
        this.isOpen = true;
    } 

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {
        this.component.registerEventSpans(registry);
        if(this.isOpen) {
            registry.register(this.catchComponent);
            this.floatingComponent.registerEventSpans(registry);
        }
    }

    @Override
    public Vector2i getInitialDimensions(Vector2i target) {
        return this.component.getInitialDimensions(target);
    }

    @Override
    public Vector2i getDimensions(Vector2i target) {
        return this.component.getDimensions(target);
    }

    @Override
    public void computeInitialDimensions() {
        this.component.computeInitialDimensions();
        this.floatingComponent.computeInitialDimensions();
    }

    @Override
    public void computeDimensions(Vector2ic availableSpace) {
        this.component.computeDimensions(availableSpace);
        var floatingSpace = this.component.getDimensions(new Vector2i());
        floatingSpace.y = this.dropDownHeight;
        this.floatingComponent.computeDimensions(floatingSpace);
    }

    @Override
    public void writeToSpriteBatch(SpriteBatch spriteBatch) {
        this.component.writeToSpriteBatch(spriteBatch);
        if(this.isOpen) {
            this.floatingComponent.writeToSpriteBatch(spriteBatch);
        }
    }

    private ISpriteTemplate getBackground() {
        if(this.isOpen) {
            return MenuTextureSample.blueHighlight;
        } else {
            return MenuTextureSample.dialogCenter;
        }
    }

    @Override
    public Vector2i getPosition(Vector2i target) {
        return target.set(this.position);
    }

    @Override
    public SpriteHeight getSpriteHeight() {
        return this.spriteHeight;
    }
    
}
