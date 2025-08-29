package my.e88gt.liimorous.graphics;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.*;

public final class VertexArray
{
	private final int handle;
	
	public VertexArray()
	{
		handle = glGenVertexArrays();
		
		if (handle == NULL)
			throw new IllegalStateException("Failed to create vertex array");
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
