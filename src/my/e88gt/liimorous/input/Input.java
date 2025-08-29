package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

import my.e88gt.liimorous.screen.*;

public interface Input
{
	public static enum Action {
		PRESS(GLFW_PRESS),
		REPEAT(GLFW_REPEAT),
		RELEASE(GLFW_RELEASE);
		
		private final int value;
		
		private Action(int value)
		{
			this.value = value;
		}
	}
	
	Window getWindow();
}
