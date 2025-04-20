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

layout (location = 0) out vec4 frag_color;

in float camera_distance;
in vec4 vert_screen_position;
in vec2 vert_texture_offset;
flat in TextureMetaData vert_texture_metadata;
flat in TerrainMetaData vert_terrain_metadata;

uniform vec3 base_light_color;
uniform vec3 primary_light_color;
uniform vec3 secondary_light_color;
uniform vec3 tertiary_light_color;
uniform vec3 portal_light_color;
uniform vec3 fog_color;

uniform int can_see_hidden_blocks;
uniform int is_lighting_enabled;
uniform int is_fog_enabled;

uniform float fog_start_distance;
uniform float fog_range;

uniform sampler2D sampler_entity;
uniform sampler2D sampler_room;
uniform sampler2D sampler_portal;

const int max_light = 0xF;
const int entity_texture_unit = 0;
const int room_texture_unit = 1;
const int portal_texture_unit = 3;
const float epsilon = 0.0001;

vec4 sample_from_offset(vec2 offset, int unit) {
    switch(unit) {
        case entity_texture_unit:
            return texture(sampler_entity, offset);
        case room_texture_unit:
            return texture(sampler_room, offset);
        case portal_texture_unit:
            vec2 screen_offset = vert_screen_position.xy / vert_screen_position.w * 0.5 + 0.5;
            return texture(sampler_portal, screen_offset);
        default:
            return vec4(1.0, 0.0, 0.0, 1.0);
    }
}

void main() {
    frag_color = sample_from_offset(vert_texture_offset, vert_texture_metadata.texture_unit_id);

    vec3 terrain_light = vec3(base_light_color);

    float primary_light_level = float(vert_terrain_metadata.primary_light) / max_light;
    vec3 primary_light = primary_light_color * primary_light_level;
    terrain_light = max(terrain_light, primary_light);

    float secondary_light_level = float(vert_terrain_metadata.secondary_light) / max_light;
    vec3 secondary_light = secondary_light_color * secondary_light_level;
    terrain_light = max(terrain_light, secondary_light);

    float tertiary_light_level = float(vert_terrain_metadata.tertiary_light) / max_light;
    vec3 tertiary_light = tertiary_light_color * tertiary_light_level;
    terrain_light = max(terrain_light, tertiary_light);

    float portal_light_level = float(float(vert_terrain_metadata.portal_light) / max_light);
    vec3 portal_light = portal_light_color * portal_light_level;
    terrain_light = max(terrain_light, portal_light);
    
    if(vert_terrain_metadata.is_affected_by_light == 1 && is_lighting_enabled == 1) {
        frag_color.rgb *= terrain_light;
    }

    if(is_fog_enabled == 1) {
        float fog_factor = clamp((camera_distance - fog_start_distance) / (fog_range + epsilon), 0.0, 1.0);
        frag_color.rgb = frag_color.rgb * (1.0 - fog_factor) + fog_color * fog_factor;
    }
    
    if(vert_terrain_metadata.is_hidden == 1 && can_see_hidden_blocks != 1) {
        discard;
    } else if(frag_color.a < epsilon) {
        discard;
    } else {
        frag_color.a = 1.0;
    }
}
