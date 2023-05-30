#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 normal;
layout (location = 2) in vec2 texture;

uniform mat4 projection_matrix;
uniform mat4 view_rotation_matrix;
uniform mat4 view_translation_matrix;
uniform mat4 model_matrix;
uniform vec3 ambient_light;

out vec3 vert_color;
out vec2 vert_texture;

const vec3 GLOBAL_LIGHT_DIRECTION = normalize(vec3(1.0, 1.0, 1.0));
const float LIGHT_DIRECTION_FACTOR = 0.5;

void main()
{
    float non_directional_light_factor = (1 - LIGHT_DIRECTION_FACTOR);
    float directional_light_factor = LIGHT_DIRECTION_FACTOR * dot(GLOBAL_LIGHT_DIRECTION, normal);
    float light_factor = non_directional_light_factor + directional_light_factor;

    vert_color = ambient_light * light_factor;
    vert_texture = texture;

    gl_Position = projection_matrix * view_rotation_matrix * view_translation_matrix * model_matrix * vec4(position, 1.0);
}
