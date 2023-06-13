package dm.joxel.Entities;

import org.joml.*;
import dm.joxel.Models.TexturedModel;

public class Entity {
	public TexturedModel model;
	public Vector3f position;
	public float rotX, rotY, rotZ;
	public float scale;

	public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}
}
