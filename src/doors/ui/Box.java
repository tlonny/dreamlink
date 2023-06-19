package doors.ui;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;
import org.joml.Vector3f;

import doors.overlay.Overlay;
import doors.overlay.SystemTextureAtlas;
import doors.utility.Color;

public class Box implements IComponent {

    public Vector3f color;
    public Vector2i dimensions;
    public List<IComponent> children;

    public int padding;
    public boolean center;

    public Box(Vector2i dimensions) {
        this.dimensions = dimensions;
        this.color = new Vector3f(Color.MENU_BACKGROUND_PRIMARY);
        this.children = new ArrayList<>();
    }

    public void paint(Vector2i position) {
        var positionCursor = new Vector2i(position);
        var dimensionsCursor = new Vector2i(this.dimensions);
        Overlay.OVERLAY.paint(positionCursor, dimensionsCursor, SystemTextureAtlas.SOLID, Color.BLACK);

        positionCursor.add(1, 1);
        dimensionsCursor.sub(2, 2);
        Overlay.OVERLAY.paint(positionCursor, dimensionsCursor, SystemTextureAtlas.SOLID, Color.WHITE);

        positionCursor.add(1, 1);
        dimensionsCursor.sub(2, 2);
        Overlay.OVERLAY.paint(positionCursor, dimensionsCursor, SystemTextureAtlas.SOLID, this.color);

        positionCursor.set(position);

        if(this.center) {
            dimensionsCursor.zero();
            for (var child : this.children) {
                var childDims = child.getDimensions();
                dimensionsCursor.y += childDims.y;
                dimensionsCursor.x = Math.max(dimensionsCursor.x, childDims.x);
            }

            positionCursor.add(
                this.dimensions.x / 2 - dimensionsCursor.x / 2,
                this.dimensions.y / 2 - dimensionsCursor.y / 2
            );
        } else {
            positionCursor.add(this.padding, this.padding);
        }

        for (var child : this.children) {
            child.paint(positionCursor);
            positionCursor.add(0, child.getDimensions().y);
        }
    }

    @Override
    public Vector2i getDimensions() {
        return this.dimensions;
    }
}
