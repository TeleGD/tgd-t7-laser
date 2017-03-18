package game3.world;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;

public class Pendulum extends BasicGameState{
	private int x;
	private int y;
	private int speed;
	private float theta;
	private float length;
	private float initialAngle;
	private double omega;
	private Block block;
	
	public Pendulum(){
		x=Main.longueur/2;
		y=-100;
		speed=2000;
		length=400;
		initialAngle=(float) (-Math.PI/4);
		omega=World3.GRAVITY/length;
		block=new Block(0,0,100,100);
		
	}
	
	
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		theta=0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawLine((float)(Main.longueur/2), y,block.getCenterX(), block.getCenterY());
		block.render(container, game, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2) throws SlickException {
		theta=(float) (initialAngle*Math.cos(speed*(double)(omega*World3.getTimeInMillis()/1000.0))); //theta=theta0cos(wt)
		block.setCenterX(x+ (float)(length*Math.sin(theta)));
		block.setCenterY(y+ (float)(length*Math.cos(theta)));
		block.setAngle(theta);
		System.out.println(theta);
		System.out.println("Time="+World3.getTime());
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getX() {
		return this.x;
	}
	public float getY() {
		return this.y;
	}

}
