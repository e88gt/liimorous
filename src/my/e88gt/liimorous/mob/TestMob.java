package my.e88gt.liimorous.mob;

import java.io.*;

import org.joml.*;
import org.joml.Math;

import my.e88gt.liimorous.input.*;
import my.e88gt.liimorous.mesh.*;
import my.e88gt.liimorous.texture.*;

public final class TestMob implements Mob
{
	private final Vector3f position, rotation, scale;
	private final Texture texture;
	private final Mesh mesh;
	
	public TestMob(boolean aa)
	{
		position = new Vector3f();
		rotation = new Vector3f();
		scale = new Vector3f(1F);
		
		try
		{
			texture = new Texture(Texture.FOLDER_PATH + "tests/Test1.png", aa);
		}
		catch (IOException e)
		{
			throw new UncheckedIOException(e);
		}
		
		mesh = Mesh.Preset.CUBE;
	}
	
	@Override public void input(Input input)
	{
		if(input instanceof Keyboard key)
		{
			if(key.isDown(Key.X))
				position.x += 0.001F;
			
			if(key.isDown(Key.Z))
				position.x -= 0.001F;
		}
	}
	
	@Override public void update(double delta)
	{
		rotation.x += delta * 60;
		rotation.y += delta * 10;
		rotation.z += delta * 100;
	}
	
	@Override public void destroy()
	{
		texture.delete();
		mesh.destroy();
	}
	
	@Override public Vector3f getPosition()
	{
		return position;
	}
	
	@Override public Vector3f getRotation()
	{
		return rotation;
	}
	
	@Override public Vector3f getScale()
	{
		return scale;
	}
	
	@Override public Mesh getMesh()
	{
		return mesh;
	}
	
	@Override public Texture getTexture()
	{
		return texture;
	}
	
	@Override public Matrix4f getTransformation()
	{
		Matrix4f transformation = new Matrix4f();
		
		transformation.translate(position);
		transformation.rotate(Math.toRadians(rotation.x), 1, 0, 0);
		transformation.rotate(Math.toRadians(rotation.y), 0, 1, 0);
		transformation.rotate(Math.toRadians(rotation.z), 0, 0, 1);
		transformation.scale(scale);
		
		return transformation;
	}
}
