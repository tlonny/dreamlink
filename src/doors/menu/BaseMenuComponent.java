package doors.menu;

import java.util.ArrayList;
import java.util.Collection;

import doors.io.Mouse;
import doors.utility.vector.Vector2in;

public class BaseMenuComponent {
    
    public Vector2in offset;
    public Vector2in dimensions;
    public Vector2in globalPosition;
    public MenuLayer layer;
    private Collection<BaseMenuComponent> children;

    protected boolean isFocused;
    protected boolean isSelected;
    protected boolean isHovered;

    public BaseMenuComponent() {
        this.offset = new Vector2in();
        this.dimensions = new Vector2in();
        this.globalPosition = new Vector2in();
        this.layer = MenuLayer.NORMAL;
        this.children = new ArrayList<>();
    }

    protected <T extends BaseMenuComponent> T addChild(T child) {
        this.children.add(child);
        return child;
    }

    protected void removeChild(BaseMenuComponent child) {
        this.children.remove(child);
    }

    public void calculateLayout() {
        for(var child : this.children) {
            child.calculateLayout();
        }
    }

    public void writeUIComponent() {
        for(var child : this.children) {
            child.writeUIComponent();
        }
    }

    public void calculatePosition(Vector2in parentPosition) {
        this.globalPosition.set(parentPosition).add(this.offset);
        for (var child : this.children) {
            child.calculatePosition(this.globalPosition);
        }
    }

    private BaseMenuComponent foldHoveredElement(BaseMenuComponent accumulator, BaseMenuComponent element) {
        if(accumulator == null) {
            return element;
        }

        if(element != null && element.layer.height > accumulator.layer.height) {
            return element;
        } 

        return accumulator;
    }

    public BaseMenuComponent getHoveredElement() {
        BaseMenuComponent hoveredElement = null;

        for(var child : this.children) {
            hoveredElement = this.foldHoveredElement(hoveredElement, child.getHoveredElement());
        }

        if(Mouse.MOUSE.position.isWithinBounds(this.globalPosition, this.dimensions)) {
            hoveredElement = this.foldHoveredElement(hoveredElement, this);
        }

        return hoveredElement;
    }

    public void onMouseEnter() {
        this.isHovered = true;
    }

    public void onMouseExit() {
        this.isHovered = false;
    }

    public void onFocus() {
        this.isFocused = true;
    }

    public void onBlur() {
        this.isFocused = false;
    }

    public void onClick() {
    }

    public void onPress() {
    }

    public void onCharacterTyped(char character) {
    }

    public void onKeyPressed(int key) {
    }

    public Cursor getCursor() {
        return Cursor.CURSOR_ARROW;
    }

    public int getRight() {
        return this.offset.x + this.dimensions.x;
    }

    public int getBottom() {
        return this.offset.y + this.dimensions.y;
    }

    public void centerH(BaseMenuComponent target) {
        this.offset.x = (target.dimensions.x - this.dimensions.x) / 2;
    }

    public void centerV(BaseMenuComponent target) {
        this.offset.y = (target.dimensions.y - this.dimensions.y) / 2;
    }

}
