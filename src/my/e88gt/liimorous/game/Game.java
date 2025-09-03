package my.e88gt.liimorous.game;

import my.e88gt.liimorous.input.*;
import my.e88gt.liimorous.mob.*;
import my.e88gt.liimorous.screen.*;
/**
 * the structure of the game itself
 */
public final class Game
{
	TestMob mob1, mob2;
	Camera3D camera;
	
	public Game()
	{
		camera = new Camera3D();
		
		mob1 = new TestMob(true);
		mob1.getPosition().x = 0.6F;
		mob1.getPosition().z = -1.5F;
		
		mob2 = new TestMob(false);
		mob2.getPosition().x = -0.6F;
		mob2.getPosition().z = -1.5F;
	}
	
	public void input(Input input)
	{
		camera.input(input);
	}
	
	public void update(double delta)
	{
		mob1.update(delta);
		camera.update(delta);
	}
	
	public void render(Renderer renderer)
	{
		renderer.render(camera, mob1);
		renderer.render(camera, mob2);
	}
	
	public void destroy()
	{
		mob1.destroy();
		mob2.destroy();
	}
}
