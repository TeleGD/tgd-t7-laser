package menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.Main;


public class WelcomeMenu extends Menu{

	
	public static int ID = -2;	
	
	private static final String CONFIRM_TEXT="PRESS ENTER";

	private Image background;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		background=new Image("Images/LogoTGD_transparent.png");
	}

	@Override
	public void onOptionItemFocusedChanged(int position){
		time=System.currentTimeMillis();
	}
	
	@Override
	public void onOptionItemSelected(int position) {
			game.enterState(MainMenu.ID, new FadeOutTransition(),
					new FadeInTransition());
	}
	
	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		//g.drawImage(new Image("img/accueil.png"), 0, 0);
		g.setColor(Color.white);

		g.drawRect(Main.longueur/2-300,25, 600,37);
		
		
		g.setFont(fontConfirmText);
		g.drawString(CONFIRM_TEXT, Main.longueur/2-fontConfirmText.getWidth(CONFIRM_TEXT)/2,35);
		g.drawImage(background,Main.longueur/2-background.getWidth()/2,Main.hauteur/2-background.getHeight()/2);
	}
	
	@Override
	public void keyPressed(int key, char c) {

		if(key == Input.KEY_ENTER)
			onOptionItemSelected(0);
		if(key== Input.KEY_ESCAPE)
			System.exit(0);
	}

}

