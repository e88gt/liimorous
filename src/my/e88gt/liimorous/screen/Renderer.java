package my.e88gt.liimorous.screen;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL43.*;

import java.nio.*;

import org.joml.*;
import org.lwjgl.opengl.*;

import my.e88gt.liimorous.mob.*;
import my.e88gt.liimorous.shader.*;

public final class Renderer
{
	private final Vector2i size = new Vector2i(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT);
	private final Vector3f color = new Vector3f(0);
	
	private final CoreShader shader;
	
	public Renderer()
	{
		GL.createCapabilities();
		
		glEnable(GL_DEBUG_OUTPUT);
		glEnable(GL_DEBUG_OUTPUT_SYNCHRONOUS);
		glDebugMessageCallback(this::debugCallback, 0);
		glDebugMessageControl(GL_DONT_CARE, GL_DONT_CARE, GL_DONT_CARE, (IntBuffer) null, true);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		
		shader = new CoreShader(CoreShader.FOLDER_PATH + "vertex_shader.glsl", CoreShader.FOLDER_PATH + "fragment_shader.glsl");
	}
	
	public void viewport(int width, int height)
	{
		if (size.x == width && size.y == height)
			return;
		
		size.set(width, height);
		glViewport(0, 0, width, height);
	}
	
	public void clear()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void clearColor(float r, float g, float b)
	{
		if (color.x == r && color.y == g && color.z == b)
			return;
		
		color.set(r, g, b);
		glClearColor(r, g, b, 1);
	}
	
	public void render(Camera camera, Mob mob)
	{
		shader.use();
		
		shader.useTexture(0);
		mob.getTexture().bind();
		
		shader.updateProjectionMatrix(camera.getProjection(size.x, size.y));
		shader.updateViewMatrix(camera.getViewMatrix());
		shader.updateWorldMatrix(mob.getTransformation());
		
		mob.getMesh().getVertexArray().bind();
		glDrawElements(GL_TRIANGLES, mob.getMesh().getElementCount(), GL_UNSIGNED_INT, 0);
	}
	
	public void destroy()
	{
		shader.delete();
		GL.destroy();
	}
	
	private void debugCallback(int source, int type, int id, int severity, int length, long message, long userParameter)
	{
		String sSource = stringDebugGL(source);
		String sType = stringDebugGL(type).toLowerCase();
		String sSeverity = stringDebugGL(severity);
		
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
