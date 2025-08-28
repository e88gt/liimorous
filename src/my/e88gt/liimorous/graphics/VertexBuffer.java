
package my.e88gt.liimorous.graphics;

import static org.lwjgl.opengl.GL46.*;

import java.nio.*;

import org.lwjgl.system.*;

public class VertexBuffer
{
	public static final int ARRAY = GL_ARRAY_BUFFER;
	public static final int ELEMENT_ARRAY = GL_ELEMENT_ARRAY_BUFFER;
	
	private final int type, handle;
	
	public VertexBuffer(int type)
	{
		this.type = type;
		handle = glGenBuffers();
	}
	
	public void bind()
	{
		glBindBuffer(type, handle);
	}
	
	public void data(float[]data)
	{
		FloatBuffer buffer = MemoryUtil.memCallocFloat(data.length).put(data).flip();
		
		bind();
		glBufferData(type, buffer, GL_STATIC_DRAW);
		
		MemoryUtil.memFree(buffer);
	}
	
	public void data(int[]data)
	{
		IntBuffer buffer = MemoryUtil.memCallocInt(data.length).put(data).flip();
		
		bind();
		glBufferData(type, buffer, GL_STATIC_DRAW);
		
		MemoryUtil.memFree(buffer);
	}
	
	public void delete()
	{
		glDeleteBuffers(handle);
	}
	
	public int getType()
	{
		return type;
	}
	
	public int getHandle()
	{
		return handle;
	}
}
