package game1.world;

public class MazeGenerator 
{
	private Labyrinth labyrinth;
	
	public void getRandomCell()
	{
		int i = (int)Math.random()*this.labyrinth.getLines();
		int j = (int)Math.random()*this.labyrinth.getRows();
	}
	
}
