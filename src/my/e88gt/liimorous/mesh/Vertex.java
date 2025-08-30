package my.e88gt.liimorous.mesh;

import org.joml.*;

public class Vertex
{
	public static final int POSITION_SIZE = 3;
	public static final int UV_SIZE = 2;
	public static final int LENGTH = POSITION_SIZE + UV_SIZE;
	public static final int STRIDE = LENGTH * Float.BYTES;
	
	private final Vector3f position;
	private final Vector2f uv;
	
	public Vertex(Vector3f position, Vector2f uv)
	{
		this.position = position;
		this.uv = uv;
	}
	
	public Vector3f getPosition()
	{
		return position;
	}
	
	public Vector2f getUV()
	{
		return uv;
	}
}
