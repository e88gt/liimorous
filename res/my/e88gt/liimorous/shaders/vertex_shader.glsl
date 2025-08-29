#version 460 core

layout (location = 0) in vec3 I_Position;
layout (location = 1) in vec2 I_UV;

out vec2 B_UV;

void main()
{
	B_UV = I_UV;
	gl_Position = vec4(I_Position, 1);
}
