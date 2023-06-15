#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 normal;
layout (location = 2) in vec2 texture;
layout (location = 3) in int packed_color_texture_unit;

uniform mat4 projection_matrix;
uniform mat4 view_rotation_matrix;
uniform mat4 view_translation_matrix;
uniform mat4 model_matrix;

out vec2 vert_texture_offset;
out vec3 vert_color;
flat out int vert_texture_unit;

void main()
{
    float color_blue = float(packed_color_texture_unit & 0xFF) / 255.0;
    float color_green = float((packed_color_texture_unit >> 8) & 0xFF) / 255.0;
    float color_red = float((packed_color_texture_unit >> 16) & 0xFF) / 255.0;

    vert_texture_unit = packed_color_texture_unit >> 24;
    vert_texture_offset = vec2(texture.x, 1.0 - texture.y);
    vert_color = vec3(color_red, color_green, color_blue);

    gl_Position = projection_matrix
        * view_rotation_matrix
        * view_translation_matrix
        * model_matrix 
        * vec4(position, 1.0);
}
