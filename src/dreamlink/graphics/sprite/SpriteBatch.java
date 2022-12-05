package dreamlink.graphics.sprite;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.joml.Vector2ic;
import org.joml.Vector4fc;

import dreamlink.graphics.mesh.sprite.SpriteMesh;
import dreamlink.graphics.mesh.sprite.SpriteMeshBuffer;
import dreamlink.graphics.texture.sample.TextureSample;
import dreamlink.utility.pool.Resource;
import dreamlink.utility.pool.ResourcePool;

public class SpriteBatch {

    private static int getSpriteHeight(Resource<Sprite> sprite) {
        return sprite.value.getSpriteHeight();
    }

    private static Comparator<Resource<Sprite>> fragmentComparator = Comparator.comparing(SpriteBatch::getSpriteHeight);

    private final ResourcePool<Sprite> spritePool = new ResourcePool<>(Sprite::new);
    private final List<Resource<Sprite>> sprites = new ArrayList<>();
    private final SpriteMesh mesh = new SpriteMesh();

    public void setup() {
        this.mesh.setup();
    }

    public int getSpriteCount() {
        return this.sprites.size();
    }

    public void clear() {
        for(var sprite : this.sprites) {
            sprite.close();
        }
        this.sprites.clear();
    }

    public void writeTextureSample(
        Vector2ic position,
        Vector2ic dimensions,
        SpriteHeight height,
        TextureSample textureSample,
        Vector4fc color
    ) {
        var sprite = this.spritePool.acquire();
        this.sprites.add(sprite);
        sprite.value.set(
            position,
            dimensions,
            height,
            textureSample,
            color
        );
    }

    public void buffer() {
        this.sprites.sort(SpriteBatch.fragmentComparator);
        var meshBuffer = SpriteMeshBuffer.instance.clear();
        for(var sprite : this.sprites) {
            sprite.value.writeToMeshBuffer(meshBuffer);
        }
        this.mesh.buffer(meshBuffer);
    }

    public void render() {
        this.mesh.render();
    }

    public void destroy() {
        this.mesh.destroy();
    }

}
