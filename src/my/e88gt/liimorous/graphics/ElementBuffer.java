package my.e88gt.liimorous.graphics;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL44.*;
import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.*;
import java.util.*;

public final class ElementBuffer implements GraphicComponent
{
	private final int ebo;
	
	public ElementBuffer()
	{
		ebo = glCreateBuffers();
		
		if (ebo == NULL)
			throw new IllegalStateException("Failed to create element buffer");
	}
	
	public void store(List<Integer> indices)
	{
		IntBuffer buffer = memCallocInt(indices.size());
		
		for (int i = 0; i < indices.size(); i++)
			buffer.put(indices.get(i));
		
		buffer.flip();
		glNamedBufferStorage(ebo, buffer, GL_DYNAMIC_STORAGE_BIT);
		memFree(buffer);
	}
	
	@Override public void bind()
	{
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
	}
	
	@Override public void delete()
	{
		glDeleteBuffers(ebo);
	}
	
	@Override public int getHandle()
	{
		return ebo;
	}
}
