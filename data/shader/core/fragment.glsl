#version 330

in vec4 vert_position;
in vec2 vert_texture_offset;
in vec3 vert_color;
flat in int vert_texture_unit;

out vec4 frag_color;

uniform sampler2D ui_sampler;
uniform sampler2D entity_sampler;
uniform sampler2D terrain_sampler;
uniform sampler2D portal_sampler;
uniform sampler2D world_sampler;
uniform vec3 color;

const int UI_TEXTURE_UNIT = 1;
const int ENTITY_TEXTURE_UNIT = 2;
const int TERRAIN_TEXTURE_UNIT = 3;
const int PORTAL_TEXTURE_UNIT = 4;
const int WORLD_TEXTURE_UNIT = 5;

vec4 sample(vec2 offset)
{
    switch(vert_texture_unit)
    {
        case UI_TEXTURE_UNIT:
            return texture(ui_sampler, offset);
        case ENTITY_TEXTURE_UNIT:
            return texture(entity_sampler, offset);
        case TERRAIN_TEXTURE_UNIT:
            return texture(terrain_sampler, offset);
        case PORTAL_TEXTURE_UNIT:
            return texture(portal_sampler, offset);
        case WORLD_TEXTURE_UNIT:
            return texture(world_sampler, offset);
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

    if(frag_color.a < 0.5)
        discard;
}
