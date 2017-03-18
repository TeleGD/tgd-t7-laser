package game3.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;

public class World3 extends BasicGameState{
	public final static float GRAVITY= 0.3f;
	public final static int ID=3;
	
	public static Pendulum pendulum;
	public static long timeInitial;
	public static Tower tower;

	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		pendulum=new Pendulum();
		pendulum.init(container, game);
		
		tower=new Tower(Main.longueur/2,Main.hauteur,new Block(pendulum.getX() - 50, Main.hauteur-101,100,100));
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		pendulum.enter(container, game);
		timeInitial=System.currentTimeMillis(); // on reinitialise le temps
		
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps
	}
	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		pendulum.render(container, game,g);
		tower.render(container, game, g);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int compt) throws SlickException {
		pendulum.update(container, game,compt);
		tower.update(container, game, compt);
	}

	@Override
	public int getID() {
		return ID;
	}

	public static void reset() {
		// TODO Auto-generated method stub
	}
	
	public static long getTime(){
		return (System.currentTimeMillis()-timeInitial)/1000;
	}

	public static double getTimeInMillis() {
		// TODO Auto-generated method stub
		return (System.currentTimeMillis()-timeInitial);
	}

	public static Tower getTower() {
		// TODO Auto-generated method stub
		return tower;
	}
}
