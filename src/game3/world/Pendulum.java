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
		
		if(!block.isRealeased()){
			block.render(container, game, g);
			g.drawLine((float)(Main.longueur/2), y,block.getCenterX(), block.getCenterY());
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2) throws SlickException {
		theta=calculateTheta(); //theta=theta0cos(wt)
		if(!block.isRealeased()){
			block.setCenterX(x+ (float)(length*Math.sin(theta)));
			block.setCenterY(y+ (float)(length*Math.cos(theta)));
			block.setAngle(theta);
		}
		//System.out.println(theta);
		//System.out.println("Time="+World3.getTime());
	}

	private float calculateTheta() {
		return (float) (initialAngle*Math.cos(speed*(double)(omega*World3.getTimeInMillis()/1000.0)));
	}
	private float calculateThetaDot() {
		return (float) (initialAngle*-1*omega*speed*Math.sin(speed*(double)(omega*World3.getTimeInMillis()/1000.0)));
	}








	public Block releaseBlock() {
		float thetaDot=calculateThetaDot();
		System.out.println("thetaDot="+thetaDot);
		//blockDropped.drop(-(float)(length*thetaDot*Math.sin(theta))/100,(float) (thetaDot*length*Math.cos(theta))/100);
		block.drop(0,0);

		return block;
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




	public void notifyStackedBlock() {
		// TODO Auto-generated method stub
		
	}

}
