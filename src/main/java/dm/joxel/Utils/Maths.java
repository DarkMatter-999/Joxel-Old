package dm.joxel.Utils;

import org.joml.*;

public class Maths {
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rotX, float rotY, float rotZ, float scale) {
		Matrix4f matrix = new Matrix4f();
	    matrix.identity();
	    matrix.translate(translation);
	    matrix.rotateX((float) rotX);
	    matrix.rotateY((float) rotY);
	    matrix.rotateZ((float) rotZ);
	    matrix.scale(scale);
	    return matrix;
	}
}
