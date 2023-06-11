
import dm.joxel.RenderEngine.DisplayManager;

public class Joxel {
	public static void main(String[] args) {
        System.out.print("hello");
		
		DisplayManager dm = new DisplayManager();

		dm.createDisplay();

		dm.updateDisplay();
		
		dm.closeDisplay();
	}

}
