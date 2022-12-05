package dreamlink.graphics.texture;

import org.lwjgl.opengl.GL42;

import dreamlink.graphics.glconfig.TextureConfig;

public class Texture implements ITexture {

    private int textureID;

    public void setup() {
        this.textureID = GL42.glGenTextures();
        try(var textureConfig = new TextureConfig(TextureUnit.working)) {
            textureConfig.setState(this);
            GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MIN_FILTER, GL42.GL_NEAREST);
            GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MAG_FILTER, GL42.GL_NEAREST);
        }
    }

    @Override
    public int getTextureID() {
        return this.textureID;
    }

    public void destroy() {
        GL42.glDeleteTextures(this.textureID);
    }

}
