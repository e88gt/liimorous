package my.e88gt.liimorous.screen;

import org.joml.*;

public interface Camera
{
	
	// used width and height instead of aspect ration
	// this is so you can use 2d camera with it
	Matrix4f getProjection(int width, int height);
	
	Matrix4f getViewMatrix();
}
