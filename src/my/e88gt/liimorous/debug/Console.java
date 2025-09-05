package my.e88gt.liimorous.debug;

public final class Console
{
	public void debug(String message)
	{
		System.out.println(message);
	}
	
	public void error(String problem)
	{
		System.err.println(problem);
	}
}
