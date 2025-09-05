package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

/**
 * the actions we do on our inputs<br>
 * like clicking on mouse button<br>
 * or pressing on our keyboard keys<br>
 */
public enum Actions
{
	/**
	 * when its held
	 */
	PRESS(GLFW_PRESS),
	
	/**
	 * i dont know
	 */
	REPEAT(GLFW_REPEAT),
	
	/**
	 * when its released
	 */
	RELEASE(GLFW_RELEASE);
	
	/**
	 * the value of that action
	 */
	private final int action;
	
	/**
	 * java's private enum constructor
	 * 
	 * @param action
	 */
	private Actions(int action)
	{
		this.action = action;
	}
	
	/**
	 * @return the value of that action
	 */
	public int getAction()
	{
		return action;
	}
}
