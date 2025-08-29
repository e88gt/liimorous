
package my.e88gt.liimorous.engine;

import my.e88gt.liimorous.screen.*;
import my.e88gt.liimorous.utils.*;

public class Engine
{
	public static final boolean DEBUG = true;
	private boolean running;
	private double fps;
	
	private final Window window;
	
	public Engine()
	{
		window = new Window("liimorous", 480, 360, false);
	}
	
	public void run()
	{
		Time time = new Time();
		double lastTime = time.getNano();
		
		start();
		
		while (running)
		{
			input();
			if (shouldClose())
				stop();
			
			double currentTime = time.getNano();
			double deltaTime = (currentTime - lastTime) / Time.NS_PER_SEC;
			
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
	}
	
	private void render()
	{
		window.swapBuffers();
	}
	
	private void destroy()
	{
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
