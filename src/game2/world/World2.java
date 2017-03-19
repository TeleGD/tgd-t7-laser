package game2.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class World2 extends BasicGameState{

	public static int ID=2;
	private static Player2 player;
	private static Grid2 grid;
	private static int score;
	private Music music;
	private int selec;
	
	private static float renderScale = (float)1;
	
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//Ici ne mettre que des initialisations de variables 
		music = new Music("Music/T7Laser/EpicSaxGuy.ogg");
	}
	
	public void enter(GameContainer arg0, StateBasedGame arg1) throws SlickException{
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps
		music.loop();
		grid =  new Grid2(4,4);
		player = new Player2();
		score = 0;
		selec = 0;
	}
	

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		//Affichage
		arg2.setColor(Color.white);
		
		arg2.fillRect(0,0,1280,720);
		grid.render(arg0,arg1,arg2);
		player.render(arg0,arg1,arg2);
		
		arg2.setColor(Color.black);
		arg2.drawString("Score : "+score, 88, 100);
		arg2.drawString("Waves : "+grid.getWaveNumber(), 95, 150);
		
		if (player.isDead()){
			arg2.setColor(Color.black);
			arg2.drawString("Rejouer", 100,400);
			arg2.drawString("Quitter", 100,450);
			arg2.drawString(">>>", 50, 400+selec*50);
		}
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if (!player.isDead()){
			player.update(arg0,arg1,arg2);
			grid.update(arg0, arg1, arg2);
			score++;
		}
		
		if(player.isDead()){
			music.stop();
			if ((player.isMoveUp() && selec == 0) || (player.isMoveDown() && selec == 0)){
				selec = 1;
				player.setMoveUp(false);
				player.setMoveDown(false);
			} else if ((player.isMoveUp() && selec == 1) || (player.isMoveDown() && selec == 1)){
				selec = 0;
				player.setMoveUp(false);
				player.setMoveDown(false);
			}
			if (player.isPressEnter()){
				if (selec == 1){
					arg0.exit();
				} else {
					music.loop();
					grid =  new Grid2(4,4);
					player = new Player2();
					score = 0;
					selec = 0;
				}
			}
			System.out.println(score);
		}
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
	
	public static Player2 getPlayer(){
		return player;
	}

	public static void reset() {
		// TODO Auto-generated method stub
	}
	
	public static float  getRenderScale(){
		return renderScale;
	}
	
	public static void setRenderScale(float d){
		 renderScale = d;
	}
}