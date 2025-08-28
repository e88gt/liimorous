
package my.e88gt.liimorous.screen;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

public class Window
{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	private long handle;
	
	private Window(String title)
	{
		init();
		windowHints();
		handle = createWindow(title);
		centerWindow();
	}
	
	public void pollEvents()
	{
		glfwPollEvents();
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
	
	private static void init()
	{
		glfwSetErrorCallback(Window::errorCallback);
		
		if (!glfwInit())
			throw new IllegalStateException("Failed to initialize window");
	}
	
	private static void errorCallback(int code, long description)
	{
		throw new IllegalStateException(GLFWErrorCallback.getDescription(description));
	}
	
	private static void windowHints()
	{
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
		glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
	}
	
	private static long createWindow(String title)
	{
		final long window = glfwCreateWindow(WIDTH, HEIGHT, title == null ? "" : title, NULL, NULL);
		
		if (window == NULL)
			throw new IllegalStateException("Failed to create window");
		
		return window;
	}
	
	private void centerWindow()
	{
		final long primaryMonitor = glfwGetPrimaryMonitor();
		final GLFWVidMode vidMode = glfwGetVideoMode(primaryMonitor);
		
		if (vidMode == null)
			return;
		
		final int x = (vidMode.width() - WIDTH) / 2;
		final int y = (vidMode.height() - HEIGHT) / 2;
		
		glfwSetWindowPos(handle, x, y);
		glfwShowWindow(handle);
		glfwMakeContextCurrent(handle);
	}
}
