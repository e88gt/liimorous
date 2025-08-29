package my.e88gt.liimorous.utils;

import java.io.*;
import java.nio.file.*;

public final class TextFile
{
	private final String content;
	
	public TextFile(String filePath) throws IOException
	{
		if(filePath == null)
			throw new NullPointerException("Path can't be null");
		
		if(filePath.isBlank())
			throw new IllegalArgumentException("Path can't be blank");
		
		content = new String(Files.readAllBytes(Paths.get(filePath)));
	}
	
	public String getContent()
	{
		return content;
	}
}
