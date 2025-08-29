
package my.e88gt.liimorous.graphics;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.*;

import org.lwjgl.system.*;

/**
 * vertex buffer
 */
public final class VertexBuffer
{
	/**
	 * the types of vertex buffer
	 */
	public static enum Type {
		/**
		 * array buffer
		 */
		ARRAY(GL_ARRAY_BUFFER),
		
		/**
		 * element array buffer
		 */
		ELEMENT_ARRAY(GL_ELEMENT_ARRAY_BUFFER);
		
		/**
		 * the type it holds
		 */
		private final int type;
		
		/**
		 * the enum constructor
		 * @param type
		 */
		private Type(int type)
		{
			this.type = type;
		}
		
		/**
		 * the getter
		 * @return what it holds
		 */
		public int getType()
		{
			return type;
		}
	}
	
	/**
	 * the id that holds the informations
	 */
	private final int vbo;
	
	/**
	 * the type
	 */
	private final Type type;
	
	/**
	 * creates a vertex buffer
	 * 
	 * @param type {@link Type}
	 */
	public VertexBuffer(Type type)
	{
		this.type = type;
		
		vbo = glGenBuffers();
		
		if (vbo == NULL)
			throw new IllegalStateException("Failed to create buffer");
	}
	
	/**
	 * binds the buffer with its type
	 */
	public void bind()
	{
		glBindBuffer(type.getType(), vbo);
	}
	
	/**
	 * sets the data
	 * @param data
	 */
	public void data(float[] data)
	{
		FloatBuffer buffer = MemoryUtil.memCallocFloat(data.length).put(data).flip();
		
		bind();
		glBufferData(type.getType(), buffer, GL_STATIC_DRAW);
		
		MemoryUtil.memFree(buffer);
	}
	
	/**
	 * sets the data
	 * @param data
	 */
	public void data(int[] data)
	{
		IntBuffer buffer = MemoryUtil.memCallocInt(data.length).put(data).flip();
		
		bind();
		glBufferData(type.getType(), buffer, GL_STATIC_DRAW);
		
		MemoryUtil.memFree(buffer);
	}
	
	/**
	 * deletes the buffer
	 */
	public void delete()
	{
		glDeleteBuffers(vbo);
	}
	
	/**
	 * the id it holds
	 * 
	 * @return the id
	 */
	public int getID()
	{
		return vbo;
	}
}
