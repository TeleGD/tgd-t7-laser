package game1.world;

import java.util.ArrayList;

public class MazeGenerator 
{
	private Labyrinth labyrinth;
	private Cell[][] unvisitedCell;
	private Cell currentCell;
	private Cell choosenCell;
	
	public MazeGenerator (Labyrinth labyrinth)
	{
		this.labyrinth = labyrinth;
		this.unvisitedCell = new Cell[labyrinth.getLines()][labyrinth.getRows()];
		for (int i = 0 ; i < labyrinth.getLines();i++)
		{
			for (int j = 0 ; j < labyrinth.getRows() ; j++)
			{
				this.unvisitedCell[i][j] = labyrinth.getCell(i, j);
			}
		}
	}
	
	public Cell getRandomCell()
	{
		int i = (int)Math.random()*this.labyrinth.getLines();
		int j = (int)Math.random()*this.labyrinth.getRows();
		return labyrinth.getCell(i, j);
	}
	
	public Cell getStart()
	{
		
		return labyrinth.getCell(0,0);
	}
	
	/*
	 * 0=>East
	 * 1=>North
	 * 2=>West
	 * 3=>South
	 * Warning can be Null
	 */
	public Cell[] getNeighbor(int i , int j)
	{
		Cell [] neighbor = new Cell[4];
		if (i-1 >= 0)
			neighbor [0] = labyrinth.getCell(i-1, j);
		else 
			neighbor [0] = null;
		if (j-1 >= 0)
			neighbor [1] = labyrinth.getCell(i,j-1);
		else
			neighbor [1] = null;
		if (i+1 < labyrinth.getLines())
			neighbor [2] = labyrinth.getCell(i+1, j);
		else
			neighbor [2] = null;
		if (j+1 < labyrinth.getRows())
			neighbor [3] = labyrinth.getCell(i, j+1);
		else
			neighbor [3] = null;
		return neighbor;
	}
	
	public boolean hasAnUnvisitedNeighbor(int i , int j)
	{
		if (i-1 >= 0)
			return (this.unvisitedCell[i-1][j]!=null);
		else if (j-1 >= 0)
			return (this.unvisitedCell[i][j-1]!=null);
		else if (i+1 < labyrinth.getLines())
			return (this.unvisitedCell[i+1][j]!=null);
		else if (j+1 < labyrinth.getRows())
			return (this.unvisitedCell[i][j+1]!=null);
		else
			return false;
	}
	
	public void getRandomUnvisitedCell (int i, int j)
	{
		Cell [] neighbor = this.getNeighbor(i, j);
		int random = (int)Math.random()*3;
		while (neighbor[random] == null)
			random = (int)Math.random()*4;
		this.choosenCell = neighbor[random];
		if (random == 0)
		{
			this.currentCell.setEstWall(false);
			this.choosenCell.setWestWall(false);
		}
		else if (random == 1)
		{
			this.currentCell.setNorthWall(false);
			this.choosenCell.setSouthWall(false);
		}
		else if (random == 2)
		{
			this.currentCell.setWestWall(false);
			this.choosenCell.setEstWall(false);
		}
		else if (random == 3)
		{
			this.currentCell.setSouthWall(false);
			this.choosenCell.setNorthWall(false);
		}
	}
	
	
}
