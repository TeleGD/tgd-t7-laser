package game1.characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game1.world.Cell;

public class Player {
	private double x,y;
	private boolean up,down,right,left,updown,rightLeft;
	private int cellSize=10;
	private Cell cell;
	private double speedX,speedY;
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		//Affichage
		arg2.drawString("Bonjour 1", 500, 400);

	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		move();
		x+=speedX*arg2;
		y+=speedY*arg2;
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
		speedX = 0;
		speedY = 0;
		if((up && !down) || (up && down && !updown)) 
		{
				speedY=-0.5;
			
		}
		if((down && !up) || (up && down && updown)){
				speedY=0.5;
		}
		if((left && !right)|| (left && right && !rightLeft))
		{
				speedX = -0.5;
			
		}
		if((!left && right)|| (left && right && rightLeft))
		{

				speedX = 0.5;
		}
	}
	
	public void getCell(){
		int i=(int) Math.floor(x/cellSize);
		int j=(int) Math.floor(y/cellSize);
		this.cell=game1.world.World1.getLabyrinth().getCell(i,j);
	}
}
