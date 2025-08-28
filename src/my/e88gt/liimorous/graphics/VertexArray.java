package my.e88gt.liimorous.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray
{
	private final int handle;
	
	public VertexArray()
	{
		handle = glGenVertexArrays();
	}
	
	public void bind()
	{
		glBindVertexArray(handle);
	}
	
	public void attribute(VertexBuffer vbo, int location, int size, int stride, long offset)
	{
		bind();
		vbo.bind();
		glVertexAttribPointer(location, size, GL_FLOAT, false, stride, offset);
		glEnableVertexAttribArray(location);
	}
	
	public void delete()
	{
		glDeleteVertexArrays(handle);
	}
	
	public int getHandle()
	{
		return handle;
	}
}
