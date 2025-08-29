#version 460 core

in vec2 B_UV;

out vec4 O_Result;

layout (location = 2) uniform bool U_UseTexture;
layout (location = 3) uniform vec3 U_Color;
layout (location = 4) uniform sampler2D U_Texture;

void main()
{
	const vec4 d_color = vec4(U_Color, 1);
	const vec4 d_texture = texture(U_Texture, B_UV);
	const vec4 d_result = (U_UseTexture)? d_texture : d_color;
	
	O_Result = d_result;
}
