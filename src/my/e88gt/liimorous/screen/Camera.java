package my.e88gt.liimorous.screen;

import org.joml.*;
import org.joml.Math;

public interface Camera
{
	Vector3f getPosition();
	
	Vector3f getRotation();
	
	// used width and height instead of aspect ratio
	// this is so you can use 2d camera with it
	Matrix4f getProjection(int width, int height);
	
	default Matrix4f getViewMatrix()
	{
		Matrix4f matrix = new Matrix4f();
		
		matrix.rotateX(Math.toRadians(getPosition().x));
		matrix.rotateY(Math.toRadians(getPosition().y));
		matrix.rotateZ(Math.toRadians(getPosition().z));
		matrix.translate(-getPosition().x, -getPosition().y, -getPosition().z);
		
		return matrix;
	}
}
