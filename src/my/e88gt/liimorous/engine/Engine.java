
package my.e88gt.liimorous.engine;

import my.e88gt.liimorous.game.*;
import my.e88gt.liimorous.screen.*;
import my.e88gt.liimorous.utils.*;

public final class Engine
{
	private boolean running;
	private double fps;
	
	private final Window window;
	private final Renderer renderer;
	private final Game game;
	
	public Engine()
	{
		window = new Window("liimorous", 1280, 720, false);
		renderer = new Renderer();
		renderer.clearColor(0.65F, 0.65F, 1);
		game = new Game();
	}
	
	public void run()
	{
		final Time time = new Time();
		double lastTime = time.getNano();
		
		start();
		
		while (running)
		{
			input();
			if (shouldClose())
				stop();
			
			final double currentTime = time.getNano();
			final double deltaTime = (currentTime - lastTime) / Time.NS_PER_SEC;
			
			lastTime = currentTime;
			fps = 1 / deltaTime;
			
			update(deltaTime);
			render();
		}
		
		destroy();
	}
	
	public double getFPS()
	{
		return fps;
	}
	
	private void input()
	{
		window.pollEvents();
	}
	
	private void update(double delta)
	{
		if(window.isResized())
			renderer.viewport(window.getFbWidth(), window.getFbHeight());
		
		game.update(delta);
	}
	
	private void render()
	{
		renderer.clear();
		game.render(renderer);
		window.swapBuffers();
	}
	
	private void destroy()
	{
		game.destroy();
		renderer.destroy();
		window.destroy();
	}
	
	private synchronized void start()
	{
		if (running)
			return;
		
		running = true;
	}
	
	private synchronized void stop()
	{
		if (!running)
			return;
		
		running = false;
	}
	
	private boolean shouldClose()
	{
		return window.shouldClose();
	}
}
