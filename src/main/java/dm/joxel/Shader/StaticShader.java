package dm.joxel.Shader;

import org.joml.*;

public class StaticShader extends ShaderProgram {

	private static final String vertexFile = "Shaders/Vertex.glsl"; 
	private static final String fragmentFile = "Shaders/Fragment.glsl";

	int loc_transformationMatrix;

	public StaticShader() {
		super(vertexFile, fragmentFile);	
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute("position", 0);
		super.bindAttribute("textureCoords", 1);
	}

	@Override
	protected void getAllUniformLocations() {
		loc_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}

	public void loadTransformationMatrix(Matrix4f mat) {
		super.loadMatrix(loc_transformationMatrix, mat);
	}

}
