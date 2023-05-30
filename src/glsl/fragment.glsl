#version 330

in vec3 vert_color;
in vec2 vert_texture;

out vec4 frag_color;

uniform sampler2D texture_sampler;

void main()
{
    frag_color = texture(texture_sampler, vert_texture) * vec4(
        vert_color.x,
        vert_color.y,
        vert_color.z,
        1.0
    );

    if(frag_color.a < 0.5)
        discard;
}
