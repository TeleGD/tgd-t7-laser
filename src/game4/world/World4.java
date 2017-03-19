package game4.world;

import java.util.ArrayList;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import game4.tower.core.*;

public class World4 extends BasicGameState{

	public static int ID=1;
	private static ArrayList<Ennemy> ennemy;
	private static ArrayList<Tower> tower;
	private static int score;
	private static int ressources;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//Ici ne mettre que des initialisations de variables 
		ennemy = new ArrayList<Ennemy>();
		tower = new ArrayList<Tower>();
		
	}
	
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1){
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps
		ennemy.add(new Ennemy());
	}
	

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		//Affichage
		/*
		arg2.setColor(Color.white);
		labyrinth.render(arg0, arg1, arg2);
		arg2.drawString("Bonjour 1", 500, 400);
		player.render(arg0, arg1, arg2);
		*/

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
	}

	@Override
	public int getID() {
		return ID;
	}

	public static void reset() {
		// TODO Auto-generated method stub
		ennemy.clear();
		tower.clear();
	}
	
	public static int  getScore() {
		return score;
	}

	public static void setScore(int score) {
		World4.score = score;
	}
	
	public static void setRessources(int amount){
		ressources = amount;
	}
	
	public static int getRessources(){
		return ressources;
	}
	
}
