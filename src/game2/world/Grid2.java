package game2.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Grid2 {
	private int rows;
	private int columns;
	public Cell grid[][];
	

	public Grid2(int r, int c){
		rows = r;
		columns = c;
		grid = new Cell[r][c];
		for(int i = 0; i<r; i++)//init row
			for(int j=0;j<c;j++) //init cologne
				grid[i][j] = new Cell(i,j,false,false);
	}

	
	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}
	
	public Cell getCell(int x, int y){
		return grid[x][y];
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
	
	//x y new position
	public boolean MovePlayer(int x,int y, Player2 p){
		if(x < rows && y < columns && x >= 0 && y >= 0){
			//set new cell true
			grid[x][y].setContains(false);
			//set old cell false
			grid[p.getX()][p.getY()].setContains(false);
			
			return true;
		}
		return  false;
	}

}
