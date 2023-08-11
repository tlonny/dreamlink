package doors.graphics.texture;

import org.lwjgl.opengl.GL42;

import doors.graphics.shader.Shader;
import doors.graphics.texture.channel.AbstractTextureChannel;
import doors.utility.vector.Vector2in;

public abstract class AbstractTexture {

    private AbstractTextureChannel textureChannel;
    public int textureID;

    public AbstractTexture(AbstractTextureChannel textureChannel) {
        this.textureChannel = textureChannel;
        this.textureID = GL42.glGenTextures();

        GL42.glActiveTexture(GL42.GL_TEXTURE0);
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MIN_FILTER, GL42.GL_NEAREST);
        GL42.glTexParameteri(GL42.GL_TEXTURE_2D, GL42.GL_TEXTURE_MAG_FILTER, GL42.GL_NEAREST);
    }
    
    public abstract Vector2in getDimensions();

    public void useTexture() {
        if(this.textureChannel.registeredTexture == this) {
            return;
        }

        this.textureChannel.registeredTexture = this;
        GL42.glActiveTexture(this.textureChannel.getTextureUnitGLID());
        GL42.glBindTexture(GL42.GL_TEXTURE_2D, this.textureID);
        Shader.SHADER.setTextureDimensions(
            this.textureChannel.getTextureUnitID(), 
            this.getDimensions()
        );
    }

}
