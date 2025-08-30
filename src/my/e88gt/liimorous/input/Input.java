package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

import my.e88gt.liimorous.screen.*;

/**
 * 
 */
public interface Input
{
	public static enum Action {
		PRESS(GLFW_PRESS),
		REPEAT(GLFW_REPEAT),
		RELEASE(GLFW_RELEASE);
		
		private final int action;
		
		private Action(int value)
		{
			action = value;
		}
		
		public int getAction()
		{
			return action;
		}
	}
	
	Window getWindow();
}
