package my.e88gt.liimorous.graphics;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * vertex array
 */
public final class _VertexArray
{
	/**
	 * its id that holds everything in place
	 */
	private final int vao;
	
	/**
	 * creates a vertex array
	 */
	public _VertexArray()
	{
		vao = glGenVertexArrays();
		
		if (vao == NULL)
			throw new IllegalStateException("Failed to create vertex array");
	}
	
	/**
	 * bind the vertex array
	 */
	public void bind()
	{
		glBindVertexArray(vao);
	}
	
	/**
	 * adds an attribute to the vertex array
	 * 
	 * @param vbo the vertex buffer<br><br>
	 * 
	 * @param location the location in gl<br><br>
	 * 
	 * @param size <br>
	 * if the size is 0 then i dont know<br>
	 * size is 1 then its a float<br>
	 * size is 2 then its a vector of 2 of the type<br>
	 * size is 3 then its a vector of 3 of the type<br>
	 * size is 4 then its a vector of 4 of the type<br><br>
	 * 
	 * @param stride the total size of the vertices<br><br>
	 * 
	 * @param offset the offset from 0 to the current attribute<br><br>
	 */
	public void attribute(_VertexBuffer vbo, int location, int size, int stride, long offset)
	{
		bind();
		vbo.bind();
		glVertexAttribPointer(location, size, GL_FLOAT, false, stride, offset);
		glEnableVertexAttribArray(location);
	}
	
	/**
	 * deletes the vertex array
	 */
	public void delete()
	{
		glDeleteVertexArrays(vao);
	}
}
