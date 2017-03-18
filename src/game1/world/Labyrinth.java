package game1.world;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game1.world.Cell;

public class Labyrinth {
	
	private int lines,rows;
	private ArrayList<Cell> cells;
	
	
	public Labyrinth (int lines,int rows) 
	{
		cells = new ArrayList<Cell>();
		Cell cell;
		for(int i=0 ; i<lines;i++ )
		{
			for (int j=0;j<lines;j++)
			{
				cell = new Cell(i,j);
				cells.add(cell);
			}
		}
		
	}
	
	public int getLines()
	{
		return this.lines;
	}
	
	public int getRows()
	{
		return this.rows;
	}
	
	public Cell getCell(int i,int j)
	{
		return cells.get(i*this.rows + j);
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawString("str", 500, 500);
		for (Cell c : cells)
		{
			c.render(arg0, arg1, arg2);
		}
	}
	
	public void mazeGenrator()
	{
		
	}

}
