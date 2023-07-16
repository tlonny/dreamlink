package doors.ui.root;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

import doors.graphics.spritebatch.SpriteBatch;
import doors.io.Mouse;
import doors.ui.component.IComponent;
import doors.ui.cursor.AbstractCursor;
import doors.ui.cursor.ArrowCursor;
import doors.ui.cursor.GrabCursor;
import doors.utility.vector.Vector2in;

public class UIRoot {

    private static int DRAG_THRESHOLD_DISTANCE = 20;

    public Collection<IComponent> rootComponents = new ArrayList<>();

    public IComponent hoveredComponent;
    public IComponent pressedComponent;
    public IComponent grabbedComponent;
    public IComponent focusedComponent;
    public IComponent draggedComponent;

    public AbstractCursor selectedCursor;

    private Vector2in grabStartPosition = new Vector2in();
    private List<ComponentCapture> componentCaptures = new ArrayList<>();
    private Queue<ComponentCapture> unusedComponentCaptures = new ArrayDeque<>();

    public SpriteBatch spriteBatch = new SpriteBatch();

    private ComponentCapture getNewComponentCapture() {
        if(unusedComponentCaptures.isEmpty()) {
            return new ComponentCapture();
        } else {
            return unusedComponentCaptures.poll();
        }
    }

    private void clearComponentCaptures() {
        for(var capture : this.componentCaptures) {
            this.unusedComponentCaptures.add(capture);
        }
        this.componentCaptures.clear();
    }
    
    public void captureMouse(IComponent component, Vector2in position, Vector2in dimensions, int priority) {
        var capture = this.getNewComponentCapture();
        capture.component = component;
        capture.position.set(position);
        capture.dimensions.set(dimensions);
        this.componentCaptures.add(capture);
    }

    private int getComponentCapturePriority(ComponentCapture capture) {
        return capture.capturePriority;
    }

    private IComponent getHoveredComponent() {
        this.componentCaptures.sort(Comparator.comparing(this::getComponentCapturePriority).reversed());
        for(var ix = this.componentCaptures.size() - 1; ix >= 0; ix -= 1) {
            var capture = this.componentCaptures.get(ix);
            if(Mouse.MOUSE.position.isWithinBounds(capture.position, capture.dimensions)) {
                return capture.component;
            }
        }
        return null;
    }

    public void processEvents() {
        this.hoveredComponent = this.getHoveredComponent();

        if(Mouse.MOUSE.isLeftMouseButtonPressed()) {
            if(this.hoveredComponent != null) {
                this.hoveredComponent.onMousePress(this);
            }
            this.pressedComponent = this.hoveredComponent;
            this.grabbedComponent = this.hoveredComponent;
            this.grabStartPosition.set(Mouse.MOUSE.position);
        }

        if(this.grabbedComponent != null) {
            if(this.grabStartPosition.getDistance(Mouse.MOUSE.position) > DRAG_THRESHOLD_DISTANCE) {
                this.grabbedComponent.onDragStart(this);
                this.grabbedComponent = null;
            }
        }

        if(Mouse.MOUSE.isLeftMouseButtonReleased()) {
            if(this.hoveredComponent != null) {
                this.hoveredComponent.onMouseRelease(this);
                if(this.hoveredComponent == this.pressedComponent) {
                    this.hoveredComponent.onMouseClick(this);
                }
            }
            if(this.draggedComponent != null && this.hoveredComponent != null) {
                this.hoveredComponent.onDragDrop(this, this.draggedComponent);
            }
            this.grabbedComponent = null;
            this.draggedComponent = null;
            this.pressedComponent = null;
        }
    }

    public void update() {
        this.selectedCursor = ArrowCursor.ARROW_CURSOR;
        this.clearComponentCaptures();
        for(var rootComponent : this.rootComponents) {
            rootComponent.calculateDimensions();
            rootComponent.update(Vector2in.ZERO, this);
        }

        if(this.draggedComponent != null) {
            this.selectedCursor = GrabCursor.GRAB_CURSOR;
        }

        this.processEvents();
    }

    public void writeUIRoot(SpriteBatch spriteBatch) {
        for(var rootComponent : this.rootComponents) {
            rootComponent.writeUIComponent(spriteBatch);
        }
    }
}
