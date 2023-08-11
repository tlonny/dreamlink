package doors.graphics.texture.channel;

import org.lwjgl.opengl.GL42;

import doors.graphics.texture.AbstractTexture;

public abstract class AbstractTextureChannel {

    public int textureChannelID;
    public AbstractTexture registeredTexture = null;

    public abstract int getTextureUnitID();

    public int getTextureUnitGLID() {
        return this.getTextureUnitID() + GL42.GL_TEXTURE0;
    }

}
