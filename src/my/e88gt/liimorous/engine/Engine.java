
package my.e88gt.liimorous.engine;

import java.time.*;

import my.e88gt.liimorous.screen.*;
import my.e88gt.liimorous.utils.*;

public class Engine
{
	private boolean running;
	
	private final Window window;
	
	public Engine()
	{
		window = new Window("liimorous", 0, 0, true);
	}
	
	public void run()
	{
		Time time = new Time();
		double lastTime = time.getNano();
		final int[] fps = new int[1];
		
		Thread t = new Thread(() -> {
			while(running) {
				try {
					Thread.sleep(Duration.ofSeconds(1));
					System.out.println("running at " + fps[0] + " fps");
				}catch(InterruptedException e) {
					System.err.println(e);
				}
			}
		});
		
		start();
		t.start();
		
		while (running)
		{
			input();
			if (shouldClose())
				stop();
			
			double currentTime = time.getNano();
			double deltaTime = (currentTime - lastTime) / Time.NS_PER_SEC;
			lastTime = currentTime;
			fps[0] = (int) (1/ deltaTime);
			
			update(deltaTime);
			render();
		}
		
		try {
			t.join();
		}catch(InterruptedException e) {
		}
		
		destroy();
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
