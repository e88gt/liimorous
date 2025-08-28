package my.e88gt.liimorous.screen;

import static org.lwjgl.opengl.GL46.*;

import org.lwjgl.opengl.*;

import my.e88gt.liimorous.scene.*;

public class Renderer
{
	private Renderer()
	{
		GL.createCapabilities();
	}
	
	public void create()
	{
	}
	
	public void clear()
	{
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public void clearColor(float r,float g,float b)
	{
		glClearColor(r,g,b,1);
	}
	
	public void render(Renderable r)
	{
		r.getVertexArray().bind();
		glDrawElements(GL_TRIANGLES, r.getElementCount(), GL_UNSIGNED_INT, 0);
	}
	
	public void render(Scene scene)
	{
		for(Renderable object : scene.getRenderables())
		{
			object.getVertexArray().bind();
			glDrawElements(GL_TRIANGLES, object.getElementCount(), GL_UNSIGNED_INT, 0);
		}
	}
	
	public void destroy()
	{
		GL.destroy();
	}
}
