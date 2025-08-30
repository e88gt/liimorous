package my.e88gt.liimorous.screen;

import org.joml.*;
import org.joml.Math;

public class Camera3D implements Camera
{
	public static final float DEFAULT_FOV = 120.0F;
	public static final float DEFAULT_ZFAR = 1000.0F;
	public static final float DEFAULT_ZNEAR = 0.01F;
	
	private float fov, zfar, znear;
	private final Vector3f position, rotation;
	
	public Camera3D()
	{
		this(DEFAULT_FOV, DEFAULT_ZNEAR, DEFAULT_ZFAR);
	}
	
	public Camera3D(float fov, float znear, float zfar)
	{
		this.fov = fov;
		this.zfar = zfar;
		this.znear = znear;
		
		position = new Vector3f();
		rotation = new Vector3f();
	}
	
	public Vector3f getPosition()
	{
		return position;
	}
	
	public Vector3f getRotation()
	{
		return rotation;
	}
	
	@Override public Matrix4f getProjection(int width, int height)
	{
		Matrix4f projection = new Matrix4f();
		projection.perspective(Math.toRadians(fov), (float) (width / height), znear, zfar);
		return projection;
	}
	
	@Override public Matrix4f getViewMatrix()
	{
		Matrix4f view = new Matrix4f();
		
		view.rotateX(Math.toRadians(-rotation.x));
		view.rotateY(Math.toRadians(-rotation.y));
		view.rotateZ(Math.toRadians(-rotation.z));
		view.translate(-position.x, -position.y, -position.z);
		
		return view;
	}
}
