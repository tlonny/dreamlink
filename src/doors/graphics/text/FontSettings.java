package doors.graphics.text;

import doors.utility.vector.Vector3fl;

public class FontSettings {

    public Vector3fl color = new Vector3fl();
    public FontDecoration fontDecoration = FontDecoration.NORMAL; 

    public void clear() {
        this.color.set(0);
        this.fontDecoration = FontDecoration.NORMAL;
    }
    
}
