package dreamlink.overlay.component.list;

import dreamlink.graphics.sprite.template.ISpriteTemplate;

public class ListRowData {

    public String text;
    public ISpriteTemplate icon;

    public ListRowData set(
        ISpriteTemplate icon,
        String text
    ) {
        this.icon = icon;
        this.text = text;
        return this;
    }
    
}
