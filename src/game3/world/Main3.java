package game3.world;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game3.world.World3;

public class Main3 extends StateBasedGame{

	public static int longueur=1280;
	public static int hauteur=720;

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main3(),longueur, hauteur, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}


	public Main3() {
		super("MultiGame3");
	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new World3());

		this.enterState(World3.ID);
	}
}
