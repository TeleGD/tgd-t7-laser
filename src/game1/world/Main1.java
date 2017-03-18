package game1.world;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game1.world.World1;

public class Main1 extends StateBasedGame{
	
	public static int longueur=1280;
	public static int hauteur=720;
	
	public static void main(String[] args) throws SlickException {
		System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
		AppGameContainer app = new AppGameContainer(new Main1(),longueur, hauteur, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}
	

	public Main1() {
		super("MultiGame1");
	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new World1());
		
		this.enterState(World1.ID);
	}
}
