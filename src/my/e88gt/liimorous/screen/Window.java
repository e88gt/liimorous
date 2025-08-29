
package my.e88gt.liimorous.screen;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.*;

/**
 * the window class
 */
public class Window
{
	/**
	 * the default width for window when the window is created
	 * using the {@link #Window()} constructor
	 */
	public static final int DEFAULT_WIDTH = 1280;
	
	/**
	 * the default height for window when the window is created
	 * using the {@link #Window()} constructor
	 */
	public static final int DEFAULT_HEIGHT = 720;
	
	/**
	 * the size of the window
	 */
	private int width = 1, height = 1;
	
	/**
	 * the size of the frame buffer
	 */
	private int fbWidth = 1, fbHeight = 1;
	
	/**
	 * the memory address for the window
	 */
	private final long handle;
	
	/**
	 * window constructor that calls the {@link #Window(String, int, int, boolean)}
	 * constructor using the default values:<br>
	 * - {@link #width} = {@link #DEFAULT_WIDTH}<br>
	 * - {@link #height} = {@link #DEFAULT_HEIGHT}<br>
	 * <br>
	 */
	public Window()
	{
		this(null, DEFAULT_WIDTH, DEFAULT_HEIGHT, false);
	}
	
	/**
	 * creates a window using the parameters given
	 * 
	 * @param title the title of the window<br>
	 * <br>
	 * 
	 * @param width the width of the window<br>
	 * if the width is less than 2 then the window will automatically be maximized<br>
	 * <br>
	 * 
	 * @param height the height of the window<br>
	 * if the heightis less than 2 then the window will automatically be maximized<br>
	 * <br>
	 * 
	 * @param vsync whether we want to use vsync or not<br>
	 * <br>
	 */
	public Window(String title, int width, int height, boolean vsync)
	{
		init();
		
		this.width = Math.max(width, 1);
		this.height = Math.max(height, 1);
		
		windowHints();
		
		handle = createWindow(title);
		
		windowCallbacks();
		centerWindow();
		makeContext(vsync);
	}
	
	/**
	 * processes all the window events (events
	 * such as keyboard key presses, mouse button
	 * clicks, etc) retrieved by the window
	 */
	public void pollEvents()
	{
		glfwPollEvents();
	}
	
	/**
	 * similar to {@link #waitEvents()} but {@link #timeEvents(double)}
	 * will process events when there is at least 1 events OR if it has
	 * reached the time specified
	 * 
	 * @param seconds the time specified
	 */
	public void timeEvents(double seconds)
	{
		glfwWaitEventsTimeout(seconds);
	}
	
	/**
	 * processes events like {@link #pollEvents()} but unlike
	 * {@link #pollEvents()}, {@link #waitEvents()} actually
	 * waits until at least 1 event is retrieved by the window
	 * and it will behave like {@link #pollEvents()} if there
	 * are more than 1 event is queued
	 */
	public void waitEvents()
	{
		glfwWaitEvents();
	}
	
	/**
	 * the window is double buffered and this
	 * will swap the two buffers of the window
	 */
	public void swapBuffers()
	{
		glfwSwapBuffers(handle);
	}
	
	/**
	 * once done using the
	 * window, you may destroy it
	 */
	public void destroy()
	{
		glfwFreeCallbacks(handle);
		glfwDestroyWindow(handle);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	/**
	 * checks whether the X button on the window
	 * is clicked, this is one of the event
	 * processed by {@link #pollEvents()}
	 * 
	 * @return whether the X button on the window has been clicked
	 */
	public boolean shouldClose()
	{
		return glfwWindowShouldClose(handle);
	}
	
	/**
	 * gets the width of the frame buffer
	 * 
	 * @return (int) the width of the frame buffer
	 */
	public int getFbWidth()
	{
		return fbWidth;
	}
	
	/**
	 * gets the height of the frame buffer
	 * 
	 * @return (int) the height of the frame buffer
	 */
	public int getFbHeight()
	{
		return fbHeight;
	}
	
	/**
	 * this will initialize the library
	 */
	private void init()
	{
		glfwSetErrorCallback(this::errorCallback);
		
		if (!glfwInit())
			throw new IllegalStateException("Failed to initialize window");
	}
	
	/**
	 * the error callback
	 * 
	 * @param code the error code<br><br>
	 * @param description the description of the error<br><br>
	 * @throws IllegalStateException when an unexpected error occurs
	 */
	private void errorCallback(int code, long description) throws IllegalStateException
	{
		throw new IllegalStateException(GLFWErrorCallback.getDescription(description));
	}
	
	/**
	 * sets the window hints before creating the window
	 */
	private void windowHints()
	{
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
		glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		
		if (width == 1 || height == 1)
		{
			glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
			glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		}
	}
	
	/**
	 * the private method that actually creates the window
	 * 
	 * @param title the title of the window, that can be null<br><br>
	 * @return the address of the window<br><br>
	 * @throws IllegalStateException if the {@link #handle} is {@link MemoryUtil#NULL}<br><br>
	 */
	private long createWindow(String title) throws IllegalStateException
	{
		final long window = glfwCreateWindow(width, height, title == null ? "" : title, NULL, NULL);
		
		if (window == NULL)
			throw new IllegalStateException("Failed to create window");
		
		return window;
	}
	
	/**
	 * sets the callbacks for the window
	 */
	private void windowCallbacks()
	{
		glfwSetFramebufferSizeCallback(handle, this::fbSizeCallback);
	}
	
	/**
	 * the frame buffer size callback
	 * 
	 * @param window the address of the window<br><br>
	 * @param width the width of frame buffer<br><br>
	 * @param height the height of frame buffer<br><br>
	 */
	private void fbSizeCallback(long window, int width, int height)
	{
		fbWidth = width;
		fbHeight = height;
	}
	
	/**
	 * centers the window, but if the width
	 * or the height of the window
	 * is less than 2 then it will not center
	 * the window
	 */
	private void centerWindow()
	{
		if (width == 1 || height == 1)
			return;
		
		final long primaryMonitor = glfwGetPrimaryMonitor();
		final GLFWVidMode vidMode = glfwGetVideoMode(primaryMonitor);
		
		if (vidMode == null)
			return;
		
		final int x = (vidMode.width() - width) / 2;
		final int y = (vidMode.height() - height) / 2;
		
		glfwSetWindowPos(handle, x, y);
		glfwShowWindow(handle);
	}
	
	/**
	 * makes the window the current context for opengl
	 * 
	 * @param vsync this determines whether the window uses vsync or not
	 */
	private void makeContext(boolean vsync)
	{
		glfwMakeContextCurrent(handle);
		glfwSwapInterval(vsync ? GLFW_TRUE : GLFW_FALSE);
	}
}
