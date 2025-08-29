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
	
	private final int handle;
	
	public Shader(Type type)
	{
		handle = glCreateShader(type.handle);
		
		if (handle == NULL)
			throw new IllegalStateException("Failed to create " + type + " shader");
	}
	
	public void shaderSource(String source)
	{
		glShaderSource(handle, source);
	}
	
	public void compile()
	{
		glCompileShader(handle);
		
		if (glGetShaderi(handle, GL_COMPILE_STATUS) == GL_FALSE)
			throw new IllegalStateException(glGetShaderInfoLog(handle));
	}
	
	public void delete()
	{
		glDeleteShader(handle);
	}
	
	public int getHandle()
	{
		return handle;
	}
}
