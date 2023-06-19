package doors.ui;

import java.util.ArrayList;
import java.util.Arrays;

import org.joml.Vector2i;

import doors.Game;
import doors.utility.Color;

public class BlockPickerElement implements IElement {

    private static Vector2i BLOCK_DIMENSIONS = new Vector2i(32, 32);

    private GridElement gridElement;
    
    public BlockPickerElement() {
        var blocks = new ArrayList<IElement>();
        for(var block : Game.GAME.currentTerrain.blockMap.values()) {
            var spriteElement = new SpriteElement(block.textureSample, BLOCK_DIMENSIONS, Color.WHITE);
            var blockElement = new VerticalSpanElement(Arrays.asList(
                new PaddingElement(new ButtonElement(spriteElement, () -> {}), 4),
                new TextLineElement(block.name, false, Color.TEXT)
            ), true);
            blocks.add(blockElement);
        }
        this.gridElement = new GridElement(blocks, 4);
    }

    public Vector2i getDimensions() {
        return this.gridElement.getDimensions();
    }

    public void render(Vector2i position) {
        this.gridElement.render(position);
    }
}
