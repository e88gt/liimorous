package my.e88gt.liimorous.shader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.*;
import java.util.*;

import org.joml.*;
import org.lwjgl.system.*;

public final class ShaderProgram
{
	private final int program;
	private List<Shader> shaders = new ArrayList<>();
	
	public ShaderProgram()
	{
		program = glCreateProgram();
	}
	
	public void attachShader(Shader shader)
	{
		shaders.add(shader);
		glAttachShader(program, shader.getHandle());
	}
	
	public void link()
	{
		glLinkProgram(program);
		
		if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE)
			throw new IllegalStateException(glGetShaderInfoLog(program));
	}
	
	public void use()
	{
		glUseProgram(program);
	}
	
	public void uniformBool(int location, boolean value)
	{
		use();
		glUniform1i(location, value ? GL_TRUE : GL_FALSE);
	}
	
	public void uniformInt(int location, int value)
	{
		use();
		glUniform1i(location, value);
	}
	
	public void uniformVec3(int location, float r, float g, float b)
	{
		use();
		glUniform3f(location, r, g, b);
	}
	
	public void uniformMat4(int location, Matrix4f matrix)
	{
		use();
		FloatBuffer buffer = MemoryUtil.memCallocFloat(16);
		glUniformMatrix4fv(location, false, buffer);
		MemoryUtil.memFree(buffer);
	}
	
	public void delete()
	{
		glDeleteProgram(program);
	}
}
