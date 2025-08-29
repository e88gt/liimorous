package my.e88gt.liimorous.game;

import my.e88gt.liimorous.input.*;
import my.e88gt.liimorous.mesh.*;
import my.e88gt.liimorous.screen.*;

public final class Game
{
	final Mesh mesh;
	
	public Game()
	{
		mesh = Mesh.PLANE;
	}
	
	public void input(Input input)
	{
	}
	
	public void update(double delta)
	{
	}
	
	public void render(Renderer renderer)
	{
		renderer.render(mesh);
	}
	
	public void destroy()
	{
		mesh.destroy();
	}
}
