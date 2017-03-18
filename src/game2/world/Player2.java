package game2.world;

import java.awt.event.KeyEvent;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Player2{
	
	private int x = 0;
	private int y = 0;
	private float width = 50;
	private float height = 50;
	
	private int lives = 1;
	
	private boolean moveLeft,moveRight,moveUp,moveDown  =false;
	
	public Player2(){
		
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//Affichage
		g.setColor(Color.green);
		g.fillRect((float)x, (float)y, (float)width, (float)height);

	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub

	}
	
	public void keyReleased(int key, char c) {
		switch (key) {
		//mouvement
			case Input.KEY_UP: //haut
				System.out.println("1");
				break;
			case Input.KEY_DOWN: //bas
				System.out.println("1");
				break;
			case Input.KEY_LEFT: //gauche
				System.out.println("1");
				break;
			case Input.KEY_RIGHT: //droite
				System.out.println("1");
				break;
			}
		
	}

	public void keyPressed(int key,char c ) {
		switch (key) {
		//mouvement
		case Input.KEY_UP: //haut
			System.out.println("1");
			break;
		case Input.KEY_DOWN: //bas
			System.out.println("1");
			break;
		case Input.KEY_LEFT: //gauche
			System.out.println("1");
			break;
		case Input.KEY_RIGHT: //droite
			System.out.println("1");
			break;
		}
		
	}

	
	public boolean isDead(){
		return this.lives <= 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}


	
	
	
}
