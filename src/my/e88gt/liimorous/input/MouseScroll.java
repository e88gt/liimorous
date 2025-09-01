package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.*;

import my.e88gt.liimorous.screen.*;

public class MouseScroll implements Input
{
	private boolean scrolling = false;
	private final Vector2d scroll = new Vector2d(0);
	private final Window window;
	
	public MouseScroll(Window window)
	{
		this.window = window;
		glfwSetScrollCallback(window.getAddress(), this::scrollCallback);
	}
	
	public boolean isScrolling()
	{
		if (scrolling)
		{
			scrolling = false;
			return true;
		}
		
		return false;
	}
	
	public double getScrollX()
	{
		return scroll.x;
	}
	
	public double getScrollY()
	{
		return scroll.y;
	}
	
	@Override public Window getWindow()
	{
		return window;
	}
	
	private void scrollCallback(long window, double xs, double ys)
	{
		scrolling = true;
		
		scroll.set(xs, ys);
	}
}
