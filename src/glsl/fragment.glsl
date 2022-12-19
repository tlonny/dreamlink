#version 330

in float vert_local_light;
in float vert_global_light;
in vec3 vert_color;
in vec2 vert_texture;

out vec4 frag_color;

uniform sampler2D texture_sampler;
uniform vec3 global_color;

void main()
{
    vec3 global_light = global_color * vert_global_light;

    frag_color = texture(texture_sampler, vert_texture) * vec4(
        max(vert_local_light, global_light.x) * vert_color.x,
        max(vert_local_light, global_light.y) * vert_color.y,
        max(vert_local_light, global_light.z) * vert_color.z,
        1.0
    );

    if(frag_color.a < 0.5)
        discard;
}
