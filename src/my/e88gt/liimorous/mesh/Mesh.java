package my.e88gt.liimorous.mesh;

import java.util.*;

import my.e88gt.liimorous.graphics.*;
import my.e88gt.liimorous.screen.*;

public class Mesh implements Renderable
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
		
		vbo.storage(vertices);
		ebo.storage(indices);
		
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
	
	@Override public VertexArray getVertexArray()
	{
		return vao;
	}
	
	@Override public int getElementCount()
	{
		return elementCount;
	}
	
	public static class BuiltIn
	{
		public static final Mesh PLANE = new Mesh(Datas.planeVertices, Datas.planeIndices);
		public static final Mesh CUBE = new Mesh(Datas.cubeVertices, Datas.cubeIndices);
	}
}
