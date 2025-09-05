package my.e88gt.liimorous.input;

import static org.lwjgl.glfw.GLFW.*;

public enum Keys
{
	N0(GLFW_KEY_0),
	N1(GLFW_KEY_1),
	N2(GLFW_KEY_2),
	N3(GLFW_KEY_3),
	N4(GLFW_KEY_4),
	N5(GLFW_KEY_5),
	N6(GLFW_KEY_6),
	N7(GLFW_KEY_7),
	N8(GLFW_KEY_8),
	N9(GLFW_KEY_9),
	
	A(GLFW_KEY_A),
	B(GLFW_KEY_B),
	C(GLFW_KEY_C),
	D(GLFW_KEY_D),
	E(GLFW_KEY_E),
	F(GLFW_KEY_F),
	G(GLFW_KEY_G),
	H(GLFW_KEY_H),
	I(GLFW_KEY_I),
	J(GLFW_KEY_J),
	K(GLFW_KEY_K),
	L(GLFW_KEY_L),
	M(GLFW_KEY_M),
	N(GLFW_KEY_N),
	O(GLFW_KEY_O),
	P(GLFW_KEY_P),
	Q(GLFW_KEY_Q),
	R(GLFW_KEY_R),
	S(GLFW_KEY_S),
	T(GLFW_KEY_T),
	U(GLFW_KEY_U),
	V(GLFW_KEY_V),
	W(GLFW_KEY_W),
	X(GLFW_KEY_X),
	Y(GLFW_KEY_Y),
	Z(GLFW_KEY_Z),
	
	SPACE(GLFW_KEY_SPACE),
	
	TAB(GLFW_KEY_TAB),
	ESCAPE(GLFW_KEY_ESCAPE),
	
	LAST(GLFW_KEY_LAST);
	
	private final int key;
	
	private Keys(int key)
	{
		this.key = key;
	}
	
	public int getKey()
	{
		return key;
	}
}
