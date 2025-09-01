package my.e88gt.liimorous.texture;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.*;

public class Texture
{
	public static final String FOLDER_PATH = "res/my/e88gt/liimorous/textures/";
	public static final String TEST_PATH = FOLDER_PATH + "tests/";
	
	private final int texture;
	
	public Texture(Image image, boolean aa)
	{
		texture = glCreateTextures(GL_TEXTURE_2D);
		if (texture == NULL)
			throw new IllegalStateException("Failed to create texture");
		
		glTextureStorage2D(texture, 1, GL_RGBA8, image.getWidth(), image.getHeight());
		glTextureSubImage2D(texture, 0, 0, 0, image.getWidth(), image.getHeight(), GL_RGBA, GL_UNSIGNED_BYTE, image.getPixels());
		glTextureParameteri(texture, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTextureParameteri(texture, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTextureParameteri(texture, GL_TEXTURE_MIN_FILTER, (aa) ? GL_LINEAR : GL_NEAREST);
		glTextureParameteri(texture, GL_TEXTURE_MAG_FILTER, (aa) ? GL_LINEAR : GL_NEAREST);
		glGenerateTextureMipmap(texture);
		
		image.dispose();
	}
	
	public Texture(String path, boolean aa) throws IOException
	{
		this(new Image(path), aa);
	}
	
	public void bind()
	{
		glBindTextureUnit(0, texture);
	}
	
	public void delete()
	{
		glDeleteTextures(texture);
	}
}
