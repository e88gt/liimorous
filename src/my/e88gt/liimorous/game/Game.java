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
	
	public Game()
	{
		mob = new TestMob();
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
		renderer.render(mob);
	}
	
	public void destroy()
	{
		mob.destroy();
	}
}
