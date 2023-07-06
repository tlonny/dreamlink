#version 330

in vec4 vert_position;
in vec2 vert_texture_offset;
in vec3 vert_color;
flat in int vert_texture_unit;

out vec4 frag_color;

uniform sampler2D font_sampler;
uniform sampler2D ui_sampler;
uniform sampler2D entity_sampler;
uniform sampler2D block_sampler;
uniform sampler2D portal_sampler;
uniform sampler2D current_sampler;
uniform vec3 color;

const int FONT_TEXTURE_UNIT = 1;
const int UI_TEXTURE_UNIT = 2;
const int ENTITY_TEXTURE_UNIT = 3;
const int BLOCK_TEXTURE_UNIT = 4;
const int PORTAL_TEXTURE_UNIT = 5;
const int CURRENT_TEXTURE_UNIT = 6;

vec4 sample(vec2 offset)
{
    switch(vert_texture_unit)
    {
        case FONT_TEXTURE_UNIT:
            return texture(font_sampler, offset);
        case UI_TEXTURE_UNIT:
            return texture(ui_sampler, offset);
        case ENTITY_TEXTURE_UNIT:
            return texture(entity_sampler, offset);
        case BLOCK_TEXTURE_UNIT:
            return texture(block_sampler, offset);
        case PORTAL_TEXTURE_UNIT:
            return texture(portal_sampler, offset);
        case CURRENT_TEXTURE_UNIT:
            return texture(current_sampler, offset);
        default:
            return vec4(1.0, 0.0, 0.0, 1.0);
    }
}

void main()
{
    vec2 tex_offset = vert_texture_offset;
    if(vert_texture_unit == PORTAL_TEXTURE_UNIT)
    {
        tex_offset = vert_position.xy / vert_position.w * 0.5 + 0.5;
    }

    vec4 tex_color = sample(tex_offset);
    frag_color = tex_color * vec4(vert_color, 1.0) * vec4(color, 1.0);

    if(frag_color.a < 0.5) {
        discard;
    }
}
