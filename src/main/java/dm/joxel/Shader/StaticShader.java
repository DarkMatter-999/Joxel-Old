package dm.joxel.Shader;

public class StaticShader extends ShaderProgram {

	private static final String vertexFile = "Shaders/Vertex.glsl"; 
	private static final String fragmentFile = "Shaders/Fragment.glsl";

	public StaticShader() {
		super(vertexFile, fragmentFile);	
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute("position", 0);
		super.bindAttribute("textureCoords", 1);
	}

}
