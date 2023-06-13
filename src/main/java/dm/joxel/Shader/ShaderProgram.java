package dm.joxel.Shader;

import java.io.*;
import java.util.Scanner;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL11;
import org.joml.*;
import org.lwjgl.BufferUtils;
import java.nio.*;

public abstract class ShaderProgram {
	int programId;
	int vertexShaderId;
	int fragmentShaderId;

	FloatBuffer matrixbuffer = BufferUtils.createFloatBuffer(16);

	public ShaderProgram(String vertexFile, String fragmentFile) {
		programId = GL20.glCreateProgram();
		vertexShaderId = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderId = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);

			GL20.glAttachShader(programId, vertexShaderId);
			GL20.glAttachShader(programId, fragmentShaderId);

			bindAttributes();

			GL20.glLinkProgram(programId);
			GL20.glValidateProgram(programId);
	}

	protected abstract void bindAttributes();

	protected void bindAttribute(String varname, int attribute) {
		GL20.glBindAttribLocation(programId, attribute, varname);
	}

	public void start() {
		GL20.glUseProgram(programId);
	}
	public void stop() {
		GL20.glUseProgram(0);
	}
	public void cleanUp() {
		stop();
		GL20.glDetachShader(programId, vertexShaderId);
		GL20.glDetachShader(programId, fragmentShaderId);

		GL20.glDeleteShader(vertexShaderId);
		GL20.glDeleteShader(fragmentShaderId);
		GL20.glDeleteProgram(programId);

		getAllUniformLocations();
	}

	protected abstract void getAllUniformLocations();

	protected int getUniformLocation(String varname) {
		return GL20.glGetUniformLocation(programId, varname);
	}
	
	protected void loadFloat(int location, float value) {
		GL20.glUniform1f(location, value);
	}
	
	protected void load2DVector(int location, Vector2f vec) {
		GL20.glUniform2f(location, vec.x, vec.y);
	}

	protected void load3DVector(int location, Vector3f vec) {
		GL20.glUniform3f(location, vec.x, vec.y, vec.z);
	}
	
	protected void loadMatrix(int location, Matrix4f mat) {
		mat.get(matrixbuffer);
		GL20.glUniformMatrix4fv(location, false, matrixbuffer);
	}

	protected void loadBoolean(int location, boolean val) {
		float data = 0;

		if (val) {
			data = 1;
		}
		GL20.glUniform1f(location, data);
	}

	private int loadShader(String file, int type) {
		StringBuilder shaderSource = new StringBuilder();

		try {
			InputStream in = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line;

			while((line = reader.readLine()) != null) {
				shaderSource.append(line).append("//\n");
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load shader");
			System.exit(-1);

		}
		
		int ShaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(ShaderID, shaderSource);
		GL20.glCompileShader(ShaderID);
		
		if(GL20.glGetShaderi(ShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
			System.out.println(GL20.glGetShaderInfoLog(ShaderID, 1000));
			System.err.println("Could not compile shader");
			System.exit(-1);
		}
		return ShaderID;
	}
}
