package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

public enum MouseButton
{
	LEFT(GLFW_MOUSE_BUTTON_LEFT),
	MIDDLE(GLFW_MOUSE_BUTTON_MIDDLE),
	RIGHT(GLFW_MOUSE_BUTTON_RIGHT),
	LAST(GLFW_MOUSE_BUTTON_LAST);
	
	private final int button;
	
	private MouseButton(int button)
	{
		this.button = button;
	}
	
	public int getButton()
	{
		return button;
	}
}
