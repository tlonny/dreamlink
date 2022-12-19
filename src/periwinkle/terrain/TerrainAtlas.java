package periwinkle.terrain;

import org.joml.Vector2i;

import periwinkle.graphics.Atlas;
import periwinkle.graphics.Texture;

public class TerrainAtlas extends Atlas {

    private static Vector2i TILE_16_16 = new Vector2i(16,16);

    public Texture grass = this.createTexture(new Vector2i(0,0), TILE_16_16);
    public Texture smoothStone = this.createTexture(new Vector2i(1,0), TILE_16_16);
    public Texture dirt = this.createTexture(new Vector2i(2,0), TILE_16_16);
    public Texture woodPlanks = this.createTexture(new Vector2i(4,0), TILE_16_16);
    public Texture ornateStoneStrips = this.createTexture(new Vector2i(5,0), TILE_16_16);
    public Texture ornateStoneSquare = this.createTexture(new Vector2i(6,0), TILE_16_16);
    public Texture bricks = this.createTexture(new Vector2i(7,0), TILE_16_16);
    public Texture cobweb = this.createTexture(new Vector2i(11,0), TILE_16_16);
    public Texture redFlowers = this.createTexture(new Vector2i(12,0), TILE_16_16);
    public Texture yellowFlowers = this.createTexture(new Vector2i(13,0), TILE_16_16);
    public Texture crackedStone = this.createTexture(new Vector2i(0,1), TILE_16_16);
    public Texture roughStone = this.createTexture(new Vector2i(1,1), TILE_16_16);
    public Texture smoothSand = this.createTexture(new Vector2i(2,1), TILE_16_16);
    public Texture gravel = this.createTexture(new Vector2i(3,1), TILE_16_16);
    public Texture woodTrunk = this.createTexture(new Vector2i(4,1), TILE_16_16);
    public Texture algae = this.createTexture(new Vector2i(9,1), TILE_16_16);
    public Texture redMushroom = this.createTexture(new Vector2i(12,1), TILE_16_16);
    public Texture brownMushroom = this.createTexture(new Vector2i(13,1), TILE_16_16);
    public Texture goldOre = this.createTexture(new Vector2i(0,2), TILE_16_16);
    public Texture silverOre = this.createTexture(new Vector2i(1,2), TILE_16_16);
    public Texture coalOre = this.createTexture(new Vector2i(2,2), TILE_16_16);
    public Texture bookShelf = this.createTexture(new Vector2i(3,2), TILE_16_16);
    public Texture stonyCorruption = this.createTexture(new Vector2i(4,2), TILE_16_16);
    public Texture darkStone = this.createTexture(new Vector2i(5,2), TILE_16_16);
    public Texture grassTuft = this.createTexture(new Vector2i(7,2), TILE_16_16);
    public Texture stonySand = this.createTexture(new Vector2i(0,3), TILE_16_16);
    public Texture ironOre = this.createTexture(new Vector2i(2,3), TILE_16_16);
    public Texture stoneSlabs = this.createTexture(new Vector2i(6,3), TILE_16_16);
    public Texture whiteCloth = this.createTexture(new Vector2i(0,4), TILE_16_16);
    public Texture fullCorruption = this.createTexture(new Vector2i(1,4), TILE_16_16);
    public Texture snow = this.createTexture(new Vector2i(2,4), TILE_16_16);
    public Texture ice = this.createTexture(new Vector2i(3,4), TILE_16_16);
    public Texture mud = this.createTexture(new Vector2i(8,4), TILE_16_16);
    public Texture torch = this.createTexture(new Vector2i(0,5), TILE_16_16);
    public Texture mossyStoneSlabs = this.createTexture(new Vector2i(4,6), TILE_16_16);
    public Texture stainedStoneSlabs = this.createTexture(new Vector2i(5,6), TILE_16_16);
    public Texture blackCloth = this.createTexture(new Vector2i(1,7), TILE_16_16);
    public Texture darkGrayCloth = this.createTexture(new Vector2i(2,7), TILE_16_16);
    public Texture redCloth = this.createTexture(new Vector2i(1,8), TILE_16_16);
    public Texture pinkCloth = this.createTexture(new Vector2i(2,8), TILE_16_16);
    public Texture greenCloth = this.createTexture(new Vector2i(1,9), TILE_16_16);
    public Texture limeCloth = this.createTexture(new Vector2i(2,9), TILE_16_16);
    public Texture brownCloth = this.createTexture(new Vector2i(1,10), TILE_16_16);
    public Texture yellowCloth = this.createTexture(new Vector2i(2,10), TILE_16_16);
    public Texture navyCloth = this.createTexture(new Vector2i(1,11), TILE_16_16);
    public Texture blueCLoth = this.createTexture(new Vector2i(2,11), TILE_16_16);
    public Texture purpleCloth = this.createTexture(new Vector2i(1,12), TILE_16_16);
    public Texture magnetaCloth = this.createTexture(new Vector2i(2,12), TILE_16_16);
    public Texture cyanCloth = this.createTexture(new Vector2i(1,13), TILE_16_16);
    public Texture orangeCloth = this.createTexture(new Vector2i(2,13), TILE_16_16);
    public Texture lightGrayCloth = this.createTexture(new Vector2i(1,14), TILE_16_16);
    public Texture magma = this.createTexture(new Vector2i(15,15), TILE_16_16);

    public TerrainAtlas() {
        super("src/texture/terrain.png", new Vector2i(256, 256));
    }
}

