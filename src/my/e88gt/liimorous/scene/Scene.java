package my.e88gt.liimorous.scene;

import java.util.*;

import my.e88gt.liimorous.input.*;
import my.e88gt.liimorous.mob.*;

public interface Scene
{
	void input(Input input);
	
	void update(double delta);
	
	void destroy();
	
	List<Mob> getMobs();
}
