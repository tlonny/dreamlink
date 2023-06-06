#version 330

in vec2 vert_texture;

out vec4 frag_color;

uniform sampler2D texture_sampler;
uniform vec3 color;

void main()
{
    frag_color = texture(texture_sampler, vert_texture) 
        * vec4(color, 1.0);

    if(frag_color.a < 0.5)
        discard;
}
