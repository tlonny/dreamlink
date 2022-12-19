package periwinkle.terrain;

import periwinkle.Game;
import periwinkle.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class BlockType {

    public static Map<Integer, BlockType> BLOCK_ID_LOOKUP = new HashMap<>();
    public static Map<String, BlockType> BLOCK_NAME_LOOKUP = new HashMap<>();

    public static BlockType AIR = new BlockType("air", null, 0, BlockFormat.VOID);

    public static BlockType GRASS = new BlockType("grass", Game.TERRAIN_ATLAS.grass, 0, BlockFormat.SOLID);

    public static BlockType SMOOTH_STONE = new BlockType("smooth_stone", Game.TERRAIN_ATLAS.smoothStone, 0, BlockFormat.SOLID);

    public static BlockType DIRT = new BlockType("dirt", Game.TERRAIN_ATLAS.dirt, 0, BlockFormat.SOLID);

    public static BlockType WOOD_PLANKS = new BlockType("wood_planks", Game.TERRAIN_ATLAS.woodPlanks, 0, BlockFormat.SOLID);

    public static BlockType ORNATE_STONE_STRIPS = new BlockType("ornate_stone_strips", Game.TERRAIN_ATLAS.ornateStoneStrips, 0, BlockFormat.SOLID);

    public static BlockType ORNAGE_STONE_SQUARE = new BlockType("ornage_stone_square", Game.TERRAIN_ATLAS.ornateStoneSquare, 0, BlockFormat.SOLID);

    public static BlockType BRICKS = new BlockType("bricks", Game.TERRAIN_ATLAS.bricks, 0, BlockFormat.SOLID);

    public static BlockType COBWEB = new BlockType("cobweb", Game.TERRAIN_ATLAS.cobweb, 0, BlockFormat.CROSS);

    public static BlockType RED_FLOWERS = new BlockType("red_flowers", Game.TERRAIN_ATLAS.redFlowers, 0, BlockFormat.CROSS);

    public static BlockType YELLOW_FLOWERS = new BlockType("yellow_flowers", Game.TERRAIN_ATLAS.yellowFlowers, 0, BlockFormat.CROSS);

    public static BlockType CRACKED_STONE = new BlockType("cracked_stone", Game.TERRAIN_ATLAS.crackedStone, 0, BlockFormat.SOLID);

    public static BlockType ROUGH_STONE = new BlockType("rough_stone", Game.TERRAIN_ATLAS.roughStone, 0, BlockFormat.SOLID);

    public static BlockType SMOOTH_SAND = new BlockType("smooth_sand", Game.TERRAIN_ATLAS.smoothSand, 0, BlockFormat.SOLID);

    public static BlockType GRAVEL = new BlockType("gravel", Game.TERRAIN_ATLAS.gravel, 0, BlockFormat.SOLID);

    public static BlockType WOOD_TRUNK = new BlockType("wood_trunk", Game.TERRAIN_ATLAS.woodTrunk, 0, BlockFormat.SOLID);

    public static BlockType ALGAE = new BlockType("algae", Game.TERRAIN_ATLAS.algae, 0, BlockFormat.SOLID);

    public static BlockType RED_MUSHROOM = new BlockType("red_mushroom", Game.TERRAIN_ATLAS.redMushroom, 0, BlockFormat.CROSS);

    public static BlockType BROWN_MUSHROOM = new BlockType("brown_mushroom", Game.TERRAIN_ATLAS.brownMushroom, 0, BlockFormat.CROSS);

    public static BlockType GOLD_ORE = new BlockType("gold_ore", Game.TERRAIN_ATLAS.goldOre, 0, BlockFormat.SOLID);

    public static BlockType SILVER_ORE = new BlockType("silver_ore", Game.TERRAIN_ATLAS.silverOre, 0, BlockFormat.SOLID);

    public static BlockType COAL = new BlockType("coal_ore", Game.TERRAIN_ATLAS.coalOre, 0, BlockFormat.SOLID);

    public static BlockType BOOK_SHELF = new BlockType("book_shelf", Game.TERRAIN_ATLAS.bookShelf, 0, BlockFormat.SOLID);

    public static BlockType STONY_CORRUPTION = new BlockType("stony_corruption", Game.TERRAIN_ATLAS.stonyCorruption, 4, BlockFormat.SOLID);

    public static BlockType DARK_STONE = new BlockType("dark_stone", Game.TERRAIN_ATLAS.darkStone, 0, BlockFormat.SOLID);

    public static BlockType GRASS_TUFT = new BlockType("grass_tuft", Game.TERRAIN_ATLAS.grassTuft, 0, BlockFormat.CROSS);

    public static BlockType STONY_SAND = new BlockType("stony_sand", Game.TERRAIN_ATLAS.stonySand, 0, BlockFormat.SOLID);

    public static BlockType IRON_ORE = new BlockType("iron_ore", Game.TERRAIN_ATLAS.ironOre, 0, BlockFormat.SOLID);

    public static BlockType STONE_SLABS = new BlockType("stone_slabs", Game.TERRAIN_ATLAS.stoneSlabs, 0, BlockFormat.SOLID);

    public static BlockType WHITE_CLOTH = new BlockType("white_cloth", Game.TERRAIN_ATLAS.whiteCloth, 0, BlockFormat.SOLID);

    public static BlockType FULL_CORRUPTION = new BlockType("full_corruption", Game.TERRAIN_ATLAS.fullCorruption, 6, BlockFormat.SOLID);

    public static BlockType SNOW = new BlockType("snow", Game.TERRAIN_ATLAS.snow, 0, BlockFormat.SOLID);

    public static BlockType ICE = new BlockType("ice", Game.TERRAIN_ATLAS.ice, 0, BlockFormat.SOLID);

    public static BlockType MUD = new BlockType("mud", Game.TERRAIN_ATLAS.mud, 0, BlockFormat.SOLID);

    public static BlockType MOSSY_STONE_SLABS = new BlockType("mossy_stone_slabs", Game.TERRAIN_ATLAS.mossyStoneSlabs, 0, BlockFormat.SOLID);

    public static BlockType STAINED_STONE_SLABS = new BlockType("stained_stone_slabs", Game.TERRAIN_ATLAS.stainedStoneSlabs, 0, BlockFormat.SOLID);

    public static BlockType BLACK_CLOTH = new BlockType("black_cloth", Game.TERRAIN_ATLAS.blackCloth, 0, BlockFormat.SOLID);

    public static BlockType DARK_GRAY_CLOTH = new BlockType("dark_gray_cloth", Game.TERRAIN_ATLAS.darkGrayCloth, 0, BlockFormat.SOLID);

    public static BlockType RED_CLOTH = new BlockType("red_cloth", Game.TERRAIN_ATLAS.redCloth, 0, BlockFormat.SOLID);

    public static BlockType PINK_CLOTH = new BlockType("pink_cloth", Game.TERRAIN_ATLAS.pinkCloth, 0, BlockFormat.SOLID);

    public static BlockType GREEN_CLOTH = new BlockType("green_cloth", Game.TERRAIN_ATLAS.greenCloth, 0, BlockFormat.SOLID);

    public static BlockType LIME_CLOTH = new BlockType("lime_cloth", Game.TERRAIN_ATLAS.limeCloth, 0, BlockFormat.SOLID);

    public static BlockType BROWN_CLOTH = new BlockType("brown_cloth", Game.TERRAIN_ATLAS.brownCloth, 0, BlockFormat.SOLID);

    public static BlockType YELLOW_CLOTH = new BlockType("yellow_cloth", Game.TERRAIN_ATLAS.yellowCloth, 0, BlockFormat.SOLID);

    public static BlockType NAVY_CLOTH = new BlockType("navy_cloth", Game.TERRAIN_ATLAS.navyCloth, 0, BlockFormat.SOLID);

    public static BlockType BLUE_CLOTH = new BlockType("blue_cloth", Game.TERRAIN_ATLAS.blueCLoth, 0, BlockFormat.SOLID);

    public static BlockType PURPLE_CLOTH = new BlockType("purple_cloth", Game.TERRAIN_ATLAS.purpleCloth, 0, BlockFormat.SOLID);

    public static BlockType MAGENTA_CLOTH = new BlockType("magenta_cloth", Game.TERRAIN_ATLAS.magnetaCloth, 0, BlockFormat.SOLID);

    public static BlockType CYAN_CLOTH = new BlockType("cyan_cloth", Game.TERRAIN_ATLAS.cyanCloth, 0, BlockFormat.SOLID);

    public static BlockType ORANGE_CLOTH = new BlockType("orange_cloth", Game.TERRAIN_ATLAS.orangeCloth, 0, BlockFormat.SOLID);

    public static BlockType LIGHT_GRAY_CLOTH = new BlockType("light_gray_cloth", Game.TERRAIN_ATLAS.lightGrayCloth, 0, BlockFormat.SOLID);

    public static BlockType MAGMA = new BlockType("magma", Game.TERRAIN_ATLAS.magma, 15, BlockFormat.SOLID);

    public static BlockType TORCH = new BlockType("torch", Game.TERRAIN_ATLAS.torch, 15, BlockFormat.CROSS);

    private static int incrementerBlockID = 0;

    public int blockID;
    public int localLight;
    public BlockFormat blockFormat;
    public String name;
    public Texture texture;

    public BlockType(String name, Texture texture, int localLight, BlockFormat blockFormat) {
        this.blockID = incrementerBlockID++;
        this.name = name;
        this.localLight = localLight;
        this.blockFormat = blockFormat;
        this.texture = texture;
        BLOCK_ID_LOOKUP.put(this.blockID, this);
        BLOCK_NAME_LOOKUP.put(this.name, this);
    }

}
