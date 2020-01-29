package games.t7Laser;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

public class Player {

	private World world;
	private int x = 0;
	private int y = 0;
	private Image image;
	private Image down;
	private Image up;
	private Image left;
	private Image right;

	private int lives = 1;

	private boolean moveLeft,moveRight,moveUp,moveDown  =false;
	private boolean pressEnter = false;

	public Player(World world) {
		this.world = world;
		//position initiale
		world.getGrid().getCell(0, 0).setContains(true);
		this.x = 0;
		this.y = 0;
		this.image = AppLoader.loadPicture(World.DIRECTORY_IMAGES+"Char_down.png");
		this.down= AppLoader.loadPicture(World.DIRECTORY_IMAGES+"Char_down.png");
		this.up=AppLoader.loadPicture(World.DIRECTORY_IMAGES+"Char_up.png");
		this.right=AppLoader.loadPicture(World.DIRECTORY_IMAGES+"Char_right.png");
		this.left=AppLoader.loadPicture(World.DIRECTORY_IMAGES+"Char_left.png");
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		//Affichage
		image.draw(280+(x*100*world.getRenderScale())+360-world.getGrid().getColumns()*100*world.getRenderScale()/2,y*100*world.getRenderScale()+360-world.getGrid().getColumns()*100*world.getRenderScale()/2,100*world.getRenderScale(),100*world.getRenderScale());
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		callMove();
	}

	private void callMove() {
		if(moveUp && !moveDown){ //haut
			move(x,y-1);
			this.image = up;
			moveUp = false;
		}
		if(moveDown && !moveUp){ //bas
			move(x,y+1);
			this.image = down;
			moveDown = false;
		}
		if(moveLeft && !moveRight){ //gauche
			move(x-1,y);
			this.image = left;
			moveLeft = false;
		}
		if(moveRight && !moveLeft){ //droite
			move(x+1,y);
			this.image = right;
			moveRight = false;
		}
	}

	public boolean isMoveUp() {
		return moveUp;
	}

	public boolean isMoveDown() {
		return moveDown;
	}

	public void setMoveUp(boolean b) {
		moveUp = b;
	}

	public void setMoveDown(boolean b) {
		moveDown = b;
	}

	private void move(int x, int y) {
		if(world.getGrid().MovePlayer(x, y, this)){
			//if move worked
			this.x = x;
			this.y =y;
		}
	}

	public void keyReleased(int key, char c) {
		switch (key) {
		//mouvement
			case Input.KEY_UP: //haut
				moveUp = false;
				break;
			case Input.KEY_DOWN: //bas
				moveDown = false;
				break;
			case Input.KEY_LEFT: //gauche
				moveLeft = false;
				break;
			case Input.KEY_RIGHT: //droite
				moveRight = false;
				break;
			case Input.KEY_ENTER: //entrer
				pressEnter = false;
				break;
			}
	}

	public void keyPressed(int key, char c) {
		switch (key) {
		//mouvement
		case Input.KEY_UP: //haut
			moveUp = true;
			break;
		case Input.KEY_DOWN: //bas
			moveDown = true;
			break;
		case Input.KEY_LEFT: //gauche
			moveLeft = true;
			break;
		case Input.KEY_RIGHT: //droite
			moveRight = true;
			break;
		case Input.KEY_ENTER: //entrer
			pressEnter = true;
			break;
		}
	}

	public boolean isDead() {
		return this.lives <= 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public boolean isPressEnter() {
		return pressEnter;
	}

}
