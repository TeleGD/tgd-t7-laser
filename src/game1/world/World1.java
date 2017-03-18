package game1.world;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import game1.characters.Player;

public class World1 extends BasicGameState{

	public static int ID=1;
	private static Labyrinth labyrinth;
	private MazeGenerator mazeGenerator;
	private static Player player;
	private ArrayList<Cell> cellTest;
	private static int score;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//Ici ne mettre que des initialisations de variables 
		labyrinth = new Labyrinth(16,16);
		
	}
	
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1){
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps
		
		player = new Player(15,0);
		mazeGenerator = new MazeGenerator(labyrinth);
		try {
			mazeGenerator.mazeGenrator();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur mazeGenerator");
			e.printStackTrace();
		}
		System.out.println("fin genration");
	}
	

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		//Affichage
		arg2.setColor(Color.white);
		labyrinth.render(arg0, arg1, arg2);
		arg2.drawString("Bonjour 1", 500, 400);
		player.render(arg0, arg1, arg2);

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub		
		player.update(arg0, arg1, arg2);
	}

	@Override
	public int getID() {
		return ID;
	}

	public static void reset() {
		// TODO Auto-generated method stub
		
	}
	
	public static Labyrinth getLabyrinth(){
		return labyrinth;
	}
	
	public void keyPressed(int key, char c) {
		player.keyPressed(key, c);
	}
	
	public void keyReleased(int key, char c) {
		player.keyReleased(key, c);
	}

	public static Player getPlayer(){
		return player;
	}

	public static int  getScore() {
		return score;
	}

	public static void setScore(int score) {
		World1.score = score;
	}
	
	
}
