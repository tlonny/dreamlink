package doors.ui;

import java.util.Collection;

import org.joml.Vector2i;

public class GridElement implements IElement {

    private Collection<IElement> children;
    private int columns;

    private boolean isDirty;
    private Vector2i dimensions;

    public GridElement(Collection<IElement> children, int columns) {
        this.children = children;
        this.columns = columns;

        this.dimensions = new Vector2i();
        this.isDirty = true;
    }

    @Override
    public void render(Vector2i position) {
        var positionCursor = new Vector2i(position);
        var count = 0;
        var rowHeight = 0;

        for(var child : this.children) {
            child.render(positionCursor);
            var childDims = child.getDimensions();
            rowHeight = Math.max(rowHeight, childDims.y);
            positionCursor.x += childDims.x;
            count += 1;
            if(count % this.columns == 0) {
                positionCursor.x = position.x;
                positionCursor.y += rowHeight;
            }
        }
    }

    @Override
    public Vector2i getDimensions() {
        if(this.isDirty) {
            var maxWidth = 0;
            var maxHeight = 0;
            var rowWidth = 0;
            var rowHeight = 0;
            var count = 0;

            for(var child : this.children) {
                if(count % this.columns == 0) {
                    maxWidth = Math.max(maxWidth, rowWidth);
                    maxHeight += rowHeight;
                    rowWidth = 0;
                    count = 0;
                }
                var dims = child.getDimensions();
                rowWidth += dims.x;
                rowHeight = Math.max(rowHeight, dims.y);
                count+=1;
            }

            maxWidth = Math.max(maxWidth, rowWidth);
            maxHeight += rowHeight;
            rowWidth = 0;
            count = 0;

            this.dimensions.set(maxWidth, maxHeight);
        }
        return this.dimensions;
    }


}
