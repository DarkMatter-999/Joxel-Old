package dm.joxel.Models;

public class RawModel {
	private int VAOid;
	private int vertexCount;

	public RawModel(int VAOid, int vertexCount) {
		this.VAOid = VAOid;
		this.vertexCount = vertexCount;
	}

	public int getVaoID() {
		return this.VAOid;
	}

	public int getVertexCount() {
		return this.vertexCount;
	}
}
