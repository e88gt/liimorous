package my.e88gt.liimorous.screen;

import my.e88gt.liimorous.material.*;
import my.e88gt.liimorous.mesh.*;

public interface Renderable
{
	Material getMaterial();
	
	Mesh getMesh();
}
