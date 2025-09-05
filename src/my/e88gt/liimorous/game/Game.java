package my.e88gt.liimorous.game;

import my.e88gt.liimorous.input.*;
import my.e88gt.liimorous.screen.*;

/**
 * the structure of the game itself
 */
public final class Game
{
	Camera3D camera;
	
	public Game()
	{
		camera = new Camera3D();
	}
	
	public void input(Input input)
	{
		camera.input(input);
	}
	
	public void update(double delta)
	{
		camera.update(delta);
	}
	
	public void render(Renderer renderer)
	{
	}
	
	public void destroy()
	{
	}
}
