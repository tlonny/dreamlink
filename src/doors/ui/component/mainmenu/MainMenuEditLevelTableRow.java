package doors.ui.component.mainmenu;

import doors.graphics.font.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.MenuTexture;
import doors.ui.component.IComponent;
import doors.ui.component.TextComponent;
import doors.ui.cursor.PointerCursor;
import doors.ui.root.UIRoot;
import doors.utility.BoxedValue;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class MainMenuEditLevelTableRow implements IComponent {

    private BoxedValue<MainMenuEditLevelTableRow> boxedSelection;

    public TextComponent content;
    private Vector2in dimensions = new Vector2in();
    private Vector2in position = new Vector2in();
    private Vector2in originCursor = new Vector2in();

    public MainMenuEditLevelTableRow(BoxedValue<MainMenuEditLevelTableRow> boxedSelection, String levelName) {
        this.boxedSelection = boxedSelection;
        this.content = new TextComponent(levelName, FontDecoration.NORMAL, Vector3fl.BLACK);
    }

    @Override
    public void onMousePress(UIRoot root) {
        var newSelected = this.boxedSelection.value == this ? null : this;
        this.boxedSelection.value = newSelected;
    }

    @Override
    public Vector2in getDimensions() {
        return this.dimensions;
    }

    @Override
    public void calculateDimensions() {
        this.content.calculateDimensions();
    }

    @Override
    public void update(Vector2in origin, UIRoot root) {
        this.position.set(origin);

        if(root.hoveredComponent == this) {
            root.selectedCursor = PointerCursor.POINTER_CURSOR;
        }

        if(this.boxedSelection.value == this) {
            this.content.color.set(Vector3fl.WHITE);
        } else {
            this.content.color.set(Vector3fl.BLACK);
        }

        this.originCursor.set(origin);
        this.originCursor.y += this.dimensions.y / 2 - this.content.getDimensions().y / 2;
        this.content.update(this.originCursor, root);

        root.captureMouse(this, this.position, this.dimensions, 0);
    }

    @Override
    public void writeUIComponent(SpriteBatch spriteBatch) {
        if(this.boxedSelection.value == this) {
            spriteBatch.writeSprite(
                MenuTexture.MENU_TEXTURE.highlight,
                this.position,
                this.dimensions,
                SpriteBatchHeight.UI_NORMAL,
                Vector3fl.WHITE
            );
        }

        this.content.writeUIComponent(spriteBatch);
    }


}
