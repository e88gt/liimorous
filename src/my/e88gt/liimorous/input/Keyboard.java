package my.e88gt.liimorous.input;

import my.e88gt.liimorous.screen.*;

public final class Keyboard implements Input
{
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
