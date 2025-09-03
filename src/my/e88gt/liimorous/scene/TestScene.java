package my.e88gt.liimorous.scene;

import java.util.*;

import my.e88gt.liimorous.input.*;
import my.e88gt.liimorous.mob.*;

public final class TestScene implements Scene
{
	private List<Mob> mobs;
	
	public TestScene()
	{
		mobs = new ArrayList<>();
		mobs.add(new TestMob(true));
		mobs.add(new TestMob(false));
		
		mobs.get(0).getPosition().z = -1.5F;
		mobs.get(1).getPosition().z = -1.5F;
		
		mobs.get(0).getPosition().x = +0.6F;
		mobs.get(1).getPosition().x = -0.6F;
	}
	
	@Override public void input(Input input)
	{
		mobs.get(1).input(input);
	}
	
	@Override public void update(double delta)
	{
		mobs.get(0).update(delta);
	}
	
	@Override public void destroy()
	{
		mobs.forEach(Mob::destroy);
	}
	
	@Override public List<Mob> getMobs()
	{
		return mobs;
	}
}
