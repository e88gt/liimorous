package my.e88gt.liimorous.screen;

import static org.lwjgl.opengl.GL46.*;

import java.nio.*;

import org.joml.*;
import org.lwjgl.opengl.*;

import my.e88gt.liimorous.scene.*;

public final class Renderer
{
	private final Vector2i size = new Vector2i(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT);
	private final Vector3f color = new Vector3f(0);
	
	public Renderer()
	{
		GL.createCapabilities();
		
		glEnable(GL_DEBUG_OUTPUT);
		glEnable(GL_DEBUG_OUTPUT_SYNCHRONOUS);
		glDebugMessageCallback(this::debugCallback, 0);
		glDebugMessageControl(GL_DONT_CARE, GL_DONT_CARE, GL_DONT_CARE, (IntBuffer) null, true);
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		glEnable(GL_STENCIL_TEST);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
	}
	
	public void setViewportSize(int width, int height)
	{
		if (size.x == width && size.y == height)
			return;
		
		size.set(width, height);
		glViewport(0, 0, width, height);
	}
	
	public void clear()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
	}
	
	public void setClearColor(float r, float g, float b)
	{
		if (color.x == r && color.y == g && color.z == b)
			return;
		
		color.set(r, g, b);
		glClearColor(r, g, b, 1);
	}
	
	public void render(Camera camera, Scene scene)
	{
		for (Renderable object : scene.getRenderables())
		{
			object.material().shaderProgram().use();
			object.mesh().vao().bind();
			glDrawElements(GL_TRIANGLES, object.mesh().elementCount(), GL_UNSIGNED_INT, 0);
		}
	}
	
	public void destroy()
	{
		GL.destroy();
	}
	
	private void debugCallback(int source, int type, int _id, int severity, int length, long message, long _userParam)
	{
		final String sSource = stringDebugGL(source);
		final String sType = stringDebugGL(type).toLowerCase();
		final String sSeverity = stringDebugGL(severity);
		
		System.err.println("- " + sSeverity + " " + sType + " from: " + sSource);
		System.err.println(GLDebugMessageCallback.getMessage(length, message) + "\n");
	}
	
	private String stringDebugGL(int theEnum)
	{
		return switch (theEnum)
		{
		case GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR -> "Deprecated behavior";
		case GL_DEBUG_TYPE_ERROR -> "Error";
		case GL_DEBUG_TYPE_MARKER -> "Marker";
		case GL_DEBUG_TYPE_OTHER -> "Other";
		case GL_DEBUG_TYPE_PERFORMANCE -> "Performance";
		case GL_DEBUG_TYPE_POP_GROUP -> "Pop group";
		case GL_DEBUG_TYPE_PORTABILITY -> "Portability";
		case GL_DEBUG_TYPE_PUSH_GROUP -> "Push group";
		case GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR -> "Undefined behavior";
	
		case GL_DEBUG_SOURCE_API -> "API";
		case GL_DEBUG_SOURCE_APPLICATION -> "Application";
		case GL_DEBUG_SOURCE_OTHER -> "Other";
		case GL_DEBUG_SOURCE_SHADER_COMPILER -> "Shader compiler";
		case GL_DEBUG_SOURCE_THIRD_PARTY -> "Third party";
		case GL_DEBUG_SOURCE_WINDOW_SYSTEM -> "Window system";
	
		case GL_DEBUG_SEVERITY_LOW -> "Minor";
		case GL_DEBUG_SEVERITY_MEDIUM -> "Moderate";
		case GL_DEBUG_SEVERITY_HIGH -> "Major";
		case GL_DEBUG_SEVERITY_NOTIFICATION -> "Notification";
	
		default -> "No";
		};
	}
}
