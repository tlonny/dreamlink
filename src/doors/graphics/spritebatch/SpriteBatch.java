package doors.graphics.spritebatch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

import doors.Config;
import doors.graphics.mesh.MeshBuffer;
import doors.graphics.texture.TextureSample;
import doors.utility.CubeFace;
import doors.utility.Orientation;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class SpriteBatch {

    private static Queue<Sprite> UNUSED_SPRITES = new ArrayDeque<>();

    private Vector3fl worldPosition = new Vector3fl();
    private Vector3fl worldDimensions = new Vector3fl();

    public List<Sprite> batchedSprites = new ArrayList<>();

    private Sprite getNewSprite() {
        if(UNUSED_SPRITES.isEmpty()) {
            return new Sprite();
        } 
        return UNUSED_SPRITES.remove();
    }

    public void pushSprite(TextureSample textureSample, Vector2in position, Vector2in dimensions, SpriteBatchHeight height, Vector3fl color) {
        var sprite = this.getNewSprite();
        sprite.textureSample = textureSample;
        sprite.position.set(position);
        sprite.dimensions.set(dimensions);
        sprite.height = height;
        sprite.color.set(color);
        batchedSprites.add(sprite);
    }

    private int getSpriteHeight(Sprite sprite) {
        return sprite.height.height;
    }

    public void clear() {
        for(var sprite : this.batchedSprites) {
            UNUSED_SPRITES.add(sprite);
        }
        this.batchedSprites.clear();
    }

    public void writeSpriteBatchToMeshBuffer(MeshBuffer meshBuffer) {
        this.batchedSprites.sort(Comparator.comparing(this::getSpriteHeight));

        for(var sprite : this.batchedSprites) {
            this.worldPosition.set(
                sprite.position.getFloatX() / Config.CONFIG.getResolution().x * 2f - 1f,
                (Config.CONFIG.getResolution().y - sprite.position.getFloatY() - sprite.dimensions.getFloatY()) / Config.CONFIG.getResolution().y * 2f - 1f, 
                1f
            );

            this.worldDimensions.set(
                sprite.dimensions.getFloatX() / Config.CONFIG.getResolution().x * 2f, 
                sprite.dimensions.getFloatY() / Config.CONFIG.getResolution().y * 2f,
                0f
            );

            meshBuffer.pushQuad(
                sprite.textureSample, 
                0,
                worldPosition,
                Orientation.FRONT,
                worldDimensions,
                CubeFace.FRONT, 
                sprite.color
            );
        }
    }
    
}
