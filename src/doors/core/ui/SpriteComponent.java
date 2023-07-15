package doors.core.ui;

import doors.core.graphics.mesh.MeshBuffer;
import doors.core.graphics.sprite.ISprite;
import doors.utility.vector.Vector2in;

public class SpriteComponent extends AbstractUIComponent {

    private ISprite sprite;
    private UILayer layer;

    public SpriteComponent(AbstractUIRoot root, ISprite sprite, UILayer layer) {
        super(root);
        this.sprite = sprite;
        this.layer = layer;
    }

    public SpriteComponent(AbstractUIRoot root, ISprite sprite) {
        this(root, sprite, UILayer.NORMAL);
    }

    @Override
    public Vector2in getDimensions() {
        return this.sprite.getDimensions();
    }

    @Override
    public UILayer getLayer() {
        return this.layer;
    }

    @Override
    public void writeUIComponent(MeshBuffer meshBuffer) {
        this.sprite.writeSprite(meshBuffer, this.position);
    }
    
}
