#version 330

in vec4 vert_position;
in vec2 vert_texture_offset;
in vec3 vert_color;
flat in int vert_texture_unit;

out vec4 frag_color;

uniform sampler2D sampler_0;
uniform sampler2D sampler_1;
uniform sampler2D sampler_2;
uniform sampler2D sampler_3;
uniform sampler2D sampler_4;
uniform sampler2D sampler_5;
uniform sampler2D sampler_6;
uniform sampler2D sampler_7;
uniform sampler2D sampler_8;
uniform sampler2D sampler_9;
uniform int texture_sample_mode;
uniform vec3 color;
uniform vec2 texture_dimensions[10];

const int TEXTURE_SAMPLE_MODE_NORMAL = 0;
const int TEXTURE_SAMPLE_MODE_SCREEN = 1;

vec4 sample(vec2 offset)
{
    switch(vert_texture_unit)
    {
        case 0:
            return texture(sampler_0, offset);
        case 1:
            return texture(sampler_1, offset);
        case 2:
            return texture(sampler_2, offset);
        case 3:
            return texture(sampler_3, offset);
        case 4:
            return texture(sampler_4, offset);
        case 5:
            return texture(sampler_5, offset);
        case 6:
            return texture(sampler_6, offset);
        case 7:
            return texture(sampler_7, offset);
        case 8:
            return texture(sampler_8, offset);
        case 9:
            return texture(sampler_9, offset);
        default:
            return vec4(1.0, 0.0, 0.0, 1.0);
    }
}

void main()
{
    vec2 tex_offset;
    if(texture_sample_mode == TEXTURE_SAMPLE_MODE_NORMAL)
    {
        tex_offset = vert_texture_offset;
    }
    else
    {
        tex_offset = vert_position.xy / vert_position.w * 0.5 + 0.5;
    }

    vec4 tex_color = sample(tex_offset);
    frag_color = tex_color * vec4(vert_color, 1.0) * vec4(color, 1.0);

    if(frag_color.a < 0.5) {
        discard;
    }
}
