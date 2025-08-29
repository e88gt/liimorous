package my.e88gt.liimorous.shader;

import static org.lwjgl.opengl.GL13.*;

import java.io.*;

import my.e88gt.liimorous.utils.*;

public final class CoreShader
{
	public static final String FOLDER_PATH = "res/my/e88gt/liimorous/shaders/";
	
	private final Shader vertex, fragment;
	private final ShaderProgram program;
	
	public CoreShader(String vertexPath, String fragmentPath)
	{
		program = new ShaderProgram();
		vertex = new Shader(Shader.Type.VERTEX);
		fragment = new Shader(Shader.Type.FRAGMENT);
		
		try
		{
			String vertexSource = new TextFile(vertexPath).getContent();
			vertex.shaderSource(vertexSource);
			
			String fragmentSource = new TextFile(fragmentPath).getContent();
			fragment.shaderSource(fragmentSource);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		
		vertex.compile();
		fragment.compile();
		
		program.attachShader(vertex);
		program.attachShader(fragment);
		program.link();
	}
	
	public void use()
	{
		program.use();
	}
	
	public void useColor(float red, float green, float blue)
	{
		use();
		program.uniformBool(2, false);
		program.uniformVec3(3, red, green, blue);
	}
	
	public void useTexture(int texture)
	{
		use();
		program.uniformBool(2, true);
		program.uniformInt(4, texture);
		glActiveTexture(GL_TEXTURE0 + texture);
	}
	
	public void delete()
	{
		vertex.delete();
		fragment.delete();
		program.delete();
	}
}
