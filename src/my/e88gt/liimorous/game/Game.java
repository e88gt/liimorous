package my.e88gt.liimorous.game;

import my.e88gt.liimorous.input.*;
import my.e88gt.liimorous.scene.*;
import my.e88gt.liimorous.screen.*;
/**
 * the structure of the game itself
 */
public final class Game
{
	Camera3D camera;
	TestScene scene;
	
	public Game()
	{
		camera = new Camera3D();
		scene = new TestScene();
	}
	
	public void input(Input input)
	{
		camera.input(input);
		scene.input(input);
	}
	
	public void update(double delta)
	{
		camera.update(delta);
		scene.update(delta);
	}
	
	public void render(Renderer renderer)
	{
		renderer.render(camera, scene);
	}
	
	public void destroy()
	{
		scene.destroy();
	}
}
