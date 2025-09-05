package my.e88gt.liimorous.graphics;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL44.*;
import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.*;
import java.util.*;

import my.e88gt.liimorous.mesh.*;

public final class VertexBuffer implements GraphicComponent
{
	private final int vbo;
	
	public VertexBuffer()
	{
		vbo = glCreateBuffers();
		
		if (vbo == NULL)
			throw new IllegalStateException("Failed to create vertex buffer");
	}
	
	public void store(List<Vertex> vertices)
	{
		FloatBuffer buffer = memCallocFloat(vertices.size() * Vertex.LENGTH);
		
		for (int i = 0; i < vertices.size(); i++)
		{
			buffer.put(vertices.get(i).getPosition().x);
			buffer.put(vertices.get(i).getPosition().y);
			buffer.put(vertices.get(i).getPosition().z);
			
			buffer.put(vertices.get(i).getUV().x);
			buffer.put(vertices.get(i).getUV().y);
		}
		
		buffer.flip();
		glNamedBufferStorage(vbo, buffer, GL_DYNAMIC_STORAGE_BIT);
		memFree(buffer);
	}
	
	@Override public void bind()
	{
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
	}
	
	@Override public void delete()
	{
		glDeleteBuffers(vbo);
	}
	
	@Override public int getHandle()
	{
		return vbo;
	}
}
