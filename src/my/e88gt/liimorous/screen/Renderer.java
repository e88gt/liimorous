package my.e88gt.liimorous.screen;

import static org.lwjgl.opengl.GL11.*;

import org.joml.*;
import org.lwjgl.opengl.*;

import my.e88gt.liimorous.shader.*;
import my.e88gt.liimorous.texture.*;

public final class Renderer
{
	private final Vector2i size = new Vector2i(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT);
	private final Vector3f color = new Vector3f(0);
	private final CoreShader shader;
	
	public Renderer()
	{
		GL.createCapabilities();
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
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public void clearColor(float r, float g, float b)
	{
		if (color.x == r && color.y == g && color.z == b)
			return;
		
		color.set(r, g, b);
		glClearColor(r, g, b, 1);
	}
	
	public void render(Renderable r, Texture texture)
	{
		shader.use();
		shader.useTexture(0);
		texture.bind();
		r.getVertexArray().bind();
		glDrawElements(GL_TRIANGLES, r.getElementCount(), GL_UNSIGNED_INT, 0);
	}
	
	public void destroy()
	{
		shader.delete();
		GL.destroy();
	}
}
