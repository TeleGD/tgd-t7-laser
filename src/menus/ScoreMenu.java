package menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import db.Person;

public class ScoreMenu extends Menu{

	public static int ID=-1;
	private Person player;
	
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1){
		this.player=db.SQLiteJDBC.search("nicorb");
		super.setTitrePrincipal("SCORE");
		super.setTitreSecondaire(player.getName());
		super.setItems("Score jeu 1: "+player.getScore1(),"Score jeu 2: "+player.getScore1(),"Score jeu 3: "+player.getScore1());
		
		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
		
	}
	
	@Override
	public void onOptionItemFocusedChanged(int position) {
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
	
}