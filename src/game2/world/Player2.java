package game2.world;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Player2{
	
	private int x = 0;
	private int y = 0;
	private float width = 100;
	private float height = 100;
	private Image image;
	private Image down;
	private Image up;
	private Image left;
	private Image right;
	
	
	private int lives = 1;
	
	private boolean moveLeft,moveRight,moveUp,moveDown  =false;
	
	public Player2() throws SlickException{
		//position initiale
		World2.getGrid().getCell(0, 0).setContains(true);
		this.x = 0;
		this.y = 0;
		this.setImage(new Image("Images/T7Laser/Char_down.png"));
		this.down= new Image("Images/T7Laser/Char_down.png");
		this.up=new Image("Images/T7Laser/Char_up.png");
		this.right=new Image("Images/T7Laser/Char_right.png");
		this.left=new Image("Images/T7Laser/Char_left.png");
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//Affichage
		image.draw(280+(x*100*World2.getRenderScale())+360-World2.getGrid().getColumns()*100*World2.getRenderScale()/2,y*100*World2.getRenderScale()+360-World2.getGrid().getColumns()*100*World2.getRenderScale()/2,100*World2.getRenderScale(),100*World2.getRenderScale());

	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		callMove();
	}
	
	public void callMove() throws SlickException{
		if(moveUp && !moveDown){ //haut
			move(x,y-1);
			this.setImage(up);
			moveUp = false;
		}
		if(moveDown && !moveUp){ //bas
			move(x,y+1);
			this.setImage(down);
			moveDown = false;
		}
		if(moveLeft && !moveRight){ //gauche
			move(x-1,y);
			this.setImage(left);
			moveLeft = false;
		}
		if(moveRight && !moveLeft){ //droite
			move(x+1,y);
			this.setImage(right);
			moveRight = false;
		}
	}
	
	public void move(int x,int y){
		
		if(World2.getGrid().MovePlayer(x, y, this)){
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
			}
		
	}

	public void keyPressed(int key,char c ) {
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
		}
		
	}

	
	public boolean isDead(){
		return this.lives <= 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}


	
	
	
}
