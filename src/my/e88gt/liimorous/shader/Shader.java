package my.e88gt.liimorous.shader;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.*;

public final class Shader
{
	public static final String FOLDER_PATH = "res/my/e88gt/liimorous/shaders/";
	
	private final int shader;
	
	public Shader(ShaderType type)
	{
		shader = glCreateShader(type.getType());
		
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
