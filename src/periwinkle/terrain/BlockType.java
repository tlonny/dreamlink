package periwinkle.terrain;

import periwinkle.graphics.Atlas;
import periwinkle.graphics.Sprite;

import java.util.HashMap;
import java.util.Map;

public class BlockType {

    public static Map<Integer, BlockType> BLOCK_ID_LOOKUP = new HashMap<>();
    public static Map<String, BlockType> BLOCK_NAME_LOOKUP = new HashMap<>();

    public static BlockType AIR = new BlockType("AIR", "MISSING", 0, BlockFormat.VOID);

    public static BlockType GRASS = new BlockType("GRASS", 0, BlockFormat.SOLID);

    public static BlockType SMOOTH_STONE = new BlockType("SMOOTH_STONE", 0, BlockFormat.SOLID);

    public static BlockType DIRT = new BlockType("DIRT", 0, BlockFormat.SOLID);

    public static BlockType WOOD_PLANKS = new BlockType("WOOD_PLANKS", 0, BlockFormat.SOLID);

    public static BlockType ORNATE_STONE_STRIPS = new BlockType("ORNATE_STONE_STRIPS", 0, BlockFormat.SOLID);

    public static BlockType ORNAGE_STONE_SQUARE = new BlockType("ORNAGE_STONE_SQUARE", 0, BlockFormat.SOLID);

    public static BlockType BRICKS = new BlockType("BRICKS", 0, BlockFormat.SOLID);

    public static BlockType COBWEB = new BlockType("COBWEB", 0, BlockFormat.CROSS);

    public static BlockType RED_FLOWERS = new BlockType("RED_FLOWERS", 0, BlockFormat.CROSS);

    public static BlockType YELLOW_FLOWERS = new BlockType("YELLOW_FLOWERS", 0, BlockFormat.CROSS);

    public static BlockType CRACKED_STONE = new BlockType("CRACKED_STONE", 0, BlockFormat.SOLID);

    public static BlockType ROUGH_STONE = new BlockType("ROUGH_STONE", 0, BlockFormat.SOLID);

    public static BlockType SMOOTH_SAND = new BlockType("SMOOTH_SAND", 0, BlockFormat.SOLID);

    public static BlockType GRAVEL = new BlockType("GRAVEL", 0, BlockFormat.SOLID);

    public static BlockType WOOD_TRUNK = new BlockType("WOOD_TRUNK", 0, BlockFormat.SOLID);

    public static BlockType ALGAE = new BlockType("ALGAE", 0, BlockFormat.SOLID);

    public static BlockType RED_MUSHROOM = new BlockType("RED_MUSHROOM", 0, BlockFormat.CROSS);

    public static BlockType BROWN_MUSHROOM = new BlockType("BROWN_MUSHROOM", 0, BlockFormat.CROSS);

    public static BlockType GOLD_ORE = new BlockType("GOLD_ORE", 0, BlockFormat.SOLID);

    public static BlockType SILVER_ORE = new BlockType("SILVER_ORE", 0, BlockFormat.SOLID);

    public static BlockType COAL = new BlockType("COAL", 0, BlockFormat.SOLID);

    public static BlockType BOOK_SHELF = new BlockType("BOOK_SHELF", 0, BlockFormat.SOLID);

    public static BlockType STONY_CORRUPTION = new BlockType("STONY_CORRUPTION", 4, BlockFormat.SOLID);

    public static BlockType DARK_STONE = new BlockType("DARK_STONE", 0, BlockFormat.SOLID);

    public static BlockType GRASS_TUFT = new BlockType("GRASS_TUFT", 0, BlockFormat.CROSS);

    public static BlockType STONY_SAND = new BlockType("STONY_SAND", 0, BlockFormat.SOLID);

    public static BlockType IRON_ORE = new BlockType("IRON_ORE", 0, BlockFormat.SOLID);

    public static BlockType STONE_SLABS = new BlockType("STONE_SLABS", 0, BlockFormat.SOLID);

    public static BlockType WHITE_CLOTH = new BlockType("WHITE_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType FULL_CORRUPTION = new BlockType("FULL_CORRUPTION", 6, BlockFormat.SOLID);

    public static BlockType SNOW = new BlockType("SNOW", 0, BlockFormat.SOLID);

    public static BlockType ICE = new BlockType("ICE", 0, BlockFormat.SOLID);

    public static BlockType CACTUS = new BlockType("CACTUS", 0, BlockFormat.SOLID);

    public static BlockType MUD = new BlockType("MUD", 0, BlockFormat.SOLID);

    public static BlockType MOSSY_STONE_SLABS = new BlockType("MOSSY_STONE_SLABS", 0, BlockFormat.SOLID);

    public static BlockType STAINED_STONE_SLABS = new BlockType("STAINED_STONE_SLABS", 0, BlockFormat.SOLID);

    public static BlockType BLACK_CLOTH = new BlockType("BLACK_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType DARK_GRAY_CLOTH = new BlockType("DARK_GRAY_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType RED_CLOTH = new BlockType("RED_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType PINK_CLOTH = new BlockType("PINK_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType GREEN_CLOTH = new BlockType("GREEN_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType LIME_CLOTH = new BlockType("LIME_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType BROWN_CLOTH = new BlockType("BROWN_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType YELLOW_CLOTH = new BlockType("YELLOW_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType NAVY_CLOTH = new BlockType("NAVY_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType BLUE_CLOTH = new BlockType("BLUE_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType PURPLE_CLOTH = new BlockType("PURPLE_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType MAGENTA_CLOTH = new BlockType("MAGENTA_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType CYAN_CLOTH = new BlockType("CYAN_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType ORANGE_CLOTH = new BlockType("ORANGE_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType LIGHT_GRAY_CLOTH = new BlockType("LIGHT_GRAY_CLOTH", 0, BlockFormat.SOLID);

    public static BlockType MAGMA = new BlockType("MAGMA", 10, BlockFormat.SOLID);

    public static BlockType TORCH = new BlockType("TORCH", 15, BlockFormat.CROSS);

    private static int incrementerBlockID = 0;

    public static void init() {
        AIR.setup();
        GRASS.setup();
        SMOOTH_STONE.setup();
        DIRT.setup();
        WOOD_PLANKS.setup();
        ORNATE_STONE_STRIPS.setup();
        ORNAGE_STONE_SQUARE.setup();
        BRICKS.setup();
        COBWEB.setup();
        RED_FLOWERS.setup();
        YELLOW_FLOWERS.setup();
        CRACKED_STONE.setup();
        ROUGH_STONE.setup();
        SMOOTH_SAND.setup();
        GRAVEL.setup();
        WOOD_TRUNK.setup();
        ALGAE.setup();
        RED_MUSHROOM.setup();
        BROWN_MUSHROOM.setup();
        GOLD_ORE.setup();
        SILVER_ORE.setup();
        COAL.setup();
        BOOK_SHELF.setup();
        STONY_CORRUPTION.setup();
        DARK_STONE.setup();
        GRASS_TUFT.setup();
        STONY_SAND.setup();
        IRON_ORE.setup();
        STONE_SLABS.setup();
        WHITE_CLOTH.setup();
        FULL_CORRUPTION.setup();
        SNOW.setup();
        ICE.setup();
        CACTUS.setup();
        MUD.setup();
        MOSSY_STONE_SLABS.setup();
        STAINED_STONE_SLABS.setup();
        BLACK_CLOTH.setup();
        DARK_GRAY_CLOTH.setup();
        RED_CLOTH.setup();
        PINK_CLOTH.setup();
        GREEN_CLOTH.setup();
        LIME_CLOTH.setup();
        BROWN_CLOTH.setup();
        YELLOW_CLOTH.setup();
        NAVY_CLOTH.setup();
        BLUE_CLOTH.setup();
        PURPLE_CLOTH.setup();
        MAGENTA_CLOTH.setup();
        CYAN_CLOTH.setup();
        ORANGE_CLOTH.setup();
        LIGHT_GRAY_CLOTH.setup();
        MAGMA.setup();
        TORCH.setup();
    }

    public final int blockID;
    public final int localLight;
    public final BlockFormat blockFormat;
    public final String blockName;
    public final Sprite sprite;

    public BlockType(String blockName, int localLight, BlockFormat blockFormat) {
        this(blockName, blockName, localLight, blockFormat);
    }

    public void setup() {
        BLOCK_ID_LOOKUP.put(this.blockID, this);
        BLOCK_NAME_LOOKUP.put(this.blockName, this);
    }

    public BlockType(String blockName, String spriteKey, int localLight, BlockFormat blockFormat) {
        this.blockID = incrementerBlockID++;
        this.blockName = blockName;
        this.localLight = localLight;
        this.blockFormat = blockFormat;
        this.sprite = Atlas.TERRAIN_ATLAS.getSprite(spriteKey);
    }

}
