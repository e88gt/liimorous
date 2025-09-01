package my.e88gt.liimorous.texture;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.*;
import java.nio.*;

public class Image
{
	private final int width, height;
	private final ByteBuffer pixels;
	
	public Image(String imagePath) throws IOException
	{
		IntBuffer dataBuffer = memCallocInt(3);
		
		stbi_set_flip_vertically_on_load(true);
		long imageAddress = nstbi_load(memAddress(memUTF8(imagePath)), memAddress(dataBuffer), memAddress(dataBuffer) + 4, memAddress(dataBuffer) + 8, STBI_rgb_alpha);
		
		if (imageAddress == NULL)
			throw new IOException(stbi_failure_reason() + "\nPath: '" + imagePath + "'");
		
		this.width = dataBuffer.get(0);
		this.height = dataBuffer.get(1);
		
		pixels = memByteBuffer(imageAddress, width * height * STBI_rgb_alpha);
		
		memFree(dataBuffer);
	}
	
	public void free()
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
	
	public ByteBuffer getPixels()
	{
		return pixels;
	}
}
