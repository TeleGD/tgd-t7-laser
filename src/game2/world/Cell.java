package game2.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Cell {
	
	//Variables
	private int x;
	private int y;
	private Boolean contains;
	private Boolean deadly;
	private boolean hasEnnemy;
	private Image image;
	protected Image normal;
	protected static Image mine;
	protected Image bonus;
	
	
	//Constructeur
	public Cell(int x, int y, Boolean c, Boolean d) throws SlickException{
		this.x=x;
		this.y=y;
		this.contains=c;
		this.deadly=d;
		this.normal= new Image("Images/T7Laser/Cell.png");
		this.mine=new Image("Images/T7Laser/Mine.png");
		this.bonus=new Image("Images/T7Laser/Bonus.png");
		this.image=(normal);
		this.hasEnnemy = false;
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
		if(this.image == mine)
			this.deadly = true;
	}
	public Image getImage(){
		return image;
	}
	public void setImage(Image i){
		this.image=i;
	}
	
	//render et update
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		//Affichage
		arg2.drawImage(image,0,0);
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if(deadly && contains)
			World2.getPlayer().setLives(0);
		
	}


	public boolean isHasEnnemy() {
		return hasEnnemy;
	}


	public void setHasEnnemy(boolean hasEnnemy) {
		this.hasEnnemy = hasEnnemy;
	}
	
}
