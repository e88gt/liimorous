
package my.e88gt.liimorous.mesh;

import my.e88gt.liimorous.graphics.*;
import my.e88gt.liimorous.screen.*;

public class Mesh implements Renderable
{
	public static final Mesh PLANE = new Mesh(new float[]
	{
		-0.5F, +0.5F, 0.0F, /**/ 0.0F, 0.0F, //
		-0.5F, -0.5F, 0.0F, /**/ 0.0F, 1.0F, //
		+0.5F, -0.5F, 0.0F, /**/ 1.0F, 1.0F, //
		+0.5F, +0.5F, 0.0F, /**/ 1.0F, 0.0F, //
	}, new int[]
	{
		0, 1, 2, //
		2, 3, 0, //
	});
	
	private final int elementCount;
	private final VertexArray vao;
	private final VertexBuffer vbo, ebo;
	
	public Mesh(float[] vertices, int[] indices)
	{
		elementCount = indices.length;
		
		ebo = new VertexBuffer(VertexBuffer.ELEMENT_ARRAY);
		vbo = new VertexBuffer(VertexBuffer.ARRAY);
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
