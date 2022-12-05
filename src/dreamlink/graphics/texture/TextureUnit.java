package dreamlink.graphics.texture;

public class TextureUnit {

    public static final TextureUnit entity = new TextureUnit();
    public static final TextureUnit room = new TextureUnit();
    public static final TextureUnit menu = new TextureUnit();
    public static final TextureUnit portal = new TextureUnit();
    public static final TextureUnit working = new TextureUnit();

    private static final TextureUnit[] values = new TextureUnit[] {
        entity,
        room,
        menu,
        portal,
        working
    };

    public static int getSize() {
        return TextureUnit.values.length;
    }

    public static TextureUnit get(int index) {
        return TextureUnit.values[index];
    }

    public static void setup() {
        for(var ix = 0; ix < TextureUnit.getSize(); ix += 1) {
            TextureUnit.get(ix).index = ix;
        }
    }

    private int index;

    public int getIndex() {
        return this.index;
    }

    
}
