package dreamlink.graphics.program;

import dreamlink.graphics.texture.TextureUnit;

public class ShaderProgramSampler {

    public static final ShaderProgramSampler entity = new ShaderProgramSampler(
        TextureUnit.entity,
        "sampler_entity"
    );

    public static final ShaderProgramSampler room = new ShaderProgramSampler(
        TextureUnit.room,
        "sampler_room"
    );

    public static final ShaderProgramSampler overlay = new ShaderProgramSampler(
        TextureUnit.menu,
        "sampler_overlay"
    );

    public static final ShaderProgramSampler portal = new ShaderProgramSampler(
        TextureUnit.portal,
        "sampler_portal"
    );

    private static final ShaderProgramSampler[] samplers = new ShaderProgramSampler[] {
        ShaderProgramSampler.entity,
        ShaderProgramSampler.room,
        ShaderProgramSampler.overlay,
        ShaderProgramSampler.portal
    };

    public static int size() {
        return samplers.length;
    }

    public static ShaderProgramSampler get(int index) {
        return samplers[index];
    }

    public final TextureUnit textureUnit;
    public final String samplerName;

    public ShaderProgramSampler(TextureUnit textureUnit, String samplerName) {
        this.textureUnit = textureUnit;
        this.samplerName = samplerName;
    }
    
}
