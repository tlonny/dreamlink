package dreamlink.graphics.sprite;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector2ic;
import org.joml.Vector4f;
import org.joml.Vector4fc;

import dreamlink.graphics.mesh.sprite.SpriteMeshBuffer;
import dreamlink.graphics.mesh.sprite.SpriteQuadData;
import dreamlink.graphics.texture.sample.TextureSample;
import dreamlink.window.Window;

public class Sprite {

    private final Vector2i position;
    private final Vector2i dimensions;
    private final Vector4f color;

    private SpriteHeight spriteHeight;
    private TextureSample textureSample;

    public Sprite() {
        this.position = new Vector2i();
        this.dimensions = new Vector2i();
        this.color = new Vector4f();
    }

    public int getSpriteHeight() {
        return this.spriteHeight.getHeight();
    }

    public Sprite set(
        Vector2ic position, 
        Vector2ic dimensions, 
        SpriteHeight height,
        TextureSample textureSample, 
        Vector4fc color
    ) {
        this.position.set(position);
        this.dimensions.set(dimensions);
        this.spriteHeight = height;
        this.textureSample = textureSample;
        this.color.set(color);
        this.spriteHeight = height;
        return this;
    }

    private final Vector2f writeToMeshBufferResolution = new Vector2f();
    private final Vector2f writeToMeshBufferPosition = new Vector2f();
    private final Vector2f writeToMeshBufferDimensions = new Vector2f();

    public void writeToMeshBuffer(SpriteMeshBuffer meshBuffer) {
        var resolution = Window.instance.getResolution(this.writeToMeshBufferResolution);
        var invertedY = resolution.y - this.position.y - this.dimensions.y;

        var spritePosition = this.writeToMeshBufferPosition.set(
            (float)this.position.x / resolution.x * 2f - 1f,
            (float)invertedY / resolution.y * 2f - 1f
        );

        var spriteDimensions = this.writeToMeshBufferDimensions.set(
            (float)this.dimensions.x / resolution.x * 2f, 
            (float)this.dimensions.y / resolution.y * 2f
        );

        meshBuffer.addQuad(
            new SpriteQuadData().set(
                spritePosition,
                spriteDimensions,
                this.textureSample,
                this.color
            )
        );
    }
    
}
