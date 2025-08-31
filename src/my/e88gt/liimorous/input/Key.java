package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

public enum Key
{
	TAB(GLFW_KEY_TAB),
	ESCAPE(GLFW_KEY_ESCAPE),
	SPACE(GLFW_KEY_SPACE),
	LAST(GLFW_KEY_LAST);
	
	private final int key;
	
	private Key(int key)
	{
		this.key = key;
	}
	
	public int getKey()
	{
		return key;
	}
}
