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

import dm.joxel.RenderEngine.DisplayManager;
import dm.joxel.RenderEngine.MasterRenderer;

public class Joxel {
	public static void main(String[] args) {
		DisplayManager dm = new DisplayManager();

		MasterRenderer renderer = new MasterRenderer();

		dm.createDisplay();

		final long window = dm.window;

		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();
		
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(window) ) {
			renderer.prepare();

			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			glfwSwapBuffers(window); // swap the color buffers

			dm.updateDisplay();
			
			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		}

		
		dm.closeDisplay();
	}

}
