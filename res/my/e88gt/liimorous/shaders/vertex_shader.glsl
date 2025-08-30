#version 460 core

layout (location = 0) in vec3 I_Position;
layout (location = 1) in vec2 I_UV;

out vec2 B_UV;

layout (location = 3) uniform mat4 U_MatWorld;
layout (location = 4) uniform mat4 U_MatView;
layout (location = 5) uniform mat4 U_MatProjection;

void main()
{
	B_UV = I_UV;
	gl_Position = U_MatProjection * U_MatView * U_MatWorld * vec4(I_Position, 1);
}
