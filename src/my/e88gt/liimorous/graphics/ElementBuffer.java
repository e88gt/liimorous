package my.e88gt.liimorous.graphics;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.*;
import java.util.*;

public class ElementBuffer
{
	private final int ebo;
	
	public ElementBuffer()
	{
		ebo = glCreateBuffers();
		
		if (ebo == NULL)
			throw new IllegalStateException("Failed to create element buffer");
	}
	
	public void storage(List<Integer> indices)
	{
		IntBuffer buffer = memCallocInt(indices.size());
		
		for (int i = 0; i < indices.size(); i++)
			buffer.put(indices.get(i));
		
		buffer.flip();
		glNamedBufferStorage(ebo, buffer, GL_DYNAMIC_STORAGE_BIT);
		memFree(buffer);
	}
	
	public void delete()
	{
		glDeleteBuffers(ebo);
	}
	
	public int getID()
	{
		return ebo;
	}
}
