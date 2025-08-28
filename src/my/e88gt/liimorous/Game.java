package my.e88gt.liimorous;

import my.e88gt.liimorous.mesh.*;
import my.e88gt.liimorous.screen.*;

public class Game
{
	private final Window window = Window.WINDOW;
	private final Renderer renderer = Renderer.RENDERER;
	
	Mesh mesh;
	
	public Game()
	{
		window.create();
		renderer.create();
		
		mesh = Mesh.PLANE;
	}
	
	public void update()
	{
		window.pollEvents();
	}
	
	public void render()
	{
		renderer.clear();
		renderer.clearColor(0.65F, 0.65F, 1);
		renderer.render(mesh);
		window.swapBuffers();
	}
	
	public void destroy()
	{
		mesh.destroy();
		
		renderer.destroy();
		window.destroy();
	}
	
	public static void main(String... args)
	{
		Game g = new Game();
		
		while (!Window.WINDOW.shouldClose())
		{
			g.update();
			g.render();
		}
		
		g.destroy();
	}
}
