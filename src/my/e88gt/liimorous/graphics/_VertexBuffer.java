
package my.e88gt.liimorous.graphics;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.*;
import java.util.*;

import org.lwjgl.system.*;

import my.e88gt.liimorous.mesh.*;

/**
 * vertex buffer
 */
public final class _VertexBuffer
{
	/**
	 * the types of vertex buffer
	 */
	public static enum Type
	{
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
		 * 
		 * @param type
		 */
		private Type(int type)
		{
			this.type = type;
		}
		
		/**
		 * the getter
		 * 
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
	public _VertexBuffer(Type type)
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
	 * sets the data for vertices
	 * 
	 * @param data
	 */
	public void vData(List<Vertex> data)
	{
		FloatBuffer buffer = MemoryUtil.memCallocFloat(data.size() * Vertex.LENGTH);
		
		for (int i = 0; i < data.size(); i++)
		{
			buffer.put(data.get(i).getPosition().x());
			buffer.put(data.get(i).getPosition().y());
			buffer.put(data.get(i).getPosition().z());
			
			buffer.put(data.get(i).getUV().x());
			buffer.put(data.get(i).getUV().y());
		}
		
		buffer.flip();
		
		bind();
		glBufferData(type.getType(), buffer, GL_STATIC_DRAW);
		
		MemoryUtil.memFree(buffer);
	}
	
	/**
	 * sets the data int array
	 * 
	 * @param data
	 */
	public void iData(List<Integer> data)
	{
		IntBuffer buffer = MemoryUtil.memCallocInt(data.size());
		
		for(int i = 0; i < data.size(); i++)
			buffer.put(data.get(i));
		
		buffer.flip();
		
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
