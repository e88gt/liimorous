#version 460 core

in vec2 B_UV;

out vec4 O_Result;

layout (location = 2) uniform sampler2D U_Texture;

void main()
{
	const vec4 d_result = texture(U_Texture, B_UV);
	
	O_Result = d_result;
}
