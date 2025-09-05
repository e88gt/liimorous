package my.e88gt.liimorous.shader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.*;
import java.util.*;

import org.joml.*;
import org.lwjgl.system.*;

public final class ShaderProgram implements ShaderComponent
{
	private static int current = -1;
	private final int program;
	private List<ShaderModule> shaders = new ArrayList<>();
	
	public ShaderProgram()
	{
		program = glCreateProgram();
	}
	
	public void attachShader(ShaderModule shader)
	{
		shaders.add(shader);
		glAttachShader(program, shader.getShader());
	}
	
	public void link()
	{
		glLinkProgram(program);
		
		if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE)
			throw new IllegalStateException(glGetShaderInfoLog(program));
	}
	
	public void use()
	{
		if (isCurrent())
			return;
		
		glUseProgram(program);
		
		setCurrent();
	}
	
	private void setCurrent()
	{
		current = program;
	}
	
	private boolean isCurrent()
	{
		return (current == program);
	}
	
	public void setUniformInt(int location, int value)
	{
		use();
		glUniform1i(location, value);
	}
	
	public void setUniformVec3(int location, float r, float g, float b)
	{
		use();
		glUniform3f(location, r, g, b);
	}
	
	public void setUniformMat4(int location, Matrix4f matrix)
	{
		use();
		FloatBuffer buffer = MemoryUtil.memCallocFloat(16);
		matrix.get(buffer);
		glUniformMatrix4fv(location, false, buffer);
		MemoryUtil.memFree(buffer);
	}
	
	@Override public void delete()
	{
		glDeleteProgram(program);
	}
	
	public int getProgram()
	{
		return program;
	}
}
