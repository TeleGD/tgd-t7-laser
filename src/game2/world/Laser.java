package game2.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Laser {

	private int axe; //0 pour horizontal et 1 pour vertical
	private int pos; //position associée à la case correspondante
	private Boolean shooting = false;

	private int setupTime;

	private int shootingTime;


	public Laser(int axe, int pos){
		this.setAxe(axe);
		this.setPos(pos);

		this.setupTime = 50;
		this.shootingTime = 100;

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

	public void setDeadlyCells(boolean deadly){
		Grid2 g = World2.getGrid();
		if(axe == 0) {
			//horizontal
			for(int i = 0;i<g.getColumns();i++){
				g.getCell(i, pos).setDeadly(deadly);
			}
		}
		else {
			//vertical
			for(int i = 0;i<g.getRows();i++){
				g.getCell(pos, i).setDeadly(deadly);
			}
		}
	}



	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		if(!shooting)
			g.setColor(Color.orange);
		else
			g.setColor(Color.red);
		//Affichage
		if(axe == 0) {
			//horizontal
			g.fillRect((float)280+360-World2.getGrid().getColumns()*100*World2.getRenderScale()/2, (float)(pos*100+25)*World2.getRenderScale()+360-World2.getGrid().getColumns()*100*World2.getRenderScale()/2, (float)100*World2.getGrid().getRows()*World2.getRenderScale(), (float)50*World2.getRenderScale());
		}
		else {
			//vertical
			g.fillRect((float)(pos*100+25)*World2.getRenderScale()+280+360-World2.getGrid().getColumns()*100*World2.getRenderScale()/2, (float)+360-World2.getGrid().getColumns()*100*World2.getRenderScale()/2, (float)50*World2.getRenderScale(), (float)100*World2.getGrid().getColumns()*World2.getRenderScale());
		}

	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if(!shooting){ //temps de pr�vention
			setupTime--;
			if(setupTime == 0){
				shooting = true;
				this.setDeadlyCells(true);
			}
		}
		if(shooting){ //temps de tir
			shootingTime--;
			if(shootingTime == 0){
				this.setDeadlyCells(false);
			}
		}
		//une fois que tout �a c'est fini
		if(setupTime == 0 && shootingTime == 0)
			World2.getGrid().removeLaser(this);
	}

}
