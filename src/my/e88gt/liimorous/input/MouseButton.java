package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

import java.util.*;

import my.e88gt.liimorous.screen.*;

public final class MouseButton implements Input
{
	private final ArrayList<Boolean> buttons = new ArrayList<>(MouseButtons.LAST.getButton());
	private final Window window;
	
	public MouseButton(Window window)
	{
		this.window = window;
		
		for (int i = 0; i < MouseButtons.LAST.getButton(); i++)
			buttons.add(false);
		
		glfwSetMouseButtonCallback(window.getAddress(), this::clickCallback);
	}
	
	public boolean isDown(MouseButtons button)
	{
		return buttons.get(button.getButton());
	}
	
	public boolean isDown(int i)
	{
		return buttons.get(i);
	}
	
	private void clickCallback(long window, int button, int action, int mods)
	{
		boolean isDown = action != Actions.RELEASE.getAction();
		
		buttons.set(button, isDown);
	}
}
