#version 330

in float frag_Local_Light;
in float frag_Global_Light;
in vec2 frag_Texture;

out vec4 frag_Color;

uniform sampler2D texture_Sampler;
uniform vec3 global_Color;

void main()
{
    vec3 global_Light = global_Color * frag_Global_Light;
    frag_Color = texture(texture_Sampler, frag_Texture) * vec4(
        max(frag_Local_Light, global_Light.x),
        max(frag_Local_Light, global_Light.y),
        max(frag_Local_Light, global_Light.z),
        1.0
    );

    if(frag_Color.a < 0.5)
        discard;
}
