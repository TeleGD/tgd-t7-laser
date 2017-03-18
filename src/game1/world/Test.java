package game1.world;
import org.newdawn.slick.SlickException;

public class Test {

	public static void main(String[] args) 
	{
		Labyrinth labyrinth = new Labyrinth(16, 16);
		MazeGenerator mazeGenerator = new MazeGenerator(labyrinth);
		try {
			mazeGenerator.mazeGenrator();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("fin");
	}

}
