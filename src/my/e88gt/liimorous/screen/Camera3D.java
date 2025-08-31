package my.e88gt.liimorous.screen;

import org.joml.*;
import org.joml.Math;

import my.e88gt.liimorous.engine.*;
import my.e88gt.liimorous.input.*;

public class Camera3D implements Camera
{
	public static final float DEFAULT_FOV = 120.0F;
	public static final float DEFAULT_ZFAR = 1000.0F;
	public static final float DEFAULT_ZNEAR = 0.01F;
	
	private boolean move;
	private float fov, zfar, znear, speed, sensitivity;
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
		speed = 0.0005F;
		sensitivity = 0.01F;
		position = new Vector3f();
		rotation = new Vector3f();
	}
	
	public void input(Input input)
	{
		if (input instanceof Keyboard key)
			keyboard(key);
		
		if (input instanceof MouseClick click)
			click(click);
		
		if (input instanceof MouseCursor cursor)
			cursor(cursor);
	}
	
	private void click(MouseClick click)
	{
		if(click.isDown(MouseButton.RIGHT))
			move = true;
	}
	
	private void cursor(MouseCursor cursor)
	{
		if (move)
		{
			rotation.x += (cursor.getCenteredY() * sensitivity);
			rotation.y -= (cursor.getCenteredX() * sensitivity);
			cursor.setPosition(Launcher.LAUNCHER.getEngine().getWindow().getWidth() / 2, Launcher.LAUNCHER.getEngine().getWindow().getHeight() / 2);
			move = false;
		}
	}
	
	private void keyboard(Keyboard key)
	{
		if (key.isDownI('W'))
		{
			moveLocal(0, 0, -speed);
		}
		if (key.isDownI('S'))
		{
			moveLocal(0, 0, speed);
		}
		if (key.isDownI('D'))
		{
			moveLocal(speed, 0, 0);
		}
		if (key.isDownI('A'))
		{
			moveLocal(-speed, 0, 0);
		}
		if (key.isDownI(' '))
		{
			moveLocal(0, speed, 0);
		}
		if (key.isDown(Key.TAB))
		{
			moveLocal(0, -speed, 0);
		}
	}
	
	public void moveLocal(float x, float y, float z)
	{
		final Vector3f localMovement = new Vector3f(x, y, z);
		final Matrix4f localRotation = new Matrix4f();
		localRotation.rotateY(Math.toRadians(rotation.y));
		localRotation.rotateX(Math.toRadians(rotation.x));
		localRotation.transformDirection(localMovement);
		position.add(localMovement);
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
