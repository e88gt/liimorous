package my.e88gt.liimorous.mob;

import org.joml.*;

import my.e88gt.liimorous.input.*;
import my.e88gt.liimorous.mesh.*;
import my.e88gt.liimorous.texture.*;

public interface Mob
{
	void input(Input input);
	
	void update(double delta);
	
	void destroy();
	
	Mesh getMesh();
	
	Texture getTexture();
	
	Vector3f getPosition();
	
	Vector3f getRotation();
	
	Vector3f getScale();
	
	Matrix4f getTransformation();
}
