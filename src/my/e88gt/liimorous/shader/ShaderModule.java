package my.e88gt.liimorous.shader;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.*;

public final class ShaderModule implements ShaderComponent
{
	public static final String FOLDER_PATH = "res/my/e88gt/liimorous/shaders/";
	
	private final int shader;
	
	public ShaderModule(ShaderType type)
	{
		shader = glCreateShader(type.getType());
		
		if (shader == NULL)
			throw new IllegalStateException("Failed to create " + type + " shader");
	}
	
	public void setShaderSource(String source)
	{
		glShaderSource(shader, source);
	}
	
	public void compile()
	{
		glCompileShader(shader);
		
		if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE)
			throw new IllegalStateException(glGetShaderInfoLog(shader));
	}
	
	@Override public void delete()
	{
		glDeleteShader(shader);
	}
	
	@Override public int getHandle()
	{
		return shader;
	}
}
