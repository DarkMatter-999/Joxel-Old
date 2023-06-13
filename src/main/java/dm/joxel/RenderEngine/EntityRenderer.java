package dm.joxel.RenderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import dm.joxel.Entities.Entity;
import dm.joxel.Models.TexturedModel;
import dm.joxel.Shader.StaticShader;
import dm.joxel.Utils.Maths;

import org.joml.*;

public class EntityRenderer {
	public static void render(Entity entity, StaticShader shader) {
		GL30.glBindVertexArray(entity.model.getModel().getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);

		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.position, entity.rotX, entity.rotY, entity.rotZ, entity.scale);
		shader.loadTransformationMatrix(transformationMatrix);

		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL13.glBindTexture(GL30.GL_TEXTURE_2D, entity.model.getTexture().getTextureId());
		GL11.glDrawElements(GL11.GL_TRIANGLES, entity.model.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
}
