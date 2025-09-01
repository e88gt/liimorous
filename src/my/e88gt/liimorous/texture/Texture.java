package my.e88gt.liimorous.texture;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.*;

public class Texture
{
	public static final String FOLDER_PATH = "res/my/e88gt/liimorous/textures/";
	public static final String TEST_PATH = FOLDER_PATH + "tests/";
	
	private boolean aa;
	private final int texture;
	
	public Texture(Image image)
	{
		texture = glGenTextures();
		if (texture == NULL)
			throw new IllegalStateException("Failed to create texture");
		
		int format = GL_NONE;
		
		switch (image.getExtension())
		{
		case "bmp":
		case "jpg":
		case "jpeg":
			format = GL_RGB;
			break;
		
		case "png":
			format = GL_RGBA;
			break;
		
		default:
			throw new IllegalArgumentException("Unsupported image extension '" + image.getExtension() + "'");
		}
		
		bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexImage2D(GL_TEXTURE_2D, 0, format, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, image.getPixels());
		glGenerateMipmap(GL_TEXTURE_2D);
		
		image.dispose();
	}
	
	public Texture(String path) throws IOException
	{
		this(new Image(path));
	}
	
	public void bind()
	{
		glBindTexture(GL_TEXTURE_2D, texture);
	}
	
	public void useAA(boolean use)
	{
		aa = use;
		final int filter = (aa) ? GL_LINEAR : GL_NEAREST;
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);
	}
	
	public void delete()
	{
		glDeleteTextures(texture);
	}
}
