package game2.world;

import java.io.File;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import api.TGDApi;
import game3.world.MainMenu3;
import general.Main;
import general.ui.Button;
import general.ui.TGDComponent;
import general.ui.TGDComponent.OnClickListener;
import general.ui.TextField;
import general.ui.TextField.EnterActionListener;
import menus.MainMenu;

public class World2 extends BasicGameState implements EnterActionListener,OnClickListener{

	public static int ID=2;

	public final static String GAME_NAME="T7Laser";
	
	public final static String GAME_FOLDER_NAME="T7Laser";
	public final static String DIRECTORY_SOUNDS="sounds"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_MUSICS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_IMAGES="images"+File.separator+GAME_FOLDER_NAME+File.separator;
	
	private static Player2 player;
	private static Grid2 grid;
	private static int score;
	private Music music;
	private Music end;
	static Sound cat;
	private int selec;
	private int cpt=-30;
	private boolean start = false;
	private boolean disp = false;
	private static StateBasedGame game;
	
	private static float renderScale = (float)1;
	
	private TextField textField;
	private Button button;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//Ici ne mettre que des initialisations de variables 
		game=arg1;
		
	}
	
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps
		music = new Music(DIRECTORY_MUSICS+"EpicSaxGuy.ogg");
		end = new Music(DIRECTORY_MUSICS+"EndSong.ogg");
		cat= new Sound(DIRECTORY_SOUNDS+"Cat.ogg");
		music.loop();
		grid =  new Grid2(4,4);
		player = new Player2();
		setScore(0);
		selec = 0;
		renderScale = (float)1;
		start = false;
		
		initPerduView(container);
		
	}
	

	private void initPerduView(GameContainer container) {
		
		textField=new TextField(container,60,200,150,TGDComponent.AUTOMATIC);
		textField.setMaxNumberOfLetter(13);
		textField.setUpperCaseLock(true);
		textField.setBorderWidth(2);
		textField.setBorderColor(Color.black);
		textField.setTextColor(Color.black);
		textField.setPlaceHolder("Entrez un pseudo");
		textField.setEnterActionListener(this);
		
		button=new Button("ENREGISTRER",container,textField.getX()+160,200,TGDComponent.AUTOMATIC,textField.getHeight());
		button.setOnClickListener(this);
		button.setPadding(10,15,10,15);		
		button.setBorderWidth(2);
		button.setBorderColor(Color.black);
		button.setBackgroundColorEntered(Color.black);
		button.setTextColorEntered(Color.white);
		button.setTextColor(Color.black);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//Affichage
		g.setColor(Color.white);
		
		g.fillRect(0,0,1280,720);
		if (!start){
			g.setColor(Color.black);
			if (disp) g.drawString("Press Enter", 600, 355);
		} else {
			grid.render(container,game,g);
			player.render(container,game,g);
		
			g.setColor(Color.black);
			g.drawString("Score : "+getScore(), 88, 100);
			g.drawString("Waves : "+grid.getWaveNumber(), 95, 150);
		
			if (player.isDead()){
				
				g.setColor(Color.black);
				g.drawString("Rejouer", 100,400);
				g.drawString("Quitter", 100,450);
				g.drawString(">>>", 50, 400+selec*50);

				textField.render(container, game, g);
				button.render(container, game, g);
			}
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (!start){
			if (cpt > 60) {
				cpt = 0;
				disp = !disp;
			}
			cpt++;
			if( player.isPressEnter()){
				start = true;
				cpt = 10;
			}
		}
		if (start){
			if (!player.isDead()){
				player.update(container,game,delta);
				grid.update(container, game, delta);
				setScore(getScore() + 1);
				if (player.isDead()) {
					music.stop();
					end.play();
				}
			}
			
			if(player.isDead()){
				
				button.update(container, game, delta);
				textField.update(container, game, delta);
				textField.setHasFocus(true);

				
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
						game.enterState(MainMenu.ID, new FadeOutTransition(),
								new FadeInTransition());
					} else {
						music.loop();
						grid =  new Grid2(4,4);
						player = new Player2();
						setScore(0);
						selec = 0;
						renderScale = (float)1;
					}
				}
			}
		}
	}
	
	public void keyReleased(int key, char c) {
		player.keyReleased(key, c);
	}


	public void keyPressed(int key, char c) {
		player.keyPressed(key, c);
		if(key==Input.KEY_F1){
			music.stop();
			game.enterState(MainMenu.ID);
		}
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

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		World2.score = score;
	}

	
	@Override
	public void onClick(TGDComponent component) {
		TGDApi.updateScoreForGame(textField.getText(), 2, score);
	}

	@Override
	public void onEnterPressed() {
		TGDApi.updateScoreForGame(textField.getText(), 2, score);
	}
}