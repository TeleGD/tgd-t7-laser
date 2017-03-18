package game2.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Grid2 {
	private int rows;
	private int colums;
	public int grid[][];
	

	public Grid2(int r, int c){
		grid = new int[r][c];
	}

	
	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
	}
	
	public int getRow(){
		return grid.length;
	}
	
	public int getColumns(){
		return grid[0].length;
	}
	
	

}
