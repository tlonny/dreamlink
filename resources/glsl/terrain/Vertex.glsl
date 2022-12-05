#version 410

struct TextureMetaData {
    int texture_unit_id;
    int animation_total_frames;
    int animation_row_frames;
    int animation_start_frame;
    float animation_speed;
};

struct TerrainMetaData {
    int primary_light;
    int secondary_light;
    int tertiary_light;
    int portal_light;
    int is_hidden;
    int is_affected_by_light;
    int transformer_index;
};

layout (location = 0) in vec3 position;
layout (location = 1) in vec3 fulcrum;
layout (location = 2) in vec4 texture_offset;
layout (location = 3) in int packed_texture_metadata;
layout (location = 4) in int packed_terrain_metadata;

uniform mat4 view_projection_matrix;
uniform mat4 view_rotation_matrix;
uniform mat4 view_translation_matrix;
uniform mat4 model_matrix;
uniform vec4 clip;
uniform int animation_frame;
uniform mat4 transformer_matrix[4];

out float camera_distance;
out vec4 vert_screen_position;
out vec2 vert_texture_offset;
flat out TextureMetaData vert_texture_metadata;
flat out TerrainMetaData vert_terrain_metadata;

const float min_strides_per_frame = 0.01;
const float max_strides_per_frame = 0.3;

TextureMetaData unpack_texture_metadata(int packed_data) {
    TextureMetaData texture_metadata;
    texture_metadata.texture_unit_id = packed_data & 0xF;
    texture_metadata.animation_total_frames = (packed_data >> 4) & 0xFF;
    texture_metadata.animation_row_frames = (packed_data >> 12) & 0xFF;
    texture_metadata.animation_start_frame = (packed_data >> 20) & 0xFF;
    texture_metadata.animation_speed = (packed_data >> 28) & 0xF;
    texture_metadata.animation_speed /= 0xF;
    return texture_metadata;
}

TerrainMetaData unpack_terrain_metadata(int packed_data) {
    TerrainMetaData terrain_metadata;
    terrain_metadata.primary_light = packed_data & 0xF;
    terrain_metadata.secondary_light = (packed_data >> 4) & 0xF;
    terrain_metadata.tertiary_light = (packed_data >> 8) & 0xF;
    terrain_metadata.portal_light = (packed_data >> 12) & 0xF;
    terrain_metadata.is_hidden = (packed_data >> 16) & 0x1;
    terrain_metadata.is_affected_by_light = (packed_data >> 17) & 0x1;
    terrain_metadata.transformer_index = (packed_data >> 18) & 0x3;
    return terrain_metadata;
}

void main() {
    vert_texture_metadata = unpack_texture_metadata(packed_texture_metadata);
    vert_terrain_metadata = unpack_terrain_metadata(packed_terrain_metadata);
    
    vec4 world_position = model_matrix 
        * transformer_matrix[vert_terrain_metadata.transformer_index]
        * vec4(position - fulcrum, 1.0);
    world_position.xyz += fulcrum;

    vec4 camera_position = view_rotation_matrix
        * view_translation_matrix
        * world_position;
        
    vert_screen_position = view_projection_matrix * camera_position;

    float strides_per_frame = 0.0
        + min_strides_per_frame * (1.0 - vert_texture_metadata.animation_speed) 
        + max_strides_per_frame * vert_texture_metadata.animation_speed;
    int frames_per_stride = int(1.0 / strides_per_frame);

    int offset_animation_frame = vert_texture_metadata.animation_start_frame;
    offset_animation_frame += animation_frame / frames_per_stride;
    offset_animation_frame %= vert_texture_metadata.animation_total_frames;
    vec2 total_stride = vec2(
        texture_offset.z * (offset_animation_frame % vert_texture_metadata.animation_row_frames),
        texture_offset.w * (offset_animation_frame / vert_texture_metadata.animation_row_frames)
    );

    vert_texture_offset = vec2(
        texture_offset.x + total_stride.x, 
        1.0 - texture_offset.y - total_stride.y
    );

    camera_distance = length(camera_position.xyz);
    gl_Position = vert_screen_position;
    gl_ClipDistance[0] = dot(world_position, clip);
}