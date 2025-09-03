package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.*;
import org.joml.Math;

import my.e88gt.liimorous.screen.*;

public final class MouseCursor implements Input
{
	private boolean moving, inWindow;
	private final Vector2d position, lastPosition, centeredPosition;
	private final Window window;
	
	public MouseCursor(Window window)
	{
		this.window = window;
		position = new Vector2d(0);
		lastPosition = new Vector2d(0);
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
	
	public void setVisible(boolean showCursor)
	{
		glfwSetInputMode(window.getAddress(), GLFW_CURSOR, showCursor ? GLFW_CURSOR_NORMAL : GLFW_CURSOR_HIDDEN);
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
	
	public double getDeltaX()
	{
		return (position.x - lastPosition.x);
	}
	
	public double getDeltaY()
	{
		return (position.y - lastPosition.y);
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
		
		lastPosition.set(position);
		
		centeredPosition.x = xp - (this.window.getWidth() / 2);
		centeredPosition.y = (this.window.getHeight() / 2) - yp;
		
		position.set(xp, yp);
	}
}
