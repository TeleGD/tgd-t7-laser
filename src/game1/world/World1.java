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
		
		//TODO (by P-A) fouetter Nicolas
		/*
		cellTest = new ArrayList<Cell>();
		cellTest.add(new Cell(17,0));
		cellTest.add(new Cell(18,0));
		cellTest.add(new Cell(19,0));
		cellTest.add(new Cell(17,1));
		cellTest.add(new Cell(17,2));
		cellTest.add(new Cell(18,2));
		cellTest.add(new Cell(19,1));
		cellTest.add(new Cell(19,2));
		
		cellTest.get(0).setEastWall(false);
		cellTest.get(0).setSouthWall(false);
		
		cellTest.get(1).setEastWall(false);
		cellTest.get(1).setWestWall(false);
		
		cellTest.get(2).setWestWall(false);
		cellTest.get(2).setSouthWall(false);
		
		cellTest.get(3).setNorthWall(false);
		cellTest.get(3).setSouthWall(false);
		
		cellTest.get(4).setNorthWall(false);
		cellTest.get(4).setSouthWall(false);
		
		cellTest.get(5).setEastWall(false);
		cellTest.get(5).setNorthWall(false);

		cellTest.get(6).setEastWall(false);
		cellTest.get(6).setWestWall(false);
		

		cellTest.get(2).setWestWall(false);
		cellTest.get(2).setNorthWall(false);
		*/
		
		player = new Player(15,0);
		
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
