package dm.joxel.RenderEngine;

import org.lwjgl.opengl.GL11;

import dm.joxel.Models.RawModel;
import dm.joxel.RenderEngine.EntityRenderer;

public class MasterRenderer {
	public void prepare() {
		GL11.glClearColor(0.4f, 0.7f, 1.0f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}

	public void render(RawModel model) {
		EntityRenderer.render(model);
	}
}
