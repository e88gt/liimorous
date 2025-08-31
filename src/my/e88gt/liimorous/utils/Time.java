package my.e88gt.liimorous.utils;

public final class Time
{
	public static final int NS_PER_SEC = 1_000_000_000;
	
	public double getSystemNano()
	{
		return System.nanoTime();
	}
}
