package my.e88gt.liimorous.texture;

import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.*;
import java.nio.*;

import org.lwjgl.system.*;

public class Texture
{
	public static final String FOLDER_PATH = "res/my/e88gt/liimorous/textures/";
	
	private final int texture;
	
	public Texture(String path) throws IOException
	{
		IntBuffer width = MemoryUtil.memCallocInt(1);
		IntBuffer height = MemoryUtil.memCallocInt(1);
		IntBuffer channel = MemoryUtil.memCallocInt(1);
		
		ByteBuffer image = stbi_load(path, width, height, channel, STBI_rgb_alpha);
		if (image == null)
			throw new IOException(stbi_failure_reason() + "\nPath specified: '" + path + "'");
		
		texture = glGenTextures();
		
		if (texture == NULL)
			throw new IllegalStateException("Failed to create texture");
		
		String extension = path.substring(path.lastIndexOf('.') + 1);
		int format = GL_NONE;
		
		switch (extension)
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
			throw new IllegalArgumentException("Unsupported image extension '" + extension + "'");
		}
		
		bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexImage2D(GL_TEXTURE_2D, 0, format, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
		glGenerateMipmap(GL_TEXTURE_2D);
		
		MemoryUtil.memFree(width);
		MemoryUtil.memFree(height);
		MemoryUtil.memFree(channel);
		stbi_image_free(image);
	}
	
	public void bind()
	{
		glBindTexture(GL_TEXTURE_2D, texture);
	}
	
	public void useAA(boolean use)
	{
		final int aa = (use) ? GL_LINEAR : GL_NEAREST;
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, aa);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, aa);
	}
	
	public void delete()
	{
		glDeleteTextures(texture);
	}
}
