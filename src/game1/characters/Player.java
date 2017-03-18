package game1.characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game1.world.Cell;

public class Player {
	private double x,y;
	private boolean up,down,right,left;
	private int cellSize=10;
	private Cell cell;
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		//Affichage
		arg2.drawString("Bonjour 1", 500, 400);

	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub

		
		
	}
	
	
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			up=true;
			break;
		case Input.KEY_RIGHT:
			right=true;
			break;
		case Input.KEY_LEFT:
			left=true;
			break;
		case Input.KEY_DOWN:
			down=true;
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
	
	public void move(){
		
	}
	
	public Cell getCell(){
		int i=(int) Math.floor(x/cellSize);
		int j=(int) Math.floor(y/cellSize);
		this.cellSize=game1.world.World1.getLabyrinth().getCell(i,j);
		
	}
}
