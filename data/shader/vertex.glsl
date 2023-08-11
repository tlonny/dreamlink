#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 normal;
layout (location = 2) in vec2 texture;
layout (location = 3) in int packed_color;
layout (location = 4) in int packed_lookup_ids;

uniform mat4 view_projection_matrix;
uniform mat4 view_rotation_matrix;
uniform mat4 view_translation_matrix;
uniform mat4 model_matrix;
uniform mat4 cube_transformer_matrices[64];
uniform vec2 texture_dimensions[10];

out vec2 vert_texture_offset;
out vec3 vert_color;
out vec4 vert_position;
flat out int vert_texture_unit;

void main()
{
    float color_blue = float(packed_color & 0xFF) / 255.0;
    float color_green = float((packed_color >> 8) & 0xFF) / 255.0;
    float color_red = float((packed_color >> 16) & 0xFF) / 255.0;

    int transformer_id = packed_lookup_ids & 0xFF;
    vert_texture_unit = (packed_lookup_ids >> 8) & 0xFF;

    mat4 transformer_matrix = cube_transformer_matrices[transformer_id];

    vert_texture_offset = vec2(
        texture.x / texture_dimensions[vert_texture_unit].x, 
        1.0 - texture.y / texture_dimensions[vert_texture_unit].y
    );
    
    vert_color = vec3(color_red, color_green, color_blue);

    vert_position = view_projection_matrix
        * view_rotation_matrix
        * view_translation_matrix
        * model_matrix 
        * transformer_matrix
        * vec4(position, 1.0);

    gl_Position = vert_position;
}
