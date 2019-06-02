package game4;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game3.world.World3;
import game4.world.World4;

public class Main4 extends StateBasedGame{

	public static int longueur=1280;
	public static int hauteur=720;

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main4(),longueur, hauteur, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}


	public Main4() {
		super("MultiGame4");
	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new World4());

		this.enterState(World4.ID);
	}
}
