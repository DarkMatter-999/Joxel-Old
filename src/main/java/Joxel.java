import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import dm.joxel.Models.RawModel;
import dm.joxel.Models.TexturedModel;
import dm.joxel.Textures.ModelTexture;
import dm.joxel.RenderEngine.DisplayManager;
import dm.joxel.RenderEngine.Loader;
import dm.joxel.RenderEngine.MasterRenderer;
import dm.joxel.Shader.StaticShader;

public class Joxel {
	public static Loader loader = null;

	public static void main(String[] args) {
		DisplayManager dm = new DisplayManager();

		dm.createDisplay(1280, 720);
		final long window = dm.window;

		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();
		
		MasterRenderer renderer = new MasterRenderer();
		Loader loader = new Loader();

		StaticShader shader = new StaticShader();

		//loader = loader1;

		float[] vertices = {
			-0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
			0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f
		};

		int[] indices = {
			0, 1, 2,
			2, 3, 0
		};

		float[] uv = {
			0, 0,
			0, 1,
		    1, 1,
			1, 0
		};

		RawModel model = loader.loadToVao(vertices, indices, uv);
		ModelTexture texture = new ModelTexture(loader.loadTexture("Resources/dirt.png"));

		TexturedModel texmodel = new TexturedModel(model, texture);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(window) ) {
			renderer.prepare();

			// glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
								
			shader.start();
			renderer.render(texmodel);
			shader.stop();

			glfwSwapBuffers(window); // swap the color buffers

			dm.updateDisplay();
			
			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		}

		
		loader.cleanUp();
		shader.cleanUp();
		dm.closeDisplay();
	}

}
