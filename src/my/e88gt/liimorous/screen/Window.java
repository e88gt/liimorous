
package my.e88gt.liimorous.screen;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.*;

public class Window
{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	private int width = WIDTH, height = HEIGHT;
	private final long handle;
	
	public Window()
	{
		this("", WIDTH, HEIGHT, false);
	}
	
	public Window(String title, int width, int height, boolean vsync)
	{
		init();
		
		this.width = Math.max(width, 1);
		this.height = Math.max(height, 1);
		
		windowHints();
		
		handle = createWindow(title);
		
		centerWindow();
		makeContext(vsync);
	}
	
	public void pollEvents()
	{
		glfwPollEvents();
	}
	
	public void timeEvents(double seconds)
	{
		glfwWaitEventsTimeout(seconds);
	}
	
	public void waitEvents()
	{
		glfwWaitEvents();
	}
	
	public void swapBuffers()
	{
		glfwSwapBuffers(handle);
	}
	
	public void destroy()
	{
		glfwFreeCallbacks(handle);
		glfwDestroyWindow(handle);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public boolean shouldClose()
	{
		return glfwWindowShouldClose(handle);
	}
	
	private void init()
	{
		glfwSetErrorCallback(this::errorCallback);
		
		if (!glfwInit())
			throw new IllegalStateException("Failed to initialize window");
	}
	
	private void errorCallback(int code, long description)
	{
		throw new IllegalStateException(GLFWErrorCallback.getDescription(description));
	}
	
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
	
	private long createWindow(String title)
	{
		final long window = glfwCreateWindow(width, height, title == null ? "" : title, NULL, NULL);
		
		if (window == NULL)
			throw new IllegalStateException("Failed to create window");
		
		return window;
	}
	
	private void centerWindow()
	{
		if (width == 1 || height == 1)
			return;
		
		final long primaryMonitor = glfwGetPrimaryMonitor();
		final GLFWVidMode vidMode = glfwGetVideoMode(primaryMonitor);
		
		if (vidMode == null)
			return;
		
		final int x = (vidMode.width() - WIDTH) / 2;
		final int y = (vidMode.height() - HEIGHT) / 2;
		
		glfwSetWindowPos(handle, x, y);
		glfwShowWindow(handle);
	}
	
	private void makeContext(boolean vsync)
	{
		glfwMakeContextCurrent(handle);
		glfwSwapInterval(vsync ? GLFW_TRUE : GLFW_FALSE);
	}
}
