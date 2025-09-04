package my.e88gt.liimorous.mesh;

import java.util.*;

import my.e88gt.liimorous.graphics.*;
import my.e88gt.liimorous.screen.*;

public final class Mesh implements Renderable, Cloneable
{
	private final int elementCount;
	private final VertexArray vao;
	private final VertexBuffer vbo;
	private final ElementBuffer ebo;
	private final List<Vertex> vertices;
	private final List<Integer> indices;
	
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
		
		this.indices = indices;
		this.vertices = vertices;
	}
	
	public void destroy()
	{
		ebo.delete();
		vbo.delete();
		vao.delete();
	}
	
	@Override public Mesh clone()
	{
		return new Mesh(vertices, indices);
	}
	
	@Override public VertexArray getVertexArray()
	{
		return vao;
	}
	
	@Override public int getElementCount()
	{
		return elementCount;
	}
	
	public static final class Preset
	{
		public static final Mesh CUBE = new Mesh(Datas.cubeVertices, Datas.cubeIndices);
		public static final Mesh PLANE = new Mesh(Datas.cubeVertices, Datas.cubeIndices);
	}
}
