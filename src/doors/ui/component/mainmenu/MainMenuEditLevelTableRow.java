package doors.ui.component.mainmenu;

import doors.graphics.text.FontDecoration;
import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.graphics.texture.MenuTexture;
import doors.ui.component.IComponent;
import doors.ui.component.TextComponent;
import doors.ui.component.layout.alignment.HorizontalAlignment;
import doors.ui.component.layout.alignment.VerticalAlignment;
import doors.ui.component.layout.box.BoxComponent;
import doors.ui.component.layout.box.GrowDimension;
import doors.ui.cursor.PointerCursor;
import doors.ui.root.UIRoot;
import doors.utility.BoxedValue;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class MainMenuEditLevelTableRow implements IComponent {

    private BoxedValue<MainMenuEditLevelTableRow> boxedSelection;

    private TextComponent levelNameComponent;
    private BoxComponent layoutComponent;

    public MainMenuEditLevelTableRow(BoxedValue<MainMenuEditLevelTableRow> boxedSelection, String levelName) {
        this.boxedSelection = boxedSelection;
        this.levelNameComponent = new TextComponent(levelName, FontDecoration.NORMAL, Vector3fl.BLACK);

        this.layoutComponent = new BoxComponent(
            this.levelNameComponent,
            new GrowDimension(),
            new GrowDimension(),
            HorizontalAlignment.LEFT,
            VerticalAlignment.CENTER
        );
    }

    public String getLevelName() {
        return this.levelNameComponent.text;
    }

    public void setLevelName(String levelName) {
        this.levelNameComponent.text = levelName;
    }

    @Override
    public void onMousePress(UIRoot root) {
        var newSelected = this.boxedSelection.value == this ? null : this;
        this.boxedSelection.value = newSelected;
    }

    @Override
    public Vector2in getDimensions() {
        return this.layoutComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.layoutComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.layoutComponent.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.layoutComponent.adjustDimensions(availableSpace);
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.layoutComponent.calculatePosition(origin);
    }

    @Override
    public void update(UIRoot root) {
        if(root.hoveredComponent == this) {
            root.selectedCursor = PointerCursor.POINTER_CURSOR;
        }

        if(this.boxedSelection.value == this) {
            this.levelNameComponent.color.set(Vector3fl.WHITE);
        } else {
            this.levelNameComponent.color.set(Vector3fl.BLACK);
        }

        this.layoutComponent.update(root);

        root.captureMouse(this, this.getPosition(), this.getDimensions(), 0);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        if(this.boxedSelection.value == this) {
            spriteBatch.pushSprite(
                MenuTexture.MENU_TEXTURE.highlight,
                this.getPosition(),
                this.getDimensions(),
                SpriteBatchHeight.UI_NORMAL,
                Vector3fl.WHITE
            );
        }

        this.levelNameComponent.writeComponentToSpriteBatch(spriteBatch);
    }


}
