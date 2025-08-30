package my.e88gt.liimorous.shader;

import static org.lwjgl.opengl.GL13.*;

import java.io.*;

import org.joml.*;

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
	
	public void useTexture(int texture)
	{
		program.uniformInt(2, texture);
		glActiveTexture(GL_TEXTURE0 + texture);
	}
	
	public void projectionMatrix(Matrix4f projection)
	{
		program.uniformMat4(5, projection);
	}
	
	public void viewMatrix(Matrix4f view)
	{
		program.uniformMat4(4, view);
	}
	
	public void worldMatrix(Matrix4f world)
	{
		program.uniformMat4(3, world);
	}
	
	public void delete()
	{
		vertex.delete();
		fragment.delete();
		program.delete();
	}
	
	public int getProgram()
	{
		return program.getID();
	}
}
