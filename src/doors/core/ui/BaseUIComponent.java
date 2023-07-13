package doors.core.ui;

import java.util.ArrayList;
import java.util.Collection;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.io.Mouse;
import doors.core.utility.vector.Vector2in;

public class BaseUIComponent {
    
    public Vector2in offset;
    public Vector2in dimensions;
    public Vector2in globalPosition;
    public UILayer layer;
    private Collection<BaseUIComponent> children;

    protected boolean isFocused;
    protected boolean isSelected;
    protected boolean isHovered;

    public BaseUIComponent() {
        this.offset = new Vector2in();
        this.dimensions = new Vector2in();
        this.globalPosition = new Vector2in();
        this.layer = UILayer.NORMAL;
        this.children = new ArrayList<>();
    }

    protected <T extends BaseUIComponent> T addChild(T child) {
        this.children.add(child);
        return child;
    }

    protected void removeChild(BaseUIComponent child) {
        this.children.remove(child);
    }

    public void calculateLayout() {
        for(var child : this.children) {
            child.calculateLayout();
        }
    }

    public void writeUIComponent(MeshBuffer meshBuffer) {
        for(var child : this.children) {
            child.writeUIComponent(meshBuffer);
        }
    }

    public void calculatePosition(Vector2in parentPosition) {
        this.globalPosition.set(parentPosition).add(this.offset);
        for (var child : this.children) {
            child.calculatePosition(this.globalPosition);
        }
    }

    private BaseUIComponent foldHoveredElement(BaseUIComponent accumulator, BaseUIComponent element) {
        if(accumulator == null) {
            return element;
        }

        if(element != null && element.layer.height > accumulator.layer.height) {
            return element;
        } 

        return accumulator;
    }

    public BaseUIComponent getHoveredElement() {
        BaseUIComponent hoveredElement = null;

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

    public int getRight() {
        return this.offset.x + this.dimensions.x;
    }

    public int getBottom() {
        return this.offset.y + this.dimensions.y;
    }

    public void centerH(BaseUIComponent target) {
        this.offset.x = (target.dimensions.x - this.dimensions.x) / 2;
    }

    public void centerV(BaseUIComponent target) {
        this.offset.y = (target.dimensions.y - this.dimensions.y) / 2;
    }

}
