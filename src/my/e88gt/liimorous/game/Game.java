package my.e88gt.liimorous.game;

import my.e88gt.liimorous.input.*;
import my.e88gt.liimorous.mob.*;
import my.e88gt.liimorous.screen.*;

/**
 * the structure of the game itself
 */
public final class Game
{
	TestMob mob;
	Camera3D camera;
	
	public Game()
	{
		camera = new Camera3D();
		mob = new TestMob();
		mob.getPosition().z = -1;
	}
	
	public void input(Input input)
	{
	}
	
	public void update(double delta)
	{
		mob.update(delta);
	}
	
	public void render(Renderer renderer)
	{
		renderer.render(camera, mob);
	}
	
	public void destroy()
	{
		mob.destroy();
	}
}
