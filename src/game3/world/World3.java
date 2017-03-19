package game3.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;

public class World3 extends BasicGameState{
	public final static float GRAVITY= 0.3f;
	public final static int ID=3;
	
	public static Pendulum pendulum;
	public static long timeInitial;
	public static Tower tower;
	public static Block currentBlock;
	public static Decor decor;
	public static int difficulty;
	public static String colorImage;
	private boolean perdu=false;

	private Image toitImage;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
	
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException{
		if(difficulty==0)colorImage="Rouge";
		else if(difficulty==1)colorImage="Bleu";
		else if(difficulty==2)colorImage="Vert";

		toitImage=new Image("Images/TowerBlocks/Blocs/"+World3.colorImage+" Toit.png");
		
		this.decor=new Decor();
		pendulum=new Pendulum();
		pendulum.enter(container, game);
		timeInitial=System.currentTimeMillis(); // on reinitialise le temps
		tower=new Tower(Main.longueur/2,Main.hauteur,new Block(pendulum.getX() - 50, Main.hauteur-101,100,100,new Image("Images/TowerBlocks/Blocs/"+World3.colorImage+" Porte.png")));

		
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps
	}
	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//Affichage
		

		decor.render(container, game, g);
		pendulum.render(container, game,g);
		tower.render(container, game, g);
		
		if(currentBlock!=null){
			currentBlock.render(container, game, g);
		}
		
	}
	

	@Override
	public void update(GameContainer container, StateBasedGame game, int compt) throws SlickException {
		
		decor.update(container, game, compt);
		pendulum.update(container, game,compt);
		tower.update(container, game, compt);
		
		if(currentBlock!=null){
			currentBlock.update(container, game, compt);
			
			if(currentBlock.getY()>Main.hauteur)
			{
				currentBlock=new Block(Main.longueur/2,-100,100,100,toitImage);
				currentBlock.setRealeased(true);
				currentBlock.setIsDroping(true);
				currentBlock.setAccelY(World3.GRAVITY);
				//perdu
				perdu=true;
			}
		}
		
		
	}
	
	@Override
	public void keyPressed(int key, char  c){
		if(key==Input.KEY_SPACE){
			currentBlock=pendulum.releaseBlock();
		}
	}

	@Override
	public int getID() {
		return ID;
	}

	public static void reset() {
		// TODO Auto-generated method stub
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

	public static Block getCurrentBlock() {
		// TODO Auto-generated method stub
		return currentBlock;
	}
	public static void setCurrentBlock(Block block) {
		// TODO Auto-generated method stub
		currentBlock = block;
	}

	public static Pendulum getPendulum() {
		// TODO Auto-generated method stub
		return pendulum;
	}

	public static Decor getDecor() {
		return decor;
	}
}
