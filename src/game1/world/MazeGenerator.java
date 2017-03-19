package game1.world;

import java.util.Stack;

import org.newdawn.slick.SlickException;

public class MazeGenerator 
{
	private Labyrinth labyrinth;
	private Cell[][] unvisitedCell;
	private Cell currentCell;
	private Cell choosenCell;
	private Stack<Cell> stack;
	
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
	
	public int getChosenCell(int i,int j)
	{
		int random = (int)Math.floor(Math.random()*4);
		System.out.println(random);
		if (random == 0 && i-1 >=0 && this.unvisitedCell[i-1][j]!=null)
			this.choosenCell = this.labyrinth.getCell(i-1, j);
		else if (random ==1 && i+1<this.labyrinth.getLines() && this.unvisitedCell[i+1][j]!=null)
			this.choosenCell = this.labyrinth.getCell(i+1, j);
		else if (random ==2 && j-1>=0 && this.unvisitedCell[i][j-1]!=null)
			this.choosenCell = this.labyrinth.getCell(i, j-1);
		else if (random == 3 && j+1 < this.labyrinth.getRows() &&  this.unvisitedCell[i][j+1]!=null)
			this.choosenCell = this.labyrinth.getCell(i, j+1);
		else return getChosenCell(i,j);
		return random;
	}
	
	
	public boolean hasAnUnvisitedNeighbor(int i , int j)
	{
		boolean result = false;
		if (i-1 >= 0 && this.unvisitedCell[i-1][j]!=null)
			result =  result||(this.unvisitedCell[i-1][j]!=null);
		if (j-1 >= 0 && this.unvisitedCell[i][j-1]!=null)
			result =  result||(this.unvisitedCell[i][j-1]!=null);
		if (i+1 < labyrinth.getLines() && this.unvisitedCell[i+1][j]!=null)
			result =  result||(this.unvisitedCell[i+1][j]!=null);
		if (j+1 < labyrinth.getRows() && this.unvisitedCell[i][j+1]!=null)
			result =  result||(this.unvisitedCell[i][j+1]!=null);
		return result;
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
	
	
	public void mazeGenrator() throws SlickException
	{
		//Make the initial cell the current cell and mark it as visited 
		stack = new Stack<Cell>();
		currentCell = labyrinth.getCell(0, 0);
		unvisitedCell[0][0] = null;
		boolean stillUnvisitedCell = this.stillUnvisitedCell();
		
		//While there are unvisited cells
		while(stillUnvisitedCell)
		{
			unvisitedCell[this.currentCell.getI()][this.currentCell.getJ()] = null;
			
			//If the current cell has any neighbours which have not been visited
			if (this.hasAnUnvisitedNeighbor(this.currentCell.getI(),this.currentCell.getJ()))
			{
				//Choose randomly one of the unvisited neighbours
				int random = this.getChosenCell(this.currentCell.getI(),this.currentCell.getJ());
				System.out.println("has not visited nei... i next : "+this.choosenCell.getI()+ " j next : "+this.choosenCell.getJ());
				
				//Push the current cell to the stack
				stack.push(this.currentCell);
				
				//Remove the wall between the current cell and the chosen cell
				if (random == 0)
				{
					this.currentCell.setNorthWall(false);
					this.choosenCell.setSouthWall(false);
				}
				else if (random == 1)
				{
					this.currentCell.setSouthWall(false);
					this.choosenCell.setNorthWall(false);
				}
				else if (random == 2)
				{
					this.currentCell.setWestWall(false);
					this.choosenCell.setEastWall(false);
				}
				else if (random == 3)
				{
					this.currentCell.setEastWall(false);
					this.choosenCell.setWestWall(false);
				}
				
				//Make the chosen cell the current cell and mark it as visited
				this.currentCell = this.choosenCell;
				System.out.println("i : "+this.currentCell.getI()+ " j : "+this.currentCell.getJ());
			}
			
			//Else if stack is not empty
			else if (!stack.isEmpty())
			{
				System.out.println("! stack empty i : "+this.currentCell.getI()+ " j : "+this.currentCell.getJ());
				//Pop a cell from the stack and make it the current cell
				this.currentCell = stack.pop();
				System.out.println(stack.isEmpty());
			}
			//else break;
			stillUnvisitedCell = this.stillUnvisitedCell();
			
		}
		System.out.println("fin");
		labyrinth.autoset();
	}
	
}
