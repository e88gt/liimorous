package my.e88gt.liimorous.tests;

import my.e88gt.liimorous.screen.*;

public class Test
{
	public static void main(String... args)
	{
		Window window = new Window();
		Renderer renderer = new Renderer();
		
		Window win2 = new Window();
		Renderer r2 = new Renderer();
		
		while(!window.shouldClose())
		{
			window.pollEvents();
			win2.pollEvents();
			
			window.swapBuffers();
			win2.swapBuffers();
		}
		
		win2.destroy();
		r2.destroy();
		
		renderer.destroy();
		window.destroy();
	}
}
