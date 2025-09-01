package my.e88gt.tests;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.nuklear.Nuklear.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL43.*;
import static org.lwjgl.opengl.GL45.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

import org.joml.*;
import org.lwjgl.glfw.*;
import org.lwjgl.nuklear.*;
import org.lwjgl.opengl.*;
import org.lwjgl.stb.*;
import org.lwjgl.system.*;

class _Program
{
	static int vao, vbo, ebo, tex;
	static int prog, vert, frag;
	static int uniform_tex, uniform_proj;
	static long win;
	
	static ByteBuffer ttf;
	
	static final NkAllocator allocator = NkAllocator.calloc();
	static final NkDrawVertexLayoutElement.Buffer vertex_layout = NkDrawVertexLayoutElement.calloc(4);
	
	static final NkContext ctx = NkContext.calloc();
	static final NkUserFont default_font = NkUserFont.calloc();
	static final NkBuffer cmds = NkBuffer.calloc();
	static final NkDrawNullTexture null_texture = NkDrawNullTexture.calloc();
	
	static final Vector2i size = new Vector2i(1280, 720), fbs = new Vector2i(size);
	static final Vector3f bgc = new Vector3f();
	
	static void run()
	{
		init();
		
		while (!glfwWindowShouldClose(win))
		{
			glfwPollEvents();
			glViewport(0, 0, fbs.x, fbs.y);
			glClearColor(bgc.x, bgc.y, bgc.z, 1);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glUseProgram(prog);
			
			new_frame();
			
			glfwSwapBuffers(win);
		}
		
		destroy();
	}
	
	static void init()
	{
		init_window();
		init_shader();
		init_vao();
		init_gui();
	}
	
	static void init_window()
	{
		glfwSetErrorCallback((c, d) ->
		{
			throw new RuntimeException(GLFWErrorCallback.getDescription(d) + "\nError code: " + c);
		});
		glfwInit();
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6);
		glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
		win = glfwCreateWindow(size.x, size.y, "Nuklear test", NULL, NULL);
		glfwMakeContextCurrent(win);
		GL.createCapabilities();
		glDebugMessageCallback((_, _, _, _, l, m, _) ->
		{
			throw new RuntimeException(GLDebugMessageCallback.getMessage(l, m));
		}, 0);
		glDebugMessageControl(GL_DONT_CARE, GL_DONT_CARE, GL_DONT_CARE, (IntBuffer) null, true);
	}
	
	static void init_gui()
	{
		allocator.alloc((_, _, size) -> nmemAllocChecked(size));
		allocator.mfree((_, pointer) -> nmemFree(pointer));
		
		vertex_layout.position(0).attribute(NK_VERTEX_POSITION).format(NK_FORMAT_FLOAT).offset(0);
		vertex_layout.position(1).attribute(NK_VERTEX_TEXCOORD).format(NK_FORMAT_FLOAT).offset(8);
		vertex_layout.position(2).attribute(NK_VERTEX_COLOR).format(NK_FORMAT_R8G8B8A8).offset(16);
		vertex_layout.position(3).attribute(NK_VERTEX_ATTRIBUTE_COUNT).format(NK_FORMAT_COUNT).offset(0);
		vertex_layout.flip();
		
		init_font();
	}
	
	static void init_font()
	{
		final int tex = glCreateTextures(GL_TEXTURE_2D);
		
		STBTTFontinfo font_info = STBTTFontinfo.calloc();
		STBTTPackedchar.Buffer cdata = STBTTPackedchar.calloc(95);
		
		ttf = io_res_to_bb("res/my/e88gt/liimorous/fonts/tests/UbuntuMonoRegular.ttf");
		
		font_info.free();
		cdata.free();
	}
	
	static ByteBuffer io_res_to_bb(String path)
	{
		try (SeekableByteChannel fc = Files.newByteChannel(Paths.get(path)))
		{
			ByteBuffer buffer = memCalloc((int) fc.size() + 1);
			
			while (fc.read(buffer) != -1);
			
			try {
				return buffer;
			}finally { /// TODO finish gui
				memFree(buffer);
			}
		}
		catch (IOException e)
		{
			throw new UncheckedIOException(e);
		}
	}
	
	static void init_vao()
	{
		vao = glCreateVertexArrays();
		vbo = glCreateBuffers();
		ebo = glCreateBuffers();
		tex = glCreateTextures(GL_TEXTURE_2D);
		
		glVertexArrayVertexBuffer(vao, 0, vbo, 0, 20);
		glVertexArrayElementBuffer(vao, ebo);
		
		final int ap = glGetAttribLocation(prog, "Position");
		final int auv = glGetAttribLocation(prog, "UV");
		final int ac = glGetAttribLocation(prog, "Color");
		uniform_tex = glGetUniformLocation(prog, "Texture");
		uniform_proj = glGetUniformLocation(prog, "ProjMat");
		
		glEnableVertexArrayAttrib(vao, ap);
		glEnableVertexArrayAttrib(vao, auv);
		glEnableVertexArrayAttrib(vao, ac);
		
		glVertexArrayAttribFormat(vao, ap, 2, GL_FLOAT, false, 0);
		glVertexArrayAttribFormat(vao, auv, 2, GL_FLOAT, false, 8);
		glVertexArrayAttribFormat(vao, ac, 4, GL_UNSIGNED_BYTE, false, 16);
		
		glTextureParameteri(tex, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTextureParameteri(tex, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTextureStorage2D(tex, 1, GL_RGBA8, 1, 1);
		
		null_texture.texture().id(tex);
		null_texture.uv().set(0.5F, 0.5F);
		
		try (MemoryStack stack = MemoryStack.stackPush())
		{
			glTextureSubImage2D(tex, 0, 0, 0, 1, 1, GL_RGBA, GL_UNSIGNED_INT_8_8_8_8_REV, stack.ints(0xFFFFFFFF));
		}
	}
	
	static void init_shader()
	{
		prog = glCreateProgram();
		vert = glCreateShader(GL_VERTEX_SHADER);
		frag = glCreateShader(GL_FRAGMENT_SHADER);
		
		String vs = """
			#version 460 core
			
			layout (location = 0) in vec2 Position;
			layout (location = 1) in vec2 UV;
			layout (location = 2) in vec4 Color;
			
			out vec2 FragUV;
			out vec4 FragColor;
			
			layout (location = 3) uniform mat4 ProjMat;
			
			void main()
			{
				FragUV = UV;FragColor = Color;
				gl_Position = ProjMat * vec4(Position.xy, 0, 1);
			}
			
			""", fs = """
			#version 460 core
			
			precision mediump float;
			
			in vec2 FragUV;
			in vec4 FragColor;
			
			out vec4 OutColor;
			
			layout (location = 4) uniform sampler2D Texture;
			
			void main()
			{
				OutColor = FragColor * texture(Texture, FragUV.st);
			}
			
			""";
		
		glShaderSource(vert, vs);
		glShaderSource(frag, fs);
		glCompileShader(vert);
		glCompileShader(frag);
		
		System.err.println(glGetShaderInfoLog(vert));
		System.err.println(glGetShaderInfoLog(frag));
		
		glAttachShader(prog, vert);
		glAttachShader(prog, frag);
		
		glLinkProgram(prog);
		System.err.println(glGetProgramInfoLog(prog));
	}
	
	static void new_frame()
	{
		try (MemoryStack stack = MemoryStack.stackPush())
		{
			IntBuffer w = stack.callocInt(2);
			nglfwGetWindowSize(win, memAddress(w), memAddress(w) + 4);
			size.set(w.get(0), w.get(1));
			nglfwGetFramebufferSize(win, memAddress(w), memAddress(w) + 4);
			fbs.set(w.get(0), w.get(1));
		}
	}
	
	static void destroy()
	{
		destroy_gui();
		destroy_shader();
		destroy_vao();
		destroy_window();
	}
	
	static void destroy_gui()
	{
		ctx.free();
		cmds.free();
		null_texture.free();
		default_font.free();
		
		allocator.free();
		vertex_layout.free();
	}
	
	static void destroy_shader()
	{
		glDeleteShader(frag);
		glDeleteShader(vert);
		glDeleteProgram(prog);
	}
	
	static void destroy_vao()
	{
		glDeleteTextures(tex);
		glDeleteBuffers(vbo);
		glDeleteBuffers(ebo);
		glDeleteVertexArrays(vao);
	}
	
	static void destroy_window()
	{
		GL.destroy();
		glfwFreeCallbacks(win);
		glfwDestroyWindow(win);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public static void main(String... args)
	{
		run();
	}
}
