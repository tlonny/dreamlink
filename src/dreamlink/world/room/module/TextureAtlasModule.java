package dreamlink.world.room.module;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.joml.Vector2i;

import dreamlink.graphics.texture.TextureImageLoader;
import dreamlink.utility.png.PNGDecodeException;
import dreamlink.utility.png.PNGFns;
import dreamlink.graphics.texture.ITexture;
import dreamlink.graphics.texture.Texture;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.RoomLoadException;

public class TextureAtlasModule {

    private static final Path atlasPath = Paths.get("pack/texture/atlas.png");

    private final IRoomModuleProvider provider;
    private final Texture texture = new Texture();
    private final Vector2i atlasDimensions = new Vector2i();
    private byte[] atlasData;
    private boolean isWorkCompleted;

    public TextureAtlasModule(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    public ITexture getTexture() {
        return this.texture;
    }

    public Vector2i getDimensions(Vector2i target) {
        return target.set(this.atlasDimensions);
    }

    public void loadData() throws RoomLoadException {
        var roomPath = this.provider.getIOModule().getPath();
        var textureFile = roomPath
            .resolve(TextureAtlasModule.atlasPath)
            .toFile();

        try {
            if(textureFile.exists()) {
                this.atlasData = PNGFns.fromFile(textureFile, this.atlasDimensions);
            } else {
                throw RoomLoadException.missingTextureAtlas();
            }
        } catch (PNGDecodeException e) {
            throw RoomLoadException.invalidTextureAtlas();
        } catch (IOException e) {
            throw RoomLoadException.invalidTextureAtlas();
        }
    }

    public boolean doWork() {
        if(this.isWorkCompleted) {
            return false;
        }

        this.texture.setup();
        TextureImageLoader.instance.buffer(
            this.texture, 
            this.atlasData, 
            this.atlasDimensions
        );
        this.atlasData = null;
        this.isWorkCompleted = true;
        return true;
    }

    public void destroy() {
        if(this.isWorkCompleted) {
            this.texture.destroy();
        }
    }
}