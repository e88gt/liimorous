package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.*;

import my.e88gt.liimorous.screen.*;

public class MouseCursor implements Input
{
	private boolean moving, inWindow;
	private final Vector2d position, centeredPosition;
	private final Window window;
	
	public MouseCursor(Window window)
	{
		this.window = window;
		position = new Vector2d(0);
		centeredPosition = new Vector2d(0);
		
		glfwSetCursorEnterCallback(window.getAddress(), this::enterCallback);
		glfwSetCursorPosCallback(window.getAddress(), this::posCallback);
	}
	
	public boolean isMoving()
	{
		if (moving)
		{
			moving = false;
			return true;
		}
		
		return false;
	}
	
	public boolean isInWindow()
	{
		return inWindow;
	}
	
	public void setPosition(double xp, double yp)
	{
		glfwSetCursorPos(window.getAddress(), xp, yp);
	}
	
	public double getXPosition()
	{
		return position.x;
	}
	
	public double getYPosition()
	{
		return position.y;
	}
	
	public double getCenteredX()
	{
		return centeredPosition.x;
	}
	
	public double getCenteredY()
	{
		return centeredPosition.y;
	}
	
	@Override public Window getWindow()
	{
		return window;
	}
	
	private void enterCallback(long window, boolean enter)
	{
		inWindow = enter;
	}
	
	private void posCallback(long pWindow, double xp, double yp)
	{
		moving = true;
		
		centeredPosition.x = xp - (this.window.getWidth() / 2);
		centeredPosition.y = (this.window.getHeight() / 2) - yp;
		
		position.set(xp, yp);
	}
}
