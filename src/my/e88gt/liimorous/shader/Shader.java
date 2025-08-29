package my.e88gt.liimorous.shader;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.*;

public final class Shader
{
	public static enum Type
	{
		VERTEX(GL_VERTEX_SHADER),
		FRAGMENT(GL_FRAGMENT_SHADER);
		
		private final int handle;
		
		private Type(int handle)
		{
			this.handle = handle;
		}
	}
	
	private final int shader;
	
	public Shader(Type type)
	{
		shader = glCreateShader(type.handle);
		
		if (shader == NULL)
			throw new IllegalStateException("Failed to create " + type + " shader");
	}
	
	public void shaderSource(String source)
	{
		glShaderSource(shader, source);
	}
	
	public void compile()
	{
		glCompileShader(shader);
		
		if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE)
			throw new IllegalStateException(glGetShaderInfoLog(shader));
	}
	
	public void delete()
	{
		glDeleteShader(shader);
	}
	
	public int getHandle()
	{
		return shader;
	}
}
