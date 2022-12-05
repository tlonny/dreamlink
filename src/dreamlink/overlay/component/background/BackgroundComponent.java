package dreamlink.overlay.component.background;

import org.joml.Vector2i;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;

public class BackgroundComponent extends WrapperComponent {

    private final IBackgroundComponentProvider provider;
    private final IComponent component;

    public BackgroundComponent(
        IBackgroundComponentProvider provider,
        IComponent component
    ) {
        this.provider = provider;
        this.component = component;
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    private final Vector2i writeToSpriteBatchDimensions = new Vector2i();
    private final Vector2i writeToSpriteBatchPosition = new Vector2i();

    @Override
    public void writeToSpriteBatch(SpriteBatch spriteBatch) {
        var sprite = this.provider.getSprite();
        if(sprite == null) {
            return;
        }

        sprite.writeToSpriteBatch(
            spriteBatch,
            this.getPosition(this.writeToSpriteBatchPosition),
            this.getDimensions(this.writeToSpriteBatchDimensions),
            this.getSpriteHeight()
        );

        super.writeToSpriteBatch(spriteBatch);
    }
    
}
