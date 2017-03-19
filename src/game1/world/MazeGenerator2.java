package game1.world;

import java.util.Stack;

public class MazeGenerator2 
{
	
	private Labyrinth labyrinth;
	private Cell[][] unvisitedCell;
	private 
	
	public void MazeGenerator2(Labyrinth labyrinth)
	{
		this.labyrinth = labyrinth;
		for (int i = 0 ; i < labyrinth.getLines();i++)
		{
			for (int j = 0 ; j < labyrinth.getRows() ; j++)
			{
				this.unvisitedCell[i][j] = labyrinth.getCell(i, j);
			}
		}
	}
	
	public boolean stillUnvisitedCell()
	{
		for (int i=0 ; i<labyrinth.getLines();i++ )
		{
			for (int j = 0 ; j<labyrinth.getRows();j++)
			{
				if (this.unvisitedCell[i][j] !=null)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void mazeGenerate()
	{
		//Make the initial cell the current cell and mark it as visited 
			stack = new Stack<Cell>();
			currentCell = labyrinth.getCell(0, 0);
			unvisitedCell[0][0] = null;
			boolean stillUnvisitedCell = this.stillUnvisitedCell();
	}

}
