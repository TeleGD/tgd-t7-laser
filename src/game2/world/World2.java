package game2.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class World2 extends BasicGameState{

	public static int ID=2;
	private static Player2 player;
	private static Grid2 grid;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//Ici ne mettre que des initialisations de variables 
	}
	
	public void enter(GameContainer arg0, StateBasedGame arg1){
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps
		grid =  new Grid2(4,4);
		player = new Player2();
	}
	

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		//Affichage
		player.render(arg0,arg1,arg2);

	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		player.update(arg0,arg1,arg2);

	}
	
	public void keyReleased(int key, char c) {
		player.keyReleased(key, c);
	}


	public void keyPressed(int key, char c) {
		player.keyPressed(key, c);
	}

	public int getID() {
		return ID;
	}
	
	public static Grid2 getGrid(){
		return grid;
	}

	public static void reset() {
		// TODO Auto-generated method stub
	}
}
