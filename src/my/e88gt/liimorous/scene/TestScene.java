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
		mobs.add(new TestMob1());
		mobs.add(new TestMob2());
	}
	
	@Override public void input(Input input)
	{
		for (Mob mob : mobs)
			mob.input(input);
	}
	
	@Override public void update(double delta)
	{
		for (Mob mob : mobs)
			mob.update(delta);
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
