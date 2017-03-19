package game1.world;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game1.world.Cell;

public class Labyrinth {
	
	private int lines,rows;
	private Cell[][] cells;
	
	
	public Labyrinth (int lines,int rows) 
	{
		this.lines = lines;
		this.rows = rows;
		cells = new Cell[lines][rows];
		Cell cell;
		for(int i=0 ; i<lines;i++ )
		{
			for (int j=0;j<rows;j++)
			{
				cell = new Cell(i,j);
				cells[i][j]=cell;
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
		return cells[i][j];
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawString("str", 500, 500);
		for (Cell[] cells2 : cells)
		{
			for(Cell cell : cells2)
			{
				cell.render(arg0, arg1, arg2);
			}
		}
	}
	
	public void autoset() throws SlickException
	{
		for (int i = 0 ; i<this.getLines() ; i++)
		{
			for (int j = 0 ; j<this.getRows() ; j++)
				{
					this.getCell(i, j).autoSetSprite();
				}
		}
	}

}
