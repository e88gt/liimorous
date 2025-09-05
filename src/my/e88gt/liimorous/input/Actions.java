package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

public enum Actions
{
	PRESS(GLFW_PRESS),
	REPEAT(GLFW_REPEAT),
	RELEASE(GLFW_RELEASE);
	
	private final int action;
	
	private Actions(int value)
	{
		action = value;
	}
	
	public int getAction()
	{
		return action;
	}
}
