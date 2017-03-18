package game1.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Cell{
	
	private boolean northWall,southWall,westWall,estWall;
	private int i,j;
	private boolean partOfTheMaze;
	
	public Cell (int i, int j)
	{
		this.i = i;
		this.j = j;
		this.setNorthWall(true);
		this.setSouthWall(true);
		this.setEstWall(true);
		this.setWestWall(true);
	}

	public boolean isWestWall() 
	{
		return westWall;
	}

	public void setWestWall(boolean westWall) 
	{
		this.westWall = westWall;
	}

	public boolean isNorthWall() 
	{
		return northWall;
	}

	public void setNorthWall(boolean northWall) 
	{
		this.northWall = northWall;
	}

	public boolean isSouthWall() 
	{
		return southWall;
	}

	public void setSouthWall(boolean southWall) 
	{
		this.southWall = southWall;
	}

	public boolean isEstWall() 
	{
		return estWall;
	}

	public void setEstWall(boolean estWall) 
	{
		this.estWall = estWall;
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException 
	{
		arg2.fillRect(i*64,j*64,63,63);
	}

	public boolean isPartOfTheMaze() {
		return partOfTheMaze;
	}

	public void setPartOfTheMaze(boolean partOfTheMaze) {
		this.partOfTheMaze = partOfTheMaze;
	}

}
