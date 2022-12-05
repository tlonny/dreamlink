package dreamlink.graphics.texture;

import java.io.IOException;

import org.joml.Vector2i;

import dreamlink.utility.png.PNGDecodeException;
import dreamlink.utility.png.PNGFns;

public class MenuTexture extends Texture {

    private static final String texturePath = "texture/menu.png";

    public static final MenuTexture instance = new MenuTexture();

    @Override
    public void setup() {
        super.setup();
        var classLoader = this.getClass().getClassLoader();
        try(var stream = classLoader.getResourceAsStream(MenuTexture.texturePath)) {
            var dimensions = new Vector2i();
            var data = PNGFns.fromStream(stream, dimensions);
            TextureImageLoader.instance.buffer(this, data, dimensions);
        } catch (PNGDecodeException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
