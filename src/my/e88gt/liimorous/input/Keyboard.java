package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

import java.util.*;

import my.e88gt.liimorous.screen.*;

public final class Keyboard implements Input
{
	public static enum Key {
		LAST(GLFW_KEY_LAST)
		;
		
		private final int key;
		
		private Key(int key)
		{
			this.key = key;
		}
	}
	
	private final ArrayList<Boolean> keys = new ArrayList<>();
	private final Window window;
	
	public Keyboard(Window window)
	{
		this.window = window;
	}
	
	@Override public Window getWindow()
	{
		return window;
	}
}
