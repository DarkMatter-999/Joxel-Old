package dm.joxel.RenderEngine;

import java.util.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import java.nio.*;
import dm.joxel.Models.RawModel;

public class Loader {
	static List<Integer> vaos = new ArrayList<Integer>();
	static List<Integer> vbos = new ArrayList<Integer>();


	public RawModel loadToVao(float[] vertices, int[] indices) {
		int VAOid = createVAO();
		storeDataInAttributeList(vertices, 0, 3);
		bindIndicesBuffer(indices);
		GL30.glBindVertexArray(0);

		return new RawModel(VAOid, indices.length);
	}

	public int createVAO() {
		int VAOid = GL30.glGenVertexArrays();
		vaos.add(VAOid);
		GL30.glBindVertexArray(VAOid);
		return VAOid;

	}

	public void storeDataInAttributeList(float[] data, int attributeNo, int dimension) {
		int VBOid = GL15.glGenBuffers();
		vbos.add(VBOid);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOid);

		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNo, dimension, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	private void bindIndicesBuffer(int[] indices) {
		int VBOid = GL15.glGenBuffers();
		vbos.add(VBOid);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, VBOid);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

	}

	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();

		return buffer;
	}
	
	private IntBuffer storeDataInIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();

		return buffer;
	}

	public void cleanUp() {
		for(int vao: vaos) {
			GL30.glDeleteVertexArrays(vao);
		}

		for(int vbo: vbos) {
			GL30.glDeleteVertexArrays(vbo);
		}
	}
}
