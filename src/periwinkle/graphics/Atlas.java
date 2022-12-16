package periwinkle.graphics;

import periwinkle.utility.File;
import periwinkle.utility.ImageData;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.joml.Vector2f;
import org.json.JSONObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

public class Atlas {

    public static Atlas TERRAIN_ATLAS = new Atlas("src/texture/terrain");

    public static Atlas UI_ATLAS = new Atlas("src/texture/ui");

    public static Atlas SKYBOX_ATLAS = new Atlas("src/texture/skybox");

    public static Atlas PARTICLE_ATLAS = new Atlas("src/texture/particle");

    public static void init() {
        TERRAIN_ATLAS.setup();
        UI_ATLAS.setup();
        SKYBOX_ATLAS.setup();
        PARTICLE_ATLAS.setup();
    }

    private int atlasID;
    private final String textureRoot;
    private final Map<String, Sprite> spriteMap;

    public Atlas(String textureRoot) {
        this.textureRoot = textureRoot;
        this.spriteMap = new HashMap<String, Sprite>();
    }

    public Sprite getSprite(String spriteKey) {
        return this.spriteMap.get(spriteKey);
    }

    public void setup() {

        var textureSrc = Paths.get(this.textureRoot, "texture.png").toString();
        var atlasSrc = Paths.get(this.textureRoot, "atlas.json").toString();

        var textureData = new ImageData(textureSrc);
        textureData.load();

        var atlasConfig = new JSONObject(File.FILE.readStringFromFile(atlasSrc));

        var atlasSprites = atlasConfig.getJSONObject("sprites");
        var atlasIterator = atlasSprites.keys();

        System.out.println(textureData.width);
        System.out.println(textureData.height);

        while(atlasIterator.hasNext()) {
            var atlasKey = atlasIterator.next();
            var atlasEntry = atlasSprites.getJSONArray(atlasKey);

            var x = atlasEntry.optInt(0);
            var y = atlasEntry.optInt(1);
            var w = atlasEntry.optInt(2);
            var h = atlasEntry.optInt(3);

            this.spriteMap.put(atlasKey, new Sprite(
                new Vector2f((float)x/textureData.width, (float)y/textureData.height),
                new Vector2f((float)x/textureData.width, (float)(y+h)/textureData.height),
                new Vector2f((float)(x+w)/textureData.width, (float)(y+h)/textureData.height),
                new Vector2f((float)(x+w)/textureData.width, (float)y/textureData.height)
            ));
        }


        this.atlasID = GL20.glGenTextures();
        GL20.glBindTexture(GL11.GL_TEXTURE_2D, this.atlasID);
        GL20.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
        GL20.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, textureData.width, textureData.height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, textureData.buffer);
        GL20.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL20.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
    }

    public void bind() {
        GL20.glActiveTexture(GL13.GL_TEXTURE0);
        GL20.glBindTexture(GL11.GL_TEXTURE_2D, this.atlasID);
    }

}
