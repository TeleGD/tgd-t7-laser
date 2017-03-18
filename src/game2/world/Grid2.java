package game2.world;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Grid2 {
	private int rows;
	private int columns;
	public Cell grid[][];
	
	public List<Laser> laserList;
	private int laserTimer;
	

	public Grid2(int r, int c) throws SlickException{
		rows = r;
		columns = c;
		grid = new Cell[r][c];
		for(int i = 0; i<r; i++)//init row
			for(int j=0;j<c;j++) //init cologne
				grid[i][j] = new Cell(i,j,false,false);
		
		laserList = new LinkedList<Laser>();
		laserTimer = 1;
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
		for(int i = 0; i<this.rows; i++)//init row
			for(int j=0;j<this.columns;j++) //init cologne
				grid[i][j].getImage().draw(0+i*100,0+j*100,100,100);
		
		for(Laser l : laserList)
			l.render(arg0, arg1, arg2);
			
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		laserTimer--;
		if(laserTimer == 0){
			addLaser();
			laserTimer = 100;
		}
		try{
			for(Laser l : laserList)
				l.update(arg0, arg1, arg2);
		}
		catch(Exception e){
			//System.out.println(e.getMessage());
		}
		
		
		for(int i = 0; i<this.rows; i++)//init row
			for(int j=0;j<this.columns;j++) //init cologne
				grid[i][j].update(arg0, arg1, arg2);
	}
	
	public int getRows(){
		return grid.length;
	}
	
	public int getColumns(){
		return grid[0].length;
	}
	
	//x y new position
	public boolean MovePlayer(int x,int y, Player2 p){
		if(x < rows && y < columns && x >= 0 && y >= 0){
			//set new cell true
			grid[x][y].setContains(true);
			//set old cell false
			grid[p.getX()][p.getY()].setContains(false);
			
			return true;
		}
		return  false;
	}
	
	public void addLaser(){
		Random r = new Random();
		boolean axe = r.nextBoolean();
		if(axe) //horizontal
			laserList.add(new Laser(0,r.nextInt(columns)));
		else //vertical
			laserList.add(new Laser(1,r.nextInt(rows)));
	}
	
	public void removeLaser(Laser l){
		laserList.remove(l);
	}

}
