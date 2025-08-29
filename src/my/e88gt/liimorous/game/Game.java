package my.e88gt.liimorous.game;

import java.io.*;

import my.e88gt.liimorous.input.*;
import my.e88gt.liimorous.mesh.*;
import my.e88gt.liimorous.screen.*;
import my.e88gt.liimorous.texture.*;

public final class Game
{
	final Mesh mesh;
	final Texture texture;
	
	public Game()
	{
		mesh = Mesh.PLANE;
		try
		{
			texture = new Texture(Texture.FOLDER_PATH + "test1.png");
		}
		catch (IOException e)
		{
			throw new UncheckedIOException(e);
		}
	}
	
	public void input(Input input)
	{
	}
	
	public void update(double delta)
	{
	}
	
	public void render(Renderer renderer)
	{
		renderer.render(mesh, texture);
	}
	
	public void destroy()
	{
		texture.delete();
		mesh.destroy();
	}
}
