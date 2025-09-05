package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

import java.util.*;

import my.e88gt.liimorous.screen.*;

public final class Keyboard implements Input
{
	private final ArrayList<Boolean> keys = new ArrayList<>(Keys.LAST.getKey());
	private final Window window;
	
	public Keyboard(Window window)
	{
		this.window = window;
		
		for (int i = 0; i < Keys.LAST.getKey(); i++)
			keys.add(false);
		
		glfwSetKeyCallback(window.getAddress(), this::keyCallback);
	}
	
	private void keyCallback(long window, int key, int scancode, int action, int mods)
	{
		boolean isDown = action != Actions.RELEASE.getAction();
		
		keys.set(key, isDown);
	}
	
	public boolean isDown(int i)
	{
		return keys.get(i);
	}
	
	public boolean isDown(Keys key)
	{
		return keys.get(key.getKey());
	}
}
