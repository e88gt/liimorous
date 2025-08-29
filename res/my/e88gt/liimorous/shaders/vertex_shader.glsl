#version 460 core

layout (location = 0) in vec3 i_position;
layout (location = 1) in vec2 i_uv;

out vec2 b_uv;

void main()
{
	b_uv = i_uv;
	gl_Position = vec4(i_position, 1);
}
