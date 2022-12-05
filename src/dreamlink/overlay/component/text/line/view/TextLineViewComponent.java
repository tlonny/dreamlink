package dreamlink.overlay.component.text.line.view;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.graphics.sprite.SpriteBatch;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.EmptyComponent;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.text.CharacterData;

public class TextLineViewComponent extends WrapperComponent {

    private final IComponent component;
    private final ITextLineViewComponentProvider provider;
    private final Alignment alignment;
    private int visibleCharacterCount;

    public TextLineViewComponent(ITextLineViewComponentProvider provider, Alignment alignment) {
        this.provider = provider;
        this.alignment = alignment;
        this.component = new BoxComponent(
            new EmptyComponent(),
            BoxDimension.min(MenuTextureSample.glyphDimensions.x()),
            BoxDimension.fixed(MenuTextureSample.glyphDimensions.y())
        );
    }

    public int getVisibleCharacterCount() {
        return this.visibleCharacterCount;
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    @Override
    public void computeDimensions(Vector2ic availableSpace) {
        this.visibleCharacterCount = availableSpace.x() / MenuTextureSample.glyphDimensions.x();
        super.computeDimensions(
            new Vector2i(
                this.visibleCharacterCount * MenuTextureSample.glyphDimensions.x(),
                availableSpace.y()
        ));
    }

    private final Vector2i writeToSpriteBatchCharacterPosition = new Vector2i();
    private final Vector2i writeToSpriteBatchDimensions = new Vector2i();
    private final CharacterData writeToSpriteBatchCharacterData = new CharacterData();

    @Override
    public void writeToSpriteBatch(SpriteBatch spriteBatch) {
        super.writeToSpriteBatch(spriteBatch);
        var length = this.provider.getCharacterSize();
        var characterPosition = this.getPosition(this.writeToSpriteBatchCharacterPosition);
        characterPosition.x += this.alignment.getOffset(
            this.getDimensions(this.writeToSpriteBatchDimensions).x,
            length * MenuTextureSample.glyphDimensions.x()
        );

        for(var ix = 0; ix < this.visibleCharacterCount; ix += 1) {
            var offsetIndex = this.provider.getCharacterOffset() + ix;
            if(offsetIndex >= length) {
                break;
            }

            this.provider.setCharacterState(offsetIndex, this.writeToSpriteBatchCharacterData);
            if(this.writeToSpriteBatchCharacterData.isHighlighted) {
                spriteBatch.writeTextureSample(
                    characterPosition, 
                    MenuTextureSample.glyphDimensions, 
                    this.getSpriteHeight(),
                    MenuTextureSample.white,
                    this.writeToSpriteBatchCharacterData.highlightColor
                );
            }

            spriteBatch.writeTextureSample(
                characterPosition, 
                MenuTextureSample.glyphDimensions, 
                this.getSpriteHeight(),
                this.writeToSpriteBatchCharacterData.getGlyph(),
                this.writeToSpriteBatchCharacterData.color
            );

            characterPosition.x += MenuTextureSample.glyphDimensions.x();
        }
    }
}
