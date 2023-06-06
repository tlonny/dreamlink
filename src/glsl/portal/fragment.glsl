#version 330

in vec4 vert_projected;

out vec4 frag_color;

uniform sampler2D texture_sampler;
uniform vec3 color;

void main()
{
    vec2 frag_texture = vert_projected.xy 
        / vert_projected.w * 0.5 + 0.5;

    frag_color = texture(texture_sampler, frag_texture) 
        * vec4(color, 1.0);

    if(frag_color.a < 0.5)
        frag_color.a = 1.0;
}
