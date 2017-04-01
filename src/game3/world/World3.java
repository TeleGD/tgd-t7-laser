package game3.world;

import java.awt.Font;
import java.io.File;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import db.SQLiteJDBC;
import general.Main;
import general.utils.FontUtils;
import menus.MainMenu;

public class World3 extends BasicGameState{
	public final static float GRAVITY= 0.3f;
	public final static int ID=3;
	
	public final static String GAME_NAME="Cathedral Bloxx";
	
	public final static String GAME_FOLDER_NAME="CathedralBloxx";
	public final static String DIRECTORY_SOUNDS="sounds"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_MUSICS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_IMAGES="images"+File.separator+GAME_FOLDER_NAME+File.separator;
	
	private static int score = 0;
	public static Pendulum pendulum;
	public static long timeInitial;
	public static Tower tower;
	public static Decor decor;
	public static int difficulty;
	public static String colorImage;
	
	private Image toitImage;
	private TrueTypeFont fontPerdu;
	private static boolean perdu=false;
	private static Music soundMusicBackground;
	private StateBasedGame game;
	private Block endBlock;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game=game;
	}
	
	public static void setDifficulty(int difficulty){
		if(difficulty==0){
			colorImage="Rouge";
			pendulum=new Pendulum();
		}
		else if(difficulty==1){
			colorImage="Bleu";
			pendulum=new Pendulum();
			pendulum.setSpeed(pendulum.getSpeed()*2);
			pendulum.setLength(1650);
		}
		else if(difficulty==2){
			colorImage="Vert";
			pendulum=new Pendulum();
			pendulum.setSpeed(pendulum.getSpeed()*4);
			pendulum.setLength(1500);
		}
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		//pendulum.enter(container, game);
		setDifficulty(difficulty);

		fontPerdu=FontUtils.chargerFont("font/PressStart2P.ttf",Font.BOLD,40,false);
		toitImage=new Image(DIRECTORY_IMAGES+"Blocs/"+World3.colorImage+" Toit.png");
		
		decor=new Decor();
		timeInitial=System.currentTimeMillis(); // on reinitialise le temps
		tower=new Tower(Main.longueur/2,Main.hauteur,new Block(pendulum.getX() - 50, Main.hauteur-101,100,100,new Image(World3.DIRECTORY_IMAGES+"Blocs/"+World3.colorImage+" Porte.png")));
		
		soundMusicBackground=new Music(DIRECTORY_MUSICS+"what_is_love.ogg");
		soundMusicBackground.play(1, 0.3f);
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps
	}
	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//Affichage
		
		  
        decor.render(container, game, g);
        tower.render(container, game, g);
        if(!perdu || pendulum.getBlock().isRealeased())pendulum.render(container, game,g);

        if(perdu){
        	int t=fontPerdu.getWidth("Score :"+score);
        	g.setColor(new Color(80,80,20));
        	g.fillRoundRect(Main.longueur/2-t/2-40,  Main.hauteur/2-150, t+80, 300,25,25);
            g.setFont(fontPerdu);
            g.setColor(Color.white);
            g.drawString("PERDU !", Main.longueur/2-fontPerdu.getWidth("PERDU !")/2, Main.hauteur/2);
            //g.setFont(FontUtils.chargerFont("Kalinga", Font.PLAIN, 15, true));
            //g.drawString("PRESS ENTER TO RESTART",Main.longueur/2-t/2-40,Main.hauteur/2+100);
        }
        
        g.setFont(fontPerdu);
        g.setColor(Color.cyan);
        g.drawString("Score :"+score, Main.longueur/2-fontPerdu.getWidth("Score :"+score)/2, Main.hauteur/2-100);

      
	}
	

	@Override
	public void update(GameContainer container, StateBasedGame game, int compt) throws SlickException {
		decor.update(container, game, compt);
		tower.update(container, game, compt);
		
        if(!perdu || pendulum.getBlock().isRealeased())pendulum.update(container, game,compt);

		if(!perdu && pendulum.getBlock().isRealeased() && pendulum.getBlock().getY()>Main.hauteur){
			pendulum.finishTower();
			perdu=true;
			db.SQLiteJDBC.updateScore("Jeje", 3, score);
		}
		
		
	}
	
	@Override
	public void keyPressed(int key, char  c){
  		switch(key){
  		case Input.KEY_SPACE:
  			pendulum.releaseBlock();
 			if(perdu){
 				reset();
 			}else{
 				pendulum.releaseBlock();
 			}
 			break;
  		case Input.KEY_ENTER:
  		if(perdu){
				reset();
			}
  		break;
  		case Input.KEY_ESCAPE:
  			if(perdu){
  				this.soundMusicBackground.stop();
				game.enterState(MainMenu3.ID);
			}
  			break;
  		case Input.KEY_F1:
  			this.soundMusicBackground.stop();
  			game.enterState(MainMenu.ID);
  			break;
  			
  		}
  	}

	@Override
	public int getID() {
		return ID;
	}

	public static void reset() {
		 		perdu=false;
		 		try {
		 			decor=new Decor();
		 			score=0;
		 			setDifficulty(difficulty);
		 			timeInitial=System.currentTimeMillis(); // on reinitialise le temps
		 			tower=new Tower(Main.longueur/2,Main.hauteur,new Block(pendulum.getX() - 50, Main.hauteur-101,100,100,new Image(World3.DIRECTORY_IMAGES+"Blocs/"+World3.colorImage+" Porte.png")));

		 			soundMusicBackground=new Music(DIRECTORY_SOUNDS+"what_is_love.wav");
		 			soundMusicBackground.play(1, 0.3f);
		 		} catch (SlickException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
		 		
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

	
	public static Pendulum getPendulum() {
		// TODO Auto-generated method stub
		return pendulum;
	}

	public static Decor getDecor() {
		return decor;
	}

	public static void notifyStackedBlock() {
		// TODO Auto-generated method stub
		
	}
	
	public static int  getScore() {
		return score;
	}

	public static void setScore(int score) {
		World3.score = score;
	}

	
}
