package game3.world;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;

public class Pendulum extends BasicGameState{
	private float theta;
	private float length;
	private float initialAngle;
	private double omega;
	
	public Pendulum(){
		initialAngle=(float) (Math.PI/2);
		omega=World3.GRAVITY/length;
		
	}
	
	
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		theta=0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawLine((float)(Main.longueur/2), 0, (float)(length*Math.cos(theta)),  (float)(length*Math.sin(theta)));
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int arg2) throws SlickException {
		theta=(float) (initialAngle*Math.cos(omega*World3.getTime())); //theta=theta0cos(wt)
		System.out.println(theta);
		System.out.println("Time="+World3.getTime());
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
