package games.t7Laser;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

public class Ennemy {

	private World world;
	private int x;
	private int y;
	private int moveTimer;

	private Image image;

	public Ennemy(World world, int x, int y) {
		this.world = world;
		image = AppLoader.loadPicture(World.DIRECTORY_IMAGES+"nyan.png");
		this.x = x;
		this.y = y;
		this.moveTimer = 100;
		world.getGrid().getCell(x, y).setDeadly(true);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		//Affichage
		image.draw(280+(x*100*world.getRenderScale())+360-world.getGrid().getColumns()*100*world.getRenderScale()/2,y*100*world.getRenderScale()+360-world.getGrid().getColumns()*100*world.getRenderScale()/2,100*world.getRenderScale(),100*world.getRenderScale());
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		moveTimer--;
		Player p = world.getPlayer();
		int newX = x;
		int newY = y;
		if(moveTimer <= 0){
			if(x > p.getX())
				newX = x-1;
			if(x < p.getX())
				newX = x+1;
			if(y > p.getY())
				newY = y-1;
			if(y < p.getY())
				newY = y+1;

			if(world.getGrid().MoveEnnemy(newX, newY, this)){
				//move
				x = newX;
				y = newY;
			}

			moveTimer=50;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
