package my.e88gt.liimorous.mesh;

import java.util.*;

import org.joml.*;

import my.e88gt.liimorous.graphics.*;
import my.e88gt.liimorous.screen.*;

final class _Mesh implements Renderable
{
	private final int elementCount;
	private final _VertexArray vao;
	private final _VertexBuffer vbo, ebo;
	
	_Mesh(List<Vertex> vertices, List<Integer> indices)
	{
		elementCount = indices.size();
		
		ebo = new _VertexBuffer(_VertexBuffer.Type.ELEMENT_ARRAY);
		vbo = new _VertexBuffer(_VertexBuffer.Type.ARRAY);
		vao = new _VertexArray();
		
		vao.attribute(vbo, 0, 3, 20, 0);
		vao.attribute(vbo, 1, 2, 20, 12);
		
		vbo.vData(vertices);
		ebo.iData(indices);
	}
	
	void destroy()
	{
		ebo.delete();
		vbo.delete();
		vao.delete();
	}
	
	public VertexArray getVertexArray()
	{
		return null;
	}
	
	public int getElementCount()
	{
		return elementCount;
	}
	
	static class BuiltIn
	{
		public static final _Mesh PLANE = new _Mesh(Datas.planeVertices, Datas.planeIndices);
		public static final _Mesh CUBE = new _Mesh(Datas.cubeVertices, Datas.cubeIndices);
	}
	
	static class Datas
	{
		static final ArrayList<Vertex> planeVertices = new ArrayList<>();
		static final ArrayList<Integer> planeIndices = new ArrayList<>();
		
		static
		{
			planeVertices.add(new Vertex(new Vector3f(-0.5F, +0.5F, 0), new Vector2f(0, 0)));
			planeVertices.add(new Vertex(new Vector3f(-0.5F, -0.5F, 0), new Vector2f(0, 1)));
			planeVertices.add(new Vertex(new Vector3f(+0.5F, -0.5F, 0), new Vector2f(1, 1)));
			planeVertices.add(new Vertex(new Vector3f(+0.5F, +0.5F, 0), new Vector2f(1, 0)));
			
			planeIndices.add(0);
			planeIndices.add(1);
			planeIndices.add(2);
			planeIndices.add(2);
			planeIndices.add(3);
			planeIndices.add(0);
		}
		
		static final ArrayList<Vertex> cubeVertices = new ArrayList<>();
		static final ArrayList<Integer> cubeIndices = new ArrayList<>();
		
		static
		{
			cubeVertices.add(0, new Vertex(new Vector3f(-0.5F, -0.5F, +0.5F), new Vector2f(0, 0)));
			cubeVertices.add(1, new Vertex(new Vector3f(+0.5F, -0.5F, +0.5F), new Vector2f(1, 0)));
			cubeVertices.add(2, new Vertex(new Vector3f(+0.5F, +0.5F, +0.5F), new Vector2f(1, 1)));
			cubeVertices.add(3, new Vertex(new Vector3f(-0.5F, +0.5F, +0.5F), new Vector2f(0, 1)));
			
			cubeVertices.add(4, new Vertex(new Vector3f(-0.5F, -0.5F, -0.5F), new Vector2f(1, 0)));
			cubeVertices.add(5, new Vertex(new Vector3f(-0.5F, +0.5F, -0.5F), new Vector2f(1, 1)));
			cubeVertices.add(6, new Vertex(new Vector3f(+0.5F, +0.5F, -0.5F), new Vector2f(0, 1)));
			cubeVertices.add(7, new Vertex(new Vector3f(+0.5F, -0.5F, -0.5F), new Vector2f(0, 0)));
			
			cubeVertices.add(8, new Vertex(new Vector3f(-0.5F, -0.5F, -0.5F), new Vector2f(0, 0)));
			cubeVertices.add(9, new Vertex(new Vector3f(-0.5F, -0.5F, +0.5F), new Vector2f(1, 0)));
			cubeVertices.add(10, new Vertex(new Vector3f(-0.5F, +0.5F, +0.5F), new Vector2f(1, 1)));
			cubeVertices.add(11, new Vertex(new Vector3f(-0.5F, +0.5F, -0.5F), new Vector2f(0, 1)));
			
			cubeVertices.add(12, new Vertex(new Vector3f(+0.5F, -0.5F, +0.5F), new Vector2f(0, 0)));
			cubeVertices.add(13, new Vertex(new Vector3f(+0.5F, -0.5F, -0.5F), new Vector2f(1, 0)));
			cubeVertices.add(14, new Vertex(new Vector3f(+0.5F, +0.5F, -0.5F), new Vector2f(1, 1)));
			cubeVertices.add(15, new Vertex(new Vector3f(+0.5F, +0.5F, +0.5F), new Vector2f(0, 1)));
			
			cubeVertices.add(16, new Vertex(new Vector3f(-0.5F, +0.5F, +0.5F), new Vector2f(0, 0)));
			cubeVertices.add(17, new Vertex(new Vector3f(+0.5F, +0.5F, +0.5F), new Vector2f(1, 0)));
			cubeVertices.add(18, new Vertex(new Vector3f(+0.5F, +0.5F, -0.5F), new Vector2f(1, 1)));
			cubeVertices.add(19, new Vertex(new Vector3f(-0.5F, +0.5F, -0.5F), new Vector2f(0, 1)));
			
			cubeVertices.add(20, new Vertex(new Vector3f(-0.5F, -0.5F, -0.5F), new Vector2f(0, 0)));
			cubeVertices.add(21, new Vertex(new Vector3f(+0.5F, -0.5F, -0.5F), new Vector2f(1, 0)));
			cubeVertices.add(22, new Vertex(new Vector3f(+0.5F, -0.5F, +0.5F), new Vector2f(1, 1)));
			cubeVertices.add(23, new Vertex(new Vector3f(-0.5F, -0.5F, +0.5F), new Vector2f(0, 1)));
			
			cubeIndices.add(0);
			cubeIndices.add(1);
			cubeIndices.add(2);
			cubeIndices.add(2);
			cubeIndices.add(3);
			cubeIndices.add(0);
			
			cubeIndices.add(4);
			cubeIndices.add(5);
			cubeIndices.add(6);
			cubeIndices.add(6);
			cubeIndices.add(7);
			cubeIndices.add(4);
			
			cubeIndices.add(8);
			cubeIndices.add(9);
			cubeIndices.add(10);
			cubeIndices.add(10);
			cubeIndices.add(11);
			cubeIndices.add(8);
			
			cubeIndices.add(12);
			cubeIndices.add(13);
			cubeIndices.add(14);
			cubeIndices.add(14);
			cubeIndices.add(15);
			cubeIndices.add(12);
			
			cubeIndices.add(16);
			cubeIndices.add(17);
			cubeIndices.add(18);
			cubeIndices.add(18);
			cubeIndices.add(19);
			cubeIndices.add(16);
			
			cubeIndices.add(20);
			cubeIndices.add(21);
			cubeIndices.add(22);
			cubeIndices.add(22);
			cubeIndices.add(23);
			cubeIndices.add(20);
		}
	}
}
