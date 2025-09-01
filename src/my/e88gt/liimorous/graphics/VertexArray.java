package my.e88gt.liimorous.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.system.MemoryUtil.*;

public final class VertexArray
{
	private final int vao;
	
	public VertexArray()
	{
		vao = glCreateVertexArrays();
		
		if (vao == NULL)
			throw new IllegalStateException("Failed to create vertex array");
	}
	
	public void vertex(VertexBuffer vbo, int stride)
	{
		glVertexArrayVertexBuffer(vao, 0, vbo.getID(), 0, stride);
	}
	
	public void element(ElementBuffer ebo)
	{
		glVertexArrayElementBuffer(vao, ebo.getID());
	}
	
	public void attribute(int location, int size, int offset)
	{
		glEnableVertexArrayAttrib(vao, location);
		glVertexArrayAttribFormat(vao, location, size, GL_FLOAT, false, offset);
		glVertexArrayAttribBinding(vao, location, 0);
	}
	
	public void bind()
	{
		glBindVertexArray(vao);
	}
	
	public void delete()
	{
		glDeleteVertexArrays(vao);
	}
}
