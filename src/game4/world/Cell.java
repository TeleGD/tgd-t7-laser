package game4.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Cell {

	private int i,j;
	private Boolean contains,road;
	private Image sprite;
	private Cell next;


	public Cell(int i, int j /* Boolean contains, Image sprite*/) {
		this.i = i;
		this.j = j;
		//this.contains = contains;
		//this.sprite = sprite;
		road=false;
	}

	public void setNext(Cell nextCell){
		this.next=nextCell;
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
	}
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		if(this.road){
			arg2.setColor(Color.green);
		}else{
			arg2.setColor(Color.blue);
		}
		arg2.fillRect(i*64+1, j*64+1, 62, 62);
		arg2.setColor(Color.black);
		arg2.drawString("i="+i, i*64+20, j*64+20);
		arg2.drawString("j="+j, i*64+20, j*64+30);
	}
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		//TODO
	}

	public boolean isRoad(){
		return road;
	}
	public void setRoad(){
		this.road=true;
	}
}
