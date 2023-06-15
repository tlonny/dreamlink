#version 330

in vec2 vert_texture_offset;
in vec3 vert_color;
flat in int vert_texture_unit;

out vec4 frag_color;

uniform sampler2D world_render_sampler;
uniform sampler2D portal_render_sampler;
uniform sampler2D overlay_sampler;
uniform sampler2D terrain_sampler;
uniform sampler2D entity_sampler;
uniform vec3 color;

void main()
{
    vec4 tex_color;
    switch(vert_texture_unit)
    {
        case 1:
            tex_color = texture(world_render_sampler, vert_texture_offset);
            break;
        case 2:
            tex_color = texture(portal_render_sampler, vert_texture_offset);
            break;
        case 3:
            tex_color = texture(overlay_sampler, vert_texture_offset);
            break;
        case 4:
            tex_color = texture(terrain_sampler, vert_texture_offset);
            break;
        case 5:
            tex_color = texture(entity_sampler, vert_texture_offset);
            break;
        default:
            tex_color = vec4(1.0, 0.0, 0.0, 1.0);
            break;
    }

    frag_color = tex_color * vec4(vert_color, 1.0) * vec4(color, 1.0);

    if(frag_color.a < 0.5)
        discard;
}
