package my.e88gt.liimorous.mesh;

import java.util.*;

import org.joml.*;

import my.e88gt.liimorous.graphics.*;
import my.e88gt.liimorous.screen.*;

public final class Mesh implements Renderable
{
	private static class Datas
	{
		private static final ArrayList<Vertex> vertices = new ArrayList<>();
		private static final int[] indices =
		{
			0, 1, 2, //
			2, 3, 0, //
		};
		
		static
		{
			vertices.add(new Vertex(new Vector3f(-0.5F, +0.5F, 0), new Vector2f(0, 0)));
			vertices.add(new Vertex(new Vector3f(-0.5F, -0.5F, 0), new Vector2f(0, 1)));
			vertices.add(new Vertex(new Vector3f(+0.5F, -0.5F, 0), new Vector2f(1, 1)));
			vertices.add(new Vertex(new Vector3f(+0.5F, +0.5F, 0), new Vector2f(1, 0)));
		}
	}
	
	public static class BuiltIn
	{
		public static final Mesh MESH = new Mesh(Datas.vertices, Datas.indices);
	}
	
	private final int elementCount;
	private final VertexArray vao;
	private final VertexBuffer vbo, ebo;
	
	public Mesh(List<Vertex> vertices, int[] indices)
	{
		elementCount = indices.length;
		
		ebo = new VertexBuffer(VertexBuffer.Type.ELEMENT_ARRAY);
		vbo = new VertexBuffer(VertexBuffer.Type.ARRAY);
		vao = new VertexArray();
		
		vao.attribute(vbo, 0, 3, 20, 0);
		vao.attribute(vbo, 1, 2, 20, 12);
		
		ebo.data(indices);
		vbo.data(vertices);
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
}
