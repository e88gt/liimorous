package my.e88gt.liimorous.mob;

import java.io.*;

import org.joml.*;
import org.joml.Math;

import my.e88gt.liimorous.mesh.*;
import my.e88gt.liimorous.texture.*;

public class TestMob implements Mob
{
	private final Vector3f position, rotation, scale; 
	private final Texture texture;
	
	Mesh mesh;
	
	public TestMob()
	{
		position = new Vector3f();
		rotation = new Vector3f();
		scale = new Vector3f(1);
		
		try {
			texture = new Texture(Texture.FOLDER_PATH + "test1.png");
		}catch(IOException e) {
			throw new UncheckedIOException(e);
		}
		
		mesh = Mesh.BuiltIn.MESH;
	}
	
	public void update(double delta)
	{
		rotation.y += delta * 60;
	}
	
	public void destroy()
	{
		texture.delete();
		mesh.destroy();
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
