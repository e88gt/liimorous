package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

public enum Key
{
	W(GLFW_KEY_W),
	S(GLFW_KEY_S),
	D(GLFW_KEY_D),
	A(GLFW_KEY_A),
	
	Q(GLFW_KEY_Q),
	
	X(GLFW_KEY_X),
	Y(GLFW_KEY_Y),
	Z(GLFW_KEY_Z),
	
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
