package my.e88gt.liimorous.mesh;

import java.util.*;

import my.e88gt.liimorous.graphics.*;

public final class Mesh
{
	private final int elementCount;
	private final VertexArray vao;
	private final VertexBuffer vbo;
	private final ElementBuffer ebo;
	
	public Mesh(List<Vertex> vertices, List<Integer> indices)
	{
		vao = new VertexArray();
		vbo = new VertexBuffer();
		ebo = new ElementBuffer();
		
		vbo.store(vertices);
		ebo.store(indices);
		
		vao.vertex(vbo, 20);
		vao.element(ebo);
		vao.attribute(0, 3, 0);
		vao.attribute(1, 2, 12);
		
		elementCount = indices.size();
	}
	
	public void destroy()
	{
		ebo.delete();
		vbo.delete();
		vao.delete();
	}
	
	public VertexArray vao()
	{
		return vao;
	}
	
	public int elementCount()
	{
		return elementCount;
	}
}
