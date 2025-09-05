
package my.e88gt.liimorous.engine;

import my.e88gt.liimorous.game.*;
import my.e88gt.liimorous.input.*;
import my.e88gt.liimorous.screen.*;
import my.e88gt.liimorous.utils.*;

/**
 * the engine that manages the game<br>
 * the core of the game
 */
public final class Engine
{
	/**
	 * for single threaded synchronization
	 */
	public static final Object LOCK = new Object();
	
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
	 * the mouse clicks
	 */
	private final MouseButton click;
	
	/**
	 * the mouse cursor movements
	 */
	private final MouseCursor cursor;
	
	/**
	 * the mouse scrolling inputs
	 */
	private final MouseScroll scroll;
	
	/**
	 * the game itself that we are in
	 */
	private final Game game;
	
	/**
	 * package-private constructor because you dont need to create it anywhere else
	 */
	Engine()
	{
		window = new Window("liimorous", 1280, 720, false);
		renderer = new Renderer();
		
		keyboard = new Keyboard(window);
		click = new MouseButton(window);
		cursor = new MouseCursor(window);
		scroll = new MouseScroll(window);
		
		game = new Game();
	}
	
	/**
	 * this is the core of the game it has the game loop and calculates the delta time and the frames
	 * rendered each second
	 */
	void run()
	{
		final Time time = new Time();
		double lastTime = time.getSystemNano();
		
		start();
		
		while (isRunning())
		{
			input();
			if (isEnding())
				stop();
			
			final double currentTime = time.getSystemNano();
			final double deltaTime = (currentTime - lastTime) / Time.NS_PER_SEC;
			
			lastTime = currentTime;
			fps = 1 / deltaTime;
			
			update(deltaTime);
			render();
		}
		
		destroy();
	}
	
	/**
	 * checks whether the engine is running or not
	 * 
	 * @return true if it is still running<br>
	 *         false if it has stopped<br>
	 */
	private boolean isRunning()
	{
		return running;
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
	 * process the inputs like keyboard key pressed, mouse button clicks, mouse cursor movements and
	 * position, etc
	 * 
	 * @throws ClassCastException if wrongly downcasted
	 */
	private void input() throws ClassCastException
	{
		window.pollEvents();
		
		try
		{
			game.input(keyboard);
			game.input(cursor);
			game.input(click);
			game.input(scroll);
		}
		catch (ClassCastException e)
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
		if (window.isResized())
			renderer.setViewportSize(window.getFbWidth(), window.getFbHeight());
		
		renderer.setClearColor(0.65F, 0.65F, 1);
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
	 * cleanup operation, freeing resources allocated
	 */
	private void destroy()
	{
		game.destroy();
		renderer.destroy();
		window.destroy();
	}
	
	/**
	 * starts the engine
	 */
	private void start()
	{
		synchronized (Engine.LOCK)
		{
			if (running)
				return;
			
			running = true;
		}
	}
	
	/**
	 * stops the engine
	 */
	private void stop()
	{
		synchronized (Engine.LOCK)
		{
			if (!running)
				return;
			
			running = false;
		}
	}
	
	/**
	 * whether the X button on the window is clicked
	 * 
	 * @return true if it should close<br>
	 *         false if it is running<br>
	 */
	private boolean isEnding()
	{
		return window.isCloseRequested();
	}
}
