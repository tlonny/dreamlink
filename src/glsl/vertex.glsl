#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 normal;
layout (location = 2) in int light;
layout (location = 3) in int color;
layout (location = 4) in vec2 texture;

uniform mat4 projection_matrix;
uniform mat4 view_rotation_matrix;
uniform mat4 view_translation_matrix;
uniform mat4 model_matrix;
uniform float global_light_direction_factor;

out float vert_local_light;
out float vert_global_light;
out vec3 vert_color;
out vec2 vert_texture;

const vec3 GLOBAL_LIGHT_DIRECTION = normalize(vec3(1.0, 1.0, 1.0));

void main()
{
    float global_light = (light & 0xFF) / 255.0;
    float local_light = (light / 0x100) / 255.0;

    float blue_color = (color & 0xFF) / 255.0;
    int _color = color / 0x100;
    float green_color = (_color & 0xFF) / 255.0;
    float red_color = (_color / 0x100) / 255.0;

    float non_directional_global_light_factor = (1 - global_light_direction_factor);
    float directional_global_light_factor = global_light_direction_factor * dot(GLOBAL_LIGHT_DIRECTION, normal);
    float global_light_factor = non_directional_global_light_factor + directional_global_light_factor;

    vert_local_light = local_light;
    vert_global_light = global_light * global_light_factor;
    vert_color = vec3(red_color, green_color, blue_color);
    vert_texture = texture;

    gl_Position = projection_matrix * view_rotation_matrix * view_translation_matrix * model_matrix * vec4(position, 1.0);
}
