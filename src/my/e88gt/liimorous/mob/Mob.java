package my.e88gt.liimorous.mob;

import org.joml.*;

import my.e88gt.liimorous.mesh.*;
import my.e88gt.liimorous.texture.*;

public interface Mob
{
	Mesh getMesh();
	
	Texture getTexture();
	
	Matrix4f getTransformation();
}
