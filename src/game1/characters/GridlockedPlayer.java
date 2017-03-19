package game1.characters;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game1.world.Cell;

public class GridlockedPlayer {
	private double x,y;
	private boolean up,down,right,left,updown,rightLeft;
	private int cellSize=64;
	private Cell cell;
	private int moveTimer = 250; 
	
	public GridlockedPlayer(){
		x = 100;
		y = 100;
		this.getCell();
	}
	
	public GridlockedPlayer(int i, int j){
		x = i*cellSize+10;
		y = j*cellSize+10;
		this.getCell();
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setColor(Color.magenta);
		arg2.fillRect((float)x,(float) y, (float)50, (float)50);

	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		moveTimer = moveTimer - arg2;
		if(moveTimer <=0){
			moveTimer = 125;
			this.getCell();
			move();
		}
	}
	
	
	public void keyPressed(int key, char c) {
		switch (key){
		
		case Input.KEY_UP:
			up=true;
			updown=false;
		break;
		
		case Input.KEY_DOWN:
			down=true;
			updown=true;
		break;
		
		case Input.KEY_LEFT:
			left=true;
			rightLeft=false;
		break;
		case Input.KEY_RIGHT:
			right=true;
			rightLeft=true;
		break;
		}
		
	}
	
	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			up=false;
			break;
		case Input.KEY_RIGHT:
			right=false;
			break;
		case Input.KEY_LEFT:
			left=false;
			break;
		case Input.KEY_DOWN:
			down=false;
			break;
		}
	}
	
	private void move() {
		if(((up && !down) || (up && down && !updown)) )
		{
			if(!this.cell.isNorthWall() && y > cellSize) y = y - cellSize;
		}
		if(((down && !up) || (up && down && updown))){
				if(!this.cell.isSouthWall() && y < game1.world.World1.getLabyrinth().getLines()*cellSize - cellSize) y = y + cellSize;
		}
		if(((left && !right)|| (left && right && !rightLeft)))
		{
				if(!this.cell.isWestWall() && x > cellSize) x = x - cellSize;
		}
		if(((!left && right)|| (left && right && rightLeft)))
		{
				if(!this.cell.isEastWall() && x < game1.world.World1.getLabyrinth().getRows()*cellSize - cellSize ) x = x + cellSize;
		}
	}
	
	public void getCell(){
		int j=(int) Math.floor((x-10)/cellSize);
		int i=(int) Math.floor((y-10)/cellSize);
		this.cell=game1.world.World1.getLabyrinth().getCell(i,j);
	}
	
	public int getI()
	{
		return this.cell.getI();
	}
	
	public int getJ()
	{
		return this.cell.getJ();
	}
}
