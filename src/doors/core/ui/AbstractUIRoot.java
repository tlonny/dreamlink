package doors.core.ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.io.Mouse;
import doors.utility.vector.Vector2in;

public abstract class AbstractUIRoot {

    public AbstractUIComponent hoveredComponent;
    public Vector2in position = new Vector2in();

    private List<AbstractUIComponent> components = new ArrayList<>();

    protected <T extends AbstractUIComponent> T addComponent(T component) {
        this.components.add(component);
        return component;
    }

    private int getLayerHeight(AbstractUIComponent component) {
        return component.getLayer().height;
    }

    public void update() {
        this.components.sort(Comparator.comparingInt(this::getLayerHeight));
        this.hoveredComponent = null;
        for(var component : components) {
            component.position.set(this.position).add(component.offset);
            if(Mouse.MOUSE.position.isWithinBounds(component.position, component.getDimensions())) {
                if(this.hoveredComponent == null || this.hoveredComponent.getLayer().height < component.getLayer().height) {
                    this.hoveredComponent = component;
                }
            }
        }

        for(var component : components) {
            component.update();
        }
    }

    public void writeUIRoot(MeshBuffer meshBuffer) {
        for(var component : components) {
            component.writeUIComponent(meshBuffer);
        }
    }

}
