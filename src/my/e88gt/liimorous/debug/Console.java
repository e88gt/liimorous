package my.e88gt.liimorous.debug;

import java.io.*;

public final class Console
{
	private final PrintStream printStream;
	
	public Console(PrintStream printStream)
	{
		this.printStream = printStream;
	}
	
	public void debug(String message)
	{
		System.out.println("DEBUG: "+message);
	}
	
	public PrintStream getPrintStream()
	{
		return printStream;
	}
}
