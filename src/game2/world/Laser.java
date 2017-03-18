package game2.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Laser {
	
	private int axe; //0 pour horizontal et 1 pour vertical
	private int pos; //position associée à la case correspondante
	private Boolean shooting; 
	
	
	public Laser(int axe, int pos){
		this.setAxe(axe);
		this.setPos(pos);
	}
	
	
	public int getAxe() {
		return axe;
	}

	public void setAxe(int axe) {
		this.axe = axe;
	}
	
	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public Boolean getShooting() {
		return shooting;
	}

	public void setShooting(Boolean shooting) {
		this.shooting = shooting;
	}
	
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		//Affichage
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
	}

}
