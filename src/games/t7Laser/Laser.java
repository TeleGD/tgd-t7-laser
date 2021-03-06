package games.t7Laser;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Laser {

	private World world;
	private int axe; //0 pour horizontal et 1 pour vertical
	private int pos; //position associée à la case correspondante
	private Boolean shooting;

	private int setupTime;

	private int shootingTime;

	public Laser(World world, int axe, int pos) {
		this.world = world;
		this.axe = axe;
		this.pos = pos;
		this.shooting = false;
		this.setupTime = 50;
		this.shootingTime = 100;
	}

	private void setDeadlyCells(boolean deadly) {
		Grid g = world.getGrid();
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

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		if(!shooting)
			g.setColor(new Color(255,128,0,204));
		else
			g.setColor(new Color(255,0,0,204));
		//Affichage
		if(axe == 0) {
			//horizontal
			g.fillRect((float)280+360-world.getGrid().getColumns()*100*world.getRenderScale()/2, (pos*100+25)*world.getRenderScale()+360-world.getGrid().getColumns()*100*world.getRenderScale()/2, (float)100*world.getGrid().getRows()*world.getRenderScale(), 50*world.getRenderScale());
		}
		else {
			//vertical
			g.fillRect((pos*100+25)*world.getRenderScale()+280+360-world.getGrid().getColumns()*100*world.getRenderScale()/2, +360-world.getGrid().getColumns()*100*world.getRenderScale()/2, 50*world.getRenderScale(), (float)100*world.getGrid().getColumns()*world.getRenderScale());
		}
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
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
			world.getGrid().removeLaser(this);
	}

}
