package my.e88gt.liimorous.texture;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.*;
import java.nio.*;

public class Image
{
	private final int width, height;
	private final String extension;
	private final ByteBuffer pixels;
	
	public Image(String imagePath) throws IOException
	{
		IntBuffer width = memCallocInt(1);
		IntBuffer height = memCallocInt(1);
		IntBuffer channel = memCallocInt(1);
		
		stbi_set_flip_vertically_on_load(true);
		
		pixels = stbi_load(imagePath, width, height, channel, STBI_rgb_alpha);
		if (pixels == null)
			throw new IOException(stbi_failure_reason() + "\nPath: '" + imagePath + "'");
		
		extension = imagePath.substring(imagePath.lastIndexOf('.') + 1);
		
		this.width = width.get(0);
		this.height = height.get(0);
		
		memFree(width);
		memFree(height);
		memFree(channel);
	}
	
	public void dispose()
	{
		stbi_image_free(pixels);
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public String getExtension()
	{
		return extension;
	}
	
	public ByteBuffer getPixels()
	{
		return pixels;
	}
}
