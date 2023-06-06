#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 normal;
layout (location = 2) in vec2 texture;

uniform mat4 projection_matrix;
uniform mat4 view_rotation_matrix;
uniform mat4 view_translation_matrix;
uniform mat4 model_matrix;

out vec2 vert_texture;

void main()
{
    vert_texture = vec2(texture.x, 1.0 - texture.y);
    gl_Position = projection_matrix
        * view_rotation_matrix
        * view_translation_matrix
        * model_matrix 
        * vec4(position, 1.0);
}
