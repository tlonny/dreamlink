package periwinkle.terrain;

import periwinkle.graphics.Atlas;
import periwinkle.graphics.ITextureLookup;
import org.joml.Vector2f;
import org.joml.Vector2i;

import java.util.HashMap;
import java.util.Map;

public class BlockType implements ITextureLookup {

    public static Map<Integer, BlockType> BLOCK_ID_LOOKUP = new HashMap<>();
    public static Map<String, BlockType> BLOCK_NAME_LOOKUP = new HashMap<>();
    private static Vector2i BLOCK_DIMENSIONS = new Vector2i(16, 16);

    public static BlockType AIR = new BlockType(
        0, "air", 0,
        BlockFormat.VOID,
        Atlas.TERRAIN_ATLAS.buildTextureOffsets(new Vector2i(15, 1).mul(BLOCK_DIMENSIONS), BLOCK_DIMENSIONS)
    );

    public static BlockType GRASS = new BlockType(
        1, "grass", 0,
        BlockFormat.SOLID,
        Atlas.TERRAIN_ATLAS.buildTextureOffsets(new Vector2i(0, 0).mul(BLOCK_DIMENSIONS), BLOCK_DIMENSIONS)
    );

    public static BlockType CORRUPTION = new BlockType(
        2, "corruption", 0,
        BlockFormat.SOLID,
        Atlas.TERRAIN_ATLAS.buildTextureOffsets(new Vector2i(1, 4).mul(BLOCK_DIMENSIONS), BLOCK_DIMENSIONS)
    );

    public static BlockType MOSSY_SLABS = new BlockType(
        3, "mossy_slabs", 0,
        BlockFormat.SOLID,
        Atlas.TERRAIN_ATLAS.buildTextureOffsets(new Vector2i(4, 6).mul(BLOCK_DIMENSIONS), BLOCK_DIMENSIONS)
    );

    public static BlockType STONE = new BlockType(
        4, "stone", 0,
        BlockFormat.SOLID,
        Atlas.TERRAIN_ATLAS.buildTextureOffsets(new Vector2i(1, 0).mul(BLOCK_DIMENSIONS), BLOCK_DIMENSIONS)
    );

    public static BlockType WOOD = new BlockType(
        5, "wood", 0,
        BlockFormat.SOLID,
        Atlas.TERRAIN_ATLAS.buildTextureOffsets(new Vector2i(4, 0).mul(BLOCK_DIMENSIONS), BLOCK_DIMENSIONS)
    );

    public static BlockType BRICK = new BlockType(
        6, "brick", 0,
        BlockFormat.SOLID,
        Atlas.TERRAIN_ATLAS.buildTextureOffsets(new Vector2i(7, 0).mul(BLOCK_DIMENSIONS), BLOCK_DIMENSIONS)
    );

    public static BlockType MAGMA = new BlockType(
        7, "magma", 15,
        BlockFormat.SOLID,
        Atlas.TERRAIN_ATLAS.buildTextureOffsets(new Vector2i(15, 15).mul(BLOCK_DIMENSIONS), BLOCK_DIMENSIONS)
    );

    public static BlockType RED_FLOWER = new BlockType(
            8, "red_flower", 0,
            BlockFormat.CROSS,
            Atlas.TERRAIN_ATLAS.buildTextureOffsets(new Vector2i(12, 0).mul(BLOCK_DIMENSIONS), BLOCK_DIMENSIONS)
    );

    public static BlockType[] BLOCK_TYPES = new BlockType[] {
        AIR,
        GRASS,
        CORRUPTION,
        MOSSY_SLABS,
        STONE,
        WOOD,
        BRICK,
        MAGMA,
        RED_FLOWER
    };

    public static void init() {
        for(var blockType : BLOCK_TYPES) {
            BLOCK_ID_LOOKUP.put(blockType.blockID, blockType);
            BLOCK_NAME_LOOKUP.put(blockType.blockName, blockType);
        }
    }

    public final int blockID;
    public final int localLight;
    public final BlockFormat blockFormat;
    public final String blockName;
    private final Vector2f[] textureOffsets;

    public Vector2f[] getTextureOffsets() {
        return this.textureOffsets;
    }

    public BlockType(int blockID, String blockName, int localLight, BlockFormat blockFormat, Vector2f[] textureOffsets) {
        this.blockID = blockID;
        this.blockName = blockName;
        this.localLight = localLight;
        this.blockFormat = blockFormat;
        this.textureOffsets = textureOffsets;
    }

}
