package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

import java.util.*;

import my.e88gt.liimorous.screen.*;

public class MouseClick implements Input
{
	private final ArrayList<Boolean> buttons = new ArrayList<>(MouseButton.LAST.getButton());
	private final Window window;
	
	public MouseClick(Window window)
	{
		this.window = window;
		
		for (int i = 0; i < MouseButton.LAST.getButton(); i++)
			buttons.add(false);
		
		glfwSetMouseButtonCallback(window.getAddress(), this::clickCallback);
	}
	
	public boolean isDown(MouseButton button)
	{
		return buttons.get(button.getButton());
	}
	
	public boolean isDownI(int i)
	{
		return buttons.get(i);
	}
	
	@Override public Window getWindow()
	{
		return window;
	}
	
	private void clickCallback(long window, int button, int action, int mods)
	{
		boolean isDown = action != Action.RELEASE.getAction();
		
		buttons.set(button, isDown);
	}
}
