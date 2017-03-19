package game4.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Cell {

	private int i,j;
	private Boolean contains;
	private Image sprite;
	
	public Cell(int i, int j, Boolean contains, Image sprite) {
		super();
		this.i = i;
		this.j = j;
		this.contains = contains;
		this.sprite = sprite;
	}

	public int getI() {
		return i;
	}
	public int getJ() {
		return j;
	}
	public Boolean getContains() {
		return contains;
	}
	public Image getSprite() {
		return sprite;
	}
	public void setI(int i) {
		this.i = i;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public void setContains(Boolean contains) {
		this.contains = contains;
	}
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	
	public void autoSetSprite() throws SlickException {
		//TODO
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException 
	{
		//TODO
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		//TODO
	}
	
}
