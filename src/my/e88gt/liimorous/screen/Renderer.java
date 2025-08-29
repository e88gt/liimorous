package my.e88gt.liimorous.screen;

import static org.lwjgl.opengl.GL11.*;

import org.joml.*;
import org.lwjgl.opengl.*;

import my.e88gt.liimorous.shader.*;

public final class Renderer
{
	private int width = Window.DEFAULT_WIDTH;
	private int height = Window.DEFAULT_HEIGHT;
	
	private final Vector3f color = new Vector3f(0);
	private final CoreShader shader;
	
	public Renderer()
	{
		GL.createCapabilities();
		shader = new CoreShader(CoreShader.FOLDER_PATH + "vertex_shader.glsl", CoreShader.FOLDER_PATH + "fragment_shader.glsl");
	}
	
	public void viewport(int width, int height)
	{
		if (this.width == width && this.height == height)
			return;
		
		this.width = width;
		this.height = height;
		glViewport(0, 0, width, height);
	}
	
	public void clear()
	{
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public void clearColor(float r, float g, float b)
	{
		if (color.x == r && color.y == g && color.z == b)
			return;
		
		color.set(r, g, b);
		glClearColor(r, g, b, 1);
	}
	
	public void render(Renderable r)
	{
		shader.use();
		r.getVertexArray().bind();
		glDrawElements(GL_TRIANGLES, r.getElementCount(), GL_UNSIGNED_INT, 0);
	}
	
	public void destroy()
	{
		shader.delete();
		GL.destroy();
	}
}
