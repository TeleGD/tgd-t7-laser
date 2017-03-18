package game2.world;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game2.world.World2;

public class Main2 extends StateBasedGame{
	
	public static int longueur=1280;
	public static int hauteur=720;
	
	public static void main(String[] args) throws SlickException {
		System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
		AppGameContainer app = new AppGameContainer(new Main2(),longueur, hauteur, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}
	

	public Main2() {
		super("MultiGame2");
	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new World2());
		
		this.enterState(World2.ID);
	}
}
