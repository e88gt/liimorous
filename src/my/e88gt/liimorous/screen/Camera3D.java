package my.e88gt.liimorous.screen;

import org.joml.*;
import org.joml.Math;

import my.e88gt.liimorous.engine.*;
import my.e88gt.liimorous.input.*;

public final class Camera3D implements Camera
{
	public static final float DEFAULT_FOV = 120.0F;
	public static final float DEFAULT_ZFAR = 1000.0F;
	public static final float DEFAULT_ZNEAR = 0.01F;
	
	private boolean movingView, affectUp;
	private float fov, zFar, zNear, speed, sensitivity;
	private final Vector3f position, rotation;
	
	public Camera3D()
	{
		this(DEFAULT_FOV, DEFAULT_ZNEAR, DEFAULT_ZFAR);
	}
	
	public Camera3D(float fov, float znear, float zfar)
	{
		this.fov = fov;
		this.zFar = zfar;
		this.zNear = znear;
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
		
		if (input instanceof MouseScroll scroll)
			scroll(scroll);
	}
	
	private void click(MouseClick click)
	{
		if (click.isDown(MouseButton.LEFT))
		{
			movingView = true;
		}
	}
	
	private void cursor(MouseCursor cursor)
	{
		cursor.setVisible(!movingView);
		
		if(movingView)
		{
			cursor.setPosition(Launcher.LAUNCHER.getEngine().getWindow().getWidth() / 2, Launcher.LAUNCHER.getEngine().getWindow().getHeight() / 2);
			rotation.z += -(cursor.getDeltaX() * sensitivity);
			rotation.x += -(cursor.getDeltaY() * sensitivity);
		}
		
//		if (movingView)
//		{
//			cursor.setPosition(Launcher.LAUNCHER.getEngine().getWindow().getWidth() / 2, Launcher.LAUNCHER.getEngine().getWindow().getHeight() / 2);
//			rotation.x += (cursor.getCenteredY() * sensitivity);
//			rotation.y -= (cursor.getCenteredX() * sensitivity);
//		}
	}
	
	private void keyboard(Keyboard key)
	{
		if (key.isDown(Key.ESCAPE) && movingView)
		{
			movingView = false;
		}
		
		if (key.isDown(Key.W))
		{
			moveLocal(0, 0, -speed);
		}
		if (key.isDown(Key.S))
		{
			moveLocal(0, 0, speed);
		}
		if (key.isDown(Key.D))
		{
			moveLocal(speed, 0, 0);
		}
		if (key.isDown(Key.A))
		{
			moveLocal(-speed, 0, 0);
		}
		if (key.isDown(Key.SPACE))
		{
			position.y += speed;
		}
		if (key.isDown(Key.TAB))
		{
			position.y -= speed;
		}
	}
	
	private void scroll(MouseScroll scroll)
	{
		if (scroll.isScrolling())
			fov += scroll.getScrollY();
	}
	
	public void update(double delta)
	{
	}
	
	public void moveLocal(float x, float y, float z)
	{
		final Vector3f localMovement = new Vector3f(x, y, z);
		final Matrix4f localRotation = new Matrix4f();
		
		localRotation.rotateY(Math.toRadians(rotation.y));
		
		if (affectUp)
			localRotation.rotateX(Math.toRadians(rotation.x));
		
		localRotation.transformDirection(localMovement);
		
		position.add(localMovement);
	}
	
	@Override public Vector3f getPosition()
	{
		return position;
	}
	
	@Override public Vector3f getRotation()
	{
		return rotation;
	}
	
	@Override public Matrix4f getProjection(int width, int height)
	{
		Matrix4f projection = new Matrix4f();
		projection.perspective(Math.toRadians(fov), (float) (width / height), zNear, zFar);
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
