package dreamlink.overlay.component.icon;

import dreamlink.graphics.sprite.template.ISpriteTemplate;

public class StaticIconComponentProvider implements IIconComponentProvider {

    private final ISpriteTemplate sprite;

    public StaticIconComponentProvider(ISpriteTemplate sprite) {
        this.sprite = sprite;
    }

    @Override
    public ISpriteTemplate getSprite() {
        return this.sprite;
    }
    
}
