package doors.graphics.text;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.spritebatch.SpriteBatchHeight;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class TextFragment {

    private static Queue<ConfiguredGlyph> CHARACTER_SETTINGS_QUEUE = new ArrayDeque<>();

    private Collection<ConfiguredGlyph> glyphs = new ArrayList<>();
    private Vector2in positionCursor = new Vector2in();

    private ConfiguredGlyph getFreeConfiguredGlyph() {
        if(CHARACTER_SETTINGS_QUEUE.isEmpty()) {
            return new ConfiguredGlyph();
        }

        return CHARACTER_SETTINGS_QUEUE.poll();
    }

    public void clear() {
        for(var glyph : this.glyphs) {
            CHARACTER_SETTINGS_QUEUE.add(glyph);
        }
        this.glyphs.clear();
    }

    public int getLength() {
        return this.glyphs.size();
    }

    public void pushCharacter(char character, FontDecoration fontDecoration, Vector3fl color, Vector3fl highlightColor) {
        var configuredGlyph = this.getFreeConfiguredGlyph();
        configuredGlyph.glyph = GlyphLookup.GLYPH_LOOKUP.getGlyph(character);
        configuredGlyph.color.set(color);
        configuredGlyph.highlightColor.set(highlightColor);
        configuredGlyph.fontDecoration = fontDecoration;
        this.glyphs.add(configuredGlyph);
    }

    public void pushCharacter(char character, FontDecoration fontDecoration, Vector3fl color) {
        this.pushCharacter(character, fontDecoration, color, Vector3fl.BLACK);
    }

    public void pushText(String text, FontDecoration fontDecoration, Vector3fl color, Vector3fl highlightColor) {
        for(int ix = 0; ix < text.length(); ix += 1) {
            this.pushCharacter(text.charAt(ix), fontDecoration, color, highlightColor);
        }
    }

    public void pushText(String text, FontDecoration fontDecoration, Vector3fl color) {
        this.pushText(text, fontDecoration, color, Vector3fl.BLACK);
    }

    public void writeTextFragmentToSpriteBatch(
        SpriteBatch spriteBatch,
        Vector2in position,
        SpriteBatchHeight height
    ) {
        this.positionCursor.set(position);
        for(var configuredGlyph : this.glyphs) {
            configuredGlyph.writeConfiguredGlyphToSpriteBatch(
                spriteBatch,
                this.positionCursor,
                height
            );
            this.positionCursor.x += Glyph.GLYPH_DIMENSIONS.x;
        }
    }


    
}
