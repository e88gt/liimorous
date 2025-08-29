package my.e88gt.liimorous.shader;

import static org.lwjgl.opengl.GL46.*;

import java.util.*;

public final class ShaderProgram
{
	private final int handle;
	private List<Shader> shaders = new ArrayList<>();
	
	public ShaderProgram()
	{
		handle = glCreateProgram();
	}
	
	public void attachShader(Shader shader)
	{
		shaders.add(shader);
		glAttachShader(handle, shader.getHandle());
	}
	
	public void link()
	{
		glLinkProgram(handle);
		
		if (glGetProgrami(handle, GL_LINK_STATUS) == GL_FALSE)
			throw new IllegalStateException(glGetShaderInfoLog(handle));
	}
	
	public void use()
	{
		glUseProgram(handle);
	}
	
	public void delete()
	{
		glDeleteProgram(handle);
	}
}
