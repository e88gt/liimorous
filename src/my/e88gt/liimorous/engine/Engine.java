
package my.e88gt.liimorous.engine;

import my.e88gt.liimorous.game.*;
import my.e88gt.liimorous.input.*;
import my.e88gt.liimorous.screen.*;
import my.e88gt.liimorous.shader.*;
import my.e88gt.liimorous.utils.*;

/**
 * the engine that manages the game<br>
 * the core of the game
 */
public final class Engine
{
	/**
	 * if the game is running or not
	 */
	private boolean running;
	
	/**
	 * how many frames are being render per-second
	 */
	private double fps;
	
	/**
	 * the window that handles windowing
	 */
	private final Window window;
	
	/**
	 * the renderer that renders
	 */
	private final Renderer renderer;
	
	/**
	 * the keyboard input
	 */
	private final Keyboard keyboard;
	
	/**
	 * the game itself that we are in
	 */
	private final Game game;
	
	/**
	 * package-private constructor because you dont
	 * need to create it anywhere else
	 */
	Engine()
	{
		window = new Window("liimorous", 1280, 720, false);
		
		renderer = new Renderer();
		renderer.clearColor(0.65F, 0.65F, 1);
		
		keyboard = new Keyboard(window);
		
		game = new Game();
	}
	
	/**
	 * this is the core of the game
	 * it has the game loop and
	 * calculates the delta time and
	 * the frames rendered each second
	 */
	void run()
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
	
	/**
	 * how many frames are rendered in a second ?
	 * 
	 * @return the fps
	 */
	public double getFPS()
	{
		return fps;
	}
	
	/**
	 * process the inputs like
	 * keyboard key pressed, mouse button clicks, mouse
	 * cursor movements and position, etc
	 * 
	 * @throws ClassCastException if wrongly downcasted
	 */
	private void input() throws ClassCastException
	{
		window.pollEvents();
		
		try
		{
			game.input(keyboard);
		}
		catch(ClassCastException e)
		{
			System.err.println(e);
		}
	}
	
	/**
	 * updates game logic
	 * 
	 * @param delta the length between last time and now in seconds
	 */
	private void update(double delta)
	{
		if(window.isResized())
			renderer.viewport(window.getFbWidth(), window.getFbHeight());
		
		game.update(delta);
	}
	
	/**
	 * clears the back screen, renders the game and swaps the buffers
	 */
	private void render()
	{
		renderer.clear();
		game.render(renderer);
		window.swapBuffers();
	}
	
	/**
	 * cleanup operation, freeing allocated memories
	 */
	private void destroy()
	{
		game.destroy();
		renderer.destroy();
		window.destroy();
	}
	
	/**
	 * start the engine
	 */
	private synchronized void start()
	{
		if (running)
			return;
		
		running = true;
	}
	
	/**
	 * stop the engine
	 */
	private synchronized void stop()
	{
		if (!running)
			return;
		
		running = false;
	}
	
	/**
	 * whether the X button on the window is clicked
	 * 
	 * @return
	 * true if it should close<br>
	 * false if it is running<br>
	 */
	private boolean shouldClose()
	{
		return window.shouldClose();
	}
}
