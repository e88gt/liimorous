package my.e88gt.liimorous.engine;

/**
 * launcher enum, singleton enum that
 * launches whatever the application is
 */
public enum Launcher
{
	/**
	 * this is the only instance of launcher
	 * dont access it unless you really need to 
	 */
	LAUNCHER;
	
	/**
	 * the engine the launcher is going to be launching
	 */
	private final Engine engine;
	
	/**
	 * private enum constructor
	 */
	private Launcher()
	{
		engine = new Engine();
	}
	
	/**
	 * launches the engine
	 */
	private void launch()
	{
		engine.run();
	}
	
	/**
	 * gets the engine the launcher launched
	 * 
	 * @return the engine
	 */
	public Engine getEngine()
	{
		return engine;
	}
	
	/**
	 * typical java main method.
	 * the only method that can be static
	 * 
	 * @param arguments
	 * the command line arguments,
	 * which is not needed
	 */
	public static void main(String... arguments)
	{
		Launcher launcher = Launcher.LAUNCHER;
		launcher.launch();
	}
}
