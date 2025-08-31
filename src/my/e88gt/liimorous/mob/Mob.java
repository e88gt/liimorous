package my.e88gt.liimorous.mob;

import org.joml.*;

import my.e88gt.liimorous.mesh.*;
import my.e88gt.liimorous.texture.*;

public interface Mob
{
	Mesh getMesh();
	
	Texture getTexture();
	
	Vector3f getPosition();
	
	Vector3f getRotation();
	
	Vector3f getScale();
	
	Matrix4f getTransformation();
}
