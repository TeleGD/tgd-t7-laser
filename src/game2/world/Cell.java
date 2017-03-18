package game2.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Cell {
	
	//Variables
	private int x;
	private int y;
	private Boolean contains;
	private Boolean deadly;
	
	
	//Constructeur
	public Cell(int x, int y, Boolean c, Boolean d){
		this.x=x;
		this.y=y;
		this.contains=c;
		this.deadly=d;
	}
	
	
	//Getters et Setters
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
	public Boolean getContains() {
		return contains;
	}
	public void setContains(Boolean contains) {
		this.contains = contains;
	}
	public Boolean getDeadly() {
		return deadly;
	}
	public void setDeadly(Boolean deadly) {
		this.deadly = deadly;
	}
	
	
	//render et update
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		//Affichage
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
	}
	
}
