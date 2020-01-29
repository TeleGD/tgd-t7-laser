package games.t7Laser;

import java.io.File;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import api.TGDApi;

import app.AppLoader;
import app.ui.Button;
import app.ui.TGDComponent;
import app.ui.TGDComponent.OnClickListener;
import app.ui.TextField;
import app.ui.TextField.EnterActionListener;

public class World extends BasicGameState implements EnterActionListener, OnClickListener {

	public final static String GAME_NAME="T7 Laser";

	public final static String GAME_FOLDER_NAME="t7Laser";
	public final static String DIRECTORY_SOUNDS="/sounds"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_MUSICS="/musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_IMAGES="/images"+File.separator+GAME_FOLDER_NAME+File.separator;

	private Player player;
	private Grid grid;
	private int score;
	private Audio music;
	private Audio end;
	private Audio cat;
	private float musicPos;
	private int selec;
	private int cpt;
	private boolean start;
	private boolean disp;

	private float renderScale;

	private TextField textField;
	private Button button;

	private int ID;
	private int state;

	public World(int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au chargement du programme */
		music = AppLoader.loadAudio(DIRECTORY_MUSICS+"EpicSaxGuy.ogg");
		end = AppLoader.loadAudio(DIRECTORY_SOUNDS+"EndSong.ogg");
		cat= AppLoader.loadAudio(DIRECTORY_SOUNDS+"Cat.ogg");
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play(container, game);
		} else if (this.state == 2) {
			this.resume(container, game);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause(container, game);
		} else if (this.state == 3) {
			this.stop(container, game);
			this.state = 0; // TODO: remove
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			this.setState(1);
			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
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
					end.playAsSoundEffect(1, .3f, false);
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
						this.setState(3);
						game.enterState(1, new FadeOutTransition(), new FadeInTransition());
					} else {
						music.playAsMusic(1, .3f, true);
						grid =  new Grid(this,4,4);
						player = new Player(this);
						setScore(0);
						selec = 0;
						renderScale = 1;
					}
				}
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		context.setColor(Color.white);

		context.fillRect(0,0,1280,720);
		if (!start){
			context.setColor(Color.black);
			if (disp) context.drawString("Press Enter", 600, 355);
		} else {
			grid.render(container,game,context);
			player.render(container,game,context);

			context.setColor(Color.black);
			context.drawString("Score : "+getScore(), 88, 100);
			context.drawString("Waves : "+grid.getWaveNumber(), 95, 150);

			if (player.isDead()){
				context.setColor(Color.black);
				context.drawString("Rejouer", 100,400);
				context.drawString("Quitter", 100,450);
				context.drawString(">>>", 50, 400+selec*50);

				textField.render(container, game, context);
				button.render(container, game, context);
			}
		}
	}

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		music.playAsMusic(1, .3f, true);
		grid =  new Grid(this,4,4);
		player = new Player(this);
		setScore(0);
		selec = 0;
		cpt = -30;
		start = false;
		disp = false;
		renderScale = 1;

		initPerduView(container);
	}

	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
		musicPos = music.getPosition();
		music.stop();
	}

	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
		music.playAsMusic(1, .3f, true);
		music.setPosition(musicPos);
	}

	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
		music.stop();
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	@Override
	public void keyReleased(int key, char c) {
		player.keyReleased(key, c);
	}

	@Override
	public void keyPressed(int key, char c) {
		player.keyPressed(key, c);
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

	public Grid getGrid() {
		return grid;
	}

	public Player getPlayer() {
		return player;
	}

	public float getRenderScale() {
		return renderScale;
	}

	public void setRenderScale(float d) {
		 renderScale = d;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Audio getCat() {
		return this.cat;
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
