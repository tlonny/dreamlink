package dreamlink.overlay.component.list.view;

import dreamlink.graphics.sprite.template.ISpriteTemplate;

public class ListViewRowData {

    public boolean isUsed;
    public ISpriteTemplate icon;
    public String text;
    public boolean isHighlighted;

    public ListViewRowData set(
        ISpriteTemplate icon,
        String text,
        boolean isHighlighted
    ) {
        this.isUsed = true;
        this.text = text;
        this.icon = icon;
        this.isHighlighted = isHighlighted;
        return this;
    }

    public ListViewRowData clear() {
        this.isUsed = false;
        return this;
    }
    
}
