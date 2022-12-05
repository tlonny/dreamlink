#version 330

layout (location =0) in vec3 position;
layout (location =1) in vec3 normal;
layout (location =2) in float local_Light;
layout (location =3) in float global_Light;
layout (location =4) in vec2 texture;

uniform mat4 projection_Matrix;
uniform mat4 view_Rotation_Matrix;
uniform mat4 view_Translation_Matrix;
uniform mat4 model_Matrix;
uniform float global_Light_Direction_Factor;

out float frag_Local_Light;
out float frag_Global_Light;
out vec2 frag_Texture;


void main()
{
    const vec3 global_Light_Direction = normalize(vec3(1.0, 1.0, 1.0));

    gl_Position = projection_Matrix * view_Rotation_Matrix * view_Translation_Matrix * model_Matrix * vec4(position, 1.0);
    frag_Local_Light = local_Light;

    float normal_Component = (1 - global_Light_Direction_Factor) + global_Light_Direction_Factor * dot(global_Light_Direction, normal);
    frag_Global_Light = global_Light * normal_Component;
    frag_Texture = texture;
}
