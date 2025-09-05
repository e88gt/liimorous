package my.e88gt.liimorous.material;

import my.e88gt.liimorous.shader.*;

public final class Material
{
	private final ShaderProgram shaderProgram;
	
	public Material(ShaderProgram program)
	{
		this.shaderProgram = program;
	}
	
	public void destroy()
	{
		shaderProgram.delete();
	}
	
	public ShaderProgram shaderProgram()
	{
		return shaderProgram;
	}
}
