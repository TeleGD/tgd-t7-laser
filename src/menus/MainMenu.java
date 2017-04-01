package menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import game1.world.World1;
import game2.world.World2;
import game3.world.MainMenu3;
import game3.world.World3;

public class MainMenu extends Menu{

	public static int ID = -3;	
	
	public MainMenu(){
		super.setTitrePrincipal("MULTIGAME DESIGN");
		super.setTitreSecondaire("Menu Principal");
		super.setItems(World1.GAME_NAME,World2.GAME_NAME,World3.GAME_NAME,"Scores", "Quitter");
		
		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
	}
	
	@Override
	public void onOptionItemFocusedChanged(int position) {
		time=System.currentTimeMillis();
	}

	@Override
	public void onOptionItemSelected(int position) {
		switch (position) {
		case 0:
			World1.reset();
			game.enterState(World1.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 1:
			World2.reset();
			game.enterState(World2.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 2:
			game.enterState(MainMenu3.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 3:
			System.out.println("gone there");
			game.enterState(ScoreMenu.ID, new FadeOutTransition(),
					new FadeInTransition());
			break;
		case 4:
			System.out.println("exit");
			System.exit(0);
			break;
		}
	}
	
	@Override
	public int getID() {
		return ID;
	}

}
