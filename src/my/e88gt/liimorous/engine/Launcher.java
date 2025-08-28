package my.e88gt.liimorous.engine;

public enum Launcher
{
	LAUNCH;
	
	private final Engine engine;
	
	private Launcher()
	{
		engine = new Engine();
		engine.run();
	}
	
	public static void main(String... args)
	{
		Launcher _ = Launcher.LAUNCH;
	}
}
