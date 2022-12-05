package dreamlink.overlay.component.list.view;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.sprite.template.SolidSpriteTemplate;
import dreamlink.graphics.text.CharacterTextureSampleLookup;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.EmptyComponent;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.utility.maths.Vector4fMaths;

public class ListViewComponent extends WrapperComponent {

    private static final int spacing = 5;
    private static final Vector2ic iconDimensions = new Vector2i(16, 16);
    public static final int rowHeight = 18;

    private final BoxComponent component;
    private final IListViewComponentProvider provider;
    private int visibleRowCount;
    private int visibleCharacterCount;

    public ListViewComponent(IListViewComponentProvider provider) {
        this.provider = provider;
        this.component = new BoxComponent(
            new EmptyComponent(),
            BoxDimension.grow(),
            BoxDimension.grow()
        );
    }

    public int getVisibleRowCount() {
        return this.visibleRowCount;
    }

    public int getVisibleCharacterCount() {
        return this.visibleCharacterCount;
    }

    @Override
    public void computeDimensions(Vector2ic availableSpace) {
        super.computeDimensions(availableSpace);

        var componentDimensions = this.component.getDimensions(new Vector2i());
        var characterWidth = componentDimensions.x
            - ListViewComponent.iconDimensions.x()
            - ListViewComponent.spacing;

        var deltaHeight = componentDimensions.y % ListViewComponent.rowHeight;

        super.computeDimensions(
            new Vector2i(
                availableSpace.x(), 
                availableSpace.y() - deltaHeight
            )
        );

        this.component.getDimensions(componentDimensions);
        this.visibleRowCount = componentDimensions.y / ListViewComponent.rowHeight;
        this.visibleCharacterCount = characterWidth / MenuTextureSample.glyphDimensions.x();
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    private final Vector2i writeToSpritBatchPositionCursor = new Vector2i();
    private final Vector2i writeToSpritBatchFinalPosition = new Vector2i();
    private final Vector2i writeToSpriteBatchDimensions = new Vector2i();
    private final ListViewRowData writeToSpriteBatchRowData = new ListViewRowData();

    @Override
    public void writeToSpriteBatch(SpriteBatch spriteBatch) {
        super.writeToSpriteBatch(spriteBatch);
        var cursor = this.getPosition(this.writeToSpritBatchPositionCursor);
        var initialHorizPosition = cursor.x;
        var rowData = this.writeToSpriteBatchRowData;
        var rowDimensions = this.component.getDimensions(this.writeToSpriteBatchDimensions);
        rowDimensions.y = ListViewComponent.rowHeight;

        for(var ix = 0; ix < this.visibleRowCount; ix += 1) {
            var offsetIndex = ix + this.provider.getRowOffset();
            this.provider.setRowData(offsetIndex, rowData);

            if(!rowData.isUsed) {
                break;
            }

            if(rowData.isHighlighted) {
                SolidSpriteTemplate.overlayHighlight.writeToSpriteBatch(
                    spriteBatch,
                    cursor, 
                    rowDimensions,
                    this.getSpriteHeight()
                );
            }


            var adjustedIconPosition = this.writeToSpritBatchFinalPosition.set(cursor);
            adjustedIconPosition.y += (ListViewComponent.rowHeight - ListViewComponent.iconDimensions.y()) / 2;
            rowData.icon.writeToSpriteBatch(
                spriteBatch,
                adjustedIconPosition,
                ListViewComponent.iconDimensions,
                this.getSpriteHeight()
            );

            var numCharacters = Math.min(rowData.text.length(), this.visibleCharacterCount);
            cursor.x += ListViewComponent.spacing + ListViewComponent.iconDimensions.x();

            for(var jx = 0; jx < numCharacters; jx += 1) {
                var character = rowData.text.charAt(jx);
                var glyph = CharacterTextureSampleLookup.instance.getTextureSample(
                    character, FontDecoration.normal
                );

                var adjustedGlyphPosition = this.writeToSpritBatchFinalPosition.set(cursor);
                adjustedGlyphPosition.y += (ListViewComponent.rowHeight - MenuTextureSample.glyphDimensions.y()) / 2;
                spriteBatch.writeTextureSample(
                    adjustedGlyphPosition, 
                    MenuTextureSample.glyphDimensions, 
                    this.getSpriteHeight(),
                    glyph,
                    rowData.isHighlighted ? Vector4fMaths.white : Vector4fMaths.black
                );

                cursor.x += MenuTextureSample.glyphDimensions.x();
            }

            cursor.x = initialHorizPosition;
            cursor.y += ListViewComponent.rowHeight;

        }
    }

}