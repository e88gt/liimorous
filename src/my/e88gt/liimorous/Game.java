package my.e88gt.liimorous;

import my.e88gt.liimorous.screen.*;

public class Game
{
	private final Window window = Window.WINDOW;
	
	public Game()
	{
		window.create();
	}
	
	public void update()
	{
		window.pollEvents();
	}
	
	public void render()
	{
		window.swapBuffers();
	}
	
	public void destroy()
	{
		window.destroy();
	}
	
	public static void main(String... args)
	{
		Game game = new Game();
		
		while (!Window.WINDOW.shouldClose())
		{
			game.update();
			game.render();
		}
		
		game.destroy();
	}
}
