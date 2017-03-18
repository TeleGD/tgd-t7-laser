package game1.world;

public class Test {

	public static void main(String[] args) 
	{
		Labyrinth labyrinth = new Labyrinth(16, 16);
		System.out.println(labyrinth.getCell(0, 0));
		MazeGenerator mazeGenerator = new MazeGenerator(labyrinth);
		System.out.println(labyrinth.getRows());
		System.out.println(0+1 < labyrinth.getRows());
		System.out.println(mazeGenerator.getNeighbor(0, 0)[3]);
		System.out.println(mazeGenerator.hasAnUnvisitedNeighbor(0,0));
		mazeGenerator.mazeGenrator();
	}

}
