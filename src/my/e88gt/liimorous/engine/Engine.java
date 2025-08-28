package my.e88gt.liimorous.engine;

public class Engine
{
	public Engine()
	{
	}
	
	public void run()
	{
		System.out.println("started");
		
		boolean running = true;
		double lastTime=System.nanoTime();
		
		while(running)
		{
			double currentTime=System.nanoTime();
			double deltaTime=(currentTime-lastTime)/1_000_000_000;
			lastTime=currentTime;
			
			System.out.println(deltaTime + " s");
		}
		
		System.out.println("quit");
	}
}
