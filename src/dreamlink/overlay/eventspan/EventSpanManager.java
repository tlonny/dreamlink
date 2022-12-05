package dreamlink.overlay.eventspan;

import java.util.ArrayList;
import java.util.Comparator;

import org.joml.Rectanglei;
import org.joml.Vector2i;

import dreamlink.window.Window;
import dreamlink.window.button.Button;
import dreamlink.overlay.eventspan.event.ClickEvent;
import dreamlink.overlay.eventspan.event.DragEndEvent;
import dreamlink.overlay.eventspan.event.DragEvent;
import dreamlink.overlay.eventspan.event.DragStartEvent;
import dreamlink.overlay.eventspan.event.DropEvent;
import dreamlink.overlay.eventspan.event.FocusEndEvent;
import dreamlink.overlay.eventspan.event.FocusStartEvent;
import dreamlink.overlay.eventspan.event.HoverEndEvent;
import dreamlink.overlay.eventspan.event.HoverEvent;
import dreamlink.overlay.eventspan.event.HoverStartEvent;
import dreamlink.overlay.eventspan.event.PressEndEvent;
import dreamlink.overlay.eventspan.event.PressEvent;
import dreamlink.overlay.eventspan.event.PressStartEvent;
import dreamlink.utility.maths.RectangleiMaths;

public class EventSpanManager implements IEventSpanRegistry {

    private static final Comparator<IEventSpan> heightComparator = Comparator.comparing(EventSpanManager::getHeight);

    private final ArrayList<IEventSpan> interactiveSpans = new ArrayList<>();
    private final Vector2i dragStartPosition = new Vector2i();

    private IEventSpan focusedSpan;
    private IEventSpan hoveredSpan;
    private IEventSpan pressedSpan;
    private IEventSpan draggedSpan;

    private static int getHeight(IEventSpan span) {
        return span.getSpriteHeight().getHeight();
    }
    
    @Override
    public void register(IEventSpan span) {
        this.interactiveSpans.add(span);
    }

    private ClickEvent updateClickEvent = new ClickEvent();
    private DragEndEvent updateDragEndEvent = new DragEndEvent();
    private DragEvent updateDragEvent = new DragEvent();
    private DragStartEvent updateDragStartEvent = new DragStartEvent();
    private DropEvent updateDropEvent = new DropEvent();
    private HoverEndEvent updateHoverEndEvent = new HoverEndEvent();
    private HoverEvent updateHoverEvent = new HoverEvent();
    private HoverStartEvent updateHoverStartEvent = new HoverStartEvent();
    private PressEndEvent updatePressEndEvent = new PressEndEvent();
    private PressEvent updatePressEvent = new PressEvent();
    private PressStartEvent updatePressStartEvent = new PressStartEvent();
    private FocusStartEvent updateFocusStartEvent = new FocusStartEvent();
    private FocusEndEvent updateFocusEndEvent = new FocusEndEvent();

    private boolean isMouseDown;

    private final Vector2i updateMousePosition = new Vector2i();
    private final Vector2i updatePosition = new Vector2i();
    private final Vector2i updateDimensions = new Vector2i();
    private final Rectanglei updateBounds = new Rectanglei(); 

    public boolean isDraggedSpan() {
        return this.draggedSpan != null;
    }

    public boolean isHoveredSpan() {
        return this.hoveredSpan != null;
    }

    public void clear() {
        this.interactiveSpans.clear();
    }

    public Cursor getCursor() {
        if(this.draggedSpan != null) {
            return Cursor.dragCursor;
        }

        if(this.hoveredSpan != null) {
            return Cursor.pointerCursor;
        }

        return Cursor.arrowCursor;
    }

    public void update() {
        var mousePosition = Window.instance.getMousePosition(this.updateMousePosition);

        IEventSpan hoveredSpan = null;
        this.interactiveSpans.sort(EventSpanManager.heightComparator);

        var isPressed = false;
        var isReleased = false;

        if(Window.instance.isButtonDown(Button.mouseLeft)) {
            if(!this.isMouseDown) {
                isPressed = true;
            }
            this.isMouseDown = true;
        } else {
            if(this.isMouseDown) {
                isReleased = true;
            }
            this.isMouseDown = false;

        }

        for(var ix = this.interactiveSpans.size() - 1; 0 <= ix; ix -= 1) {
            var component = this.interactiveSpans.get(ix);
            var position = component.getPosition(this.updatePosition);
            var dimensions = component.getDimensions(this.updateDimensions);
            var bounds = RectangleiMaths.set(this.updateBounds, position, dimensions);
            if(bounds.containsPoint(mousePosition)) {
                hoveredSpan = component;
                break;
            }
        }

        if(hoveredSpan != this.hoveredSpan) {
            if(this.hoveredSpan != null) {
                this.hoveredSpan.onEvent(this.updateHoverEndEvent);
            }

            if(hoveredSpan != null) {
                hoveredSpan.onEvent(this.updateHoverStartEvent);
            }

            this.hoveredSpan = hoveredSpan;
        }

        if(isPressed) {
            if(this.hoveredSpan != null) {
                this.hoveredSpan.onEvent(this.updatePressStartEvent);
            }

            if(this.hoveredSpan != this.focusedSpan) {
                if(this.focusedSpan != null) {
                    this.focusedSpan.onEvent(this.updateFocusEndEvent);
                }

                if(this.hoveredSpan != null) {
                    this.hoveredSpan.onEvent(this.updateFocusStartEvent);
                }
            }

            this.focusedSpan = this.hoveredSpan;
            this.pressedSpan = this.hoveredSpan;
            this.dragStartPosition.set(mousePosition);

        }

        if(isReleased) {
            if(this.pressedSpan != null) {
                this.pressedSpan.onEvent(this.updatePressEndEvent);
                if(this.pressedSpan == this.hoveredSpan) {
                    this.pressedSpan.onEvent(this.updateClickEvent);
                }
            }

            if(this.draggedSpan != null) {
                this.draggedSpan.onEvent(this.updateDragEndEvent);
                if(this.hoveredSpan != null) {
                    this.updateDropEvent.droppedPayload = this.updateDragStartEvent.draggedPayload;
                    this.hoveredSpan.onEvent(this.updateDropEvent);
                }
            }

            this.draggedSpan = null;
            this.pressedSpan = null;
        }

        if(this.pressedSpan != null) {
            var dragDistance = this.dragStartPosition.distance(mousePosition);
            if(this.pressedSpan.getDragDistanceThreshold() < dragDistance) {
                this.draggedSpan = this.pressedSpan;
                this.updateDragStartEvent.reset();
                this.pressedSpan.onEvent(this.updatePressEndEvent);
                this.draggedSpan.onEvent(this.updateDragStartEvent);
                this.pressedSpan = null;
            }
        }

        if(this.hoveredSpan != null) {
            this.hoveredSpan.onEvent(this.updateHoverEvent);
        }

        if(this.pressedSpan != null) {
            this.pressedSpan.onEvent(this.updatePressEvent);
        }

        if(this.draggedSpan != null) {
            this.draggedSpan.onEvent(this.updateDragEvent);
        }
    }
}
