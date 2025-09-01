package my.e88gt.liimorous.game;

import my.e88gt.liimorous.engine.*;
import my.e88gt.liimorous.input.*;
import my.e88gt.liimorous.mob.*;
import my.e88gt.liimorous.screen.*;

/**
 * the structure of the game itself
 */
public final class Game
{
	TestMob mob, mob2;
	Camera3D camera;
	
	public Game()
	{
		camera = new Camera3D();
		
		mob = new TestMob(true);
		mob.getPosition().x = 0.6F;
		mob.getPosition().z = -1.5F;
		
		mob2 = new TestMob(false);
		mob2.getPosition().x = -0.6F;
		mob2.getPosition().z = -1.5F;
	}
	
	public void input(Input input)
	{
		if (input instanceof Keyboard key)
			if (key.isDown(Key.ESCAPE))
				Launcher.LAUNCHER.getEngine().getWindow().setShouldClose(true);
		
		camera.input(input);
	}
	
	public void update(double delta)
	{
		mob.update(delta);
		camera.update(delta);
	}
	
	public void render(Renderer renderer)
	{
		renderer.render(camera, mob);
		renderer.render(camera, mob2);
	}
	
	public void destroy()
	{
		mob.destroy();
		mob2.destroy();
	}
}
