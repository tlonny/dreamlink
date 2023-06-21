package doors.overlay;

import doors.Config;
import doors.graphics.mesh.Mesh;
import doors.graphics.mesh.MeshBuffer;
import doors.graphics.texture.TextureChannel;
import doors.graphics.texture.TextureSample;
import doors.overlay.font.FontCharacter;
import doors.utility.CubeFace;
import doors.utility.geometry.IVector2fl;
import doors.utility.geometry.Vector2fl;
import doors.utility.geometry.Vector3fl;

public class SpriteBatch {

    private static int MAX_SPRITES = 5_000;
    
    public static SpriteBatch SPRITE_BATCH = new SpriteBatch();

    private Mesh mesh;
    private MeshBuffer meshBuffer;

    private Vector3fl positionBuffer;
    private Vector3fl dimensionsBuffer;

    public SpriteBatch() {
        this.meshBuffer = new MeshBuffer(MAX_SPRITES);
        this.mesh = new Mesh();
        this.positionBuffer = new Vector3fl();
        this.dimensionsBuffer = new Vector3fl();
    }

    public void setup() {
        this.mesh.setup();
    }

    public void updateMesh() {
        this.meshBuffer.flip();
        this.mesh.loadFromMeshBuffer(this.meshBuffer);
        this.meshBuffer.clear();
    }

    public void render() {
        this.mesh.render(Vector3fl.ZERO, Vector3fl.ZERO, Vector3fl.ONE, Vector3fl.ONE);
    }

    public void writeText(String text, IVector2fl position, boolean isUnderlined, Vector3fl color) {
        var cursor = new Vector2fl(position);
        for(var ix = 0; ix < text.length(); ix += 1) {
            var character = text.charAt(ix);
            var fontChar = FontCharacter.FONT_CHARACTER_LOOKUP.get(character);
            var textureSample = isUnderlined ? fontChar.underlineTextureSample : fontChar.textureSample;
            this.writeSprite(
                TextureChannel.FONT_TEXTURE_CHANNEL,
                textureSample,
                cursor,
                textureSample.dimensions,
                color
            );
            cursor.x += textureSample.dimensions.x;
        }
    }

    public void writeSprite(TextureChannel textureChannel, TextureSample textureSample, IVector2fl position, IVector2fl dimensions, Vector3fl color) {
        this.positionBuffer.set(
            position.getFloatX() / Config.RESOLUTION.x * 2f - 1f,
            (Config.RESOLUTION.y - position.getFloatY() - dimensions.getFloatY()) / Config.RESOLUTION.y * 2f - 1f, 
            1f
        );

        this.dimensionsBuffer.set(
            dimensions.getFloatX() / Config.RESOLUTION.x * 2f, 
            dimensions.getFloatY() / Config.RESOLUTION.y * 2f,
            0f
        );

        this.meshBuffer.writeQuad(
            textureSample, 
            textureChannel,
            this.positionBuffer,
            this.dimensionsBuffer,
            CubeFace.FRONT, 
            color
        );
    }

}
